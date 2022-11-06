package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.domain.Car;
import com.nhnacademy.gw1.parking.repository.Exit;
import com.nhnacademy.gw1.parking.domain.User;
import com.nhnacademy.gw1.parking.exception.InvalidExitGateException;
import com.nhnacademy.gw1.parking.exception.NotEnoughMoneyException;

import static com.nhnacademy.gw1.parking.domain.CarCode.*;
import static com.nhnacademy.gw1.parking.domain.Payco.*;

public class ExitServiceV2 {

    private static final long BASIC_PARK_FEE = 0L;
    private static final long ONE_HOUR_FEE = 1_000L;
    private static final long MAX_ONE_DAY_PARK_FEE = 15_000L;
    private static final long ADDITIONAL_FEE = 500L;

    private static final long TEM_MINUTES = 600L;
    private static final long HALF_HOUR = 1_800L;
    private static final long ONE_HOUR = 3_600L;
    private static final long MAX_PARK_TIME = 20_400L;
    private static final long ONE_DAY = 86_400L;
    private static final long HALF = 2;

    private static final long DISCOUNT = 10L;
    private static final long PERCENTAGE = 100L;

    public boolean isExit(User user, long parkTime, Car car, String exitGate) {
        Exit exit = new Exit();
        if (isValidExit(exitGate, exit)) {
            pay(user, parkTime, car);
            return true;
        }
        throw new InvalidExitGateException(exitGate);
    }

    public long pay(User user, long parkTime, Car car) {
        long parkFee = BASIC_PARK_FEE;

        while (true) {
            if (parkTime <= ONE_DAY) {
                break;
            }
            parkFee = addOneDayMaxFee(parkFee);
            parkTime -= ONE_DAY;
        }
        parkFee = calculateWithinOndDayFee(parkTime, parkFee);
        parkFee = isCarSmall(car, parkFee);
        parkFee = discountPaycoMember(user, parkFee);

        checkUserCanPay(user, parkFee);
        user.subtractMoney(parkFee);

        return parkFee;
    }

    private boolean isValidExit(String exitGate, Exit exit) {
        return !exit.getExit(exitGate).contains("invalid");
    }

    private long discountPaycoMember(User user, long parkFee) {
        if (user.getPayco() == MEMBER) {
            parkFee -= parkFee / (DISCOUNT % PERCENTAGE);
        }
        return parkFee;
    }

    private long calculateWithinOndDayFee(long parkTime, long parkFee) {
        if (parkTime <= HALF_HOUR) {
            parkFee = setBasicFee();
        } else if (parkTime <= ONE_HOUR) {
            parkFee = addOneHourFee(parkFee);
        } else if (parkTime < MAX_PARK_TIME) {
            parkFee = calculateBetweenMinMaxFee(parkTime, parkFee);
        } else {
            parkFee += MAX_ONE_DAY_PARK_FEE;
        }
        return parkFee;
    }

    private long calculateBetweenMinMaxFee(long parkTime, long parkFee) {
        long tmp = parkTime - ONE_HOUR;
        parkFee = addOneHourFee(parkFee);
        while (true) {
            parkFee = addAddtionalFee(parkFee);
            tmp -= TEM_MINUTES;
            if (tmp <= 0)
                break;
        }
        return parkFee;
    }

    private long addOneDayMaxFee(long parkFee) {
        parkFee += MAX_ONE_DAY_PARK_FEE;
        return parkFee;
    }

    private long addAddtionalFee(long parkFee) {
        parkFee += ADDITIONAL_FEE;
        return parkFee;
    }

    private long addOneHourFee(long parkFee) {
        parkFee += ONE_HOUR_FEE;
        return parkFee;
    }

    private long setBasicFee() {
        long parkFee;
        parkFee = BASIC_PARK_FEE;
        return parkFee;
    }

    private long isCarSmall(Car car, long parkFee) {
        if (car.getCarCode() == SMALL) {
            parkFee /= HALF;
        }
        return parkFee;
    }


    private void checkUserCanPay(User user, long parkFee) {
        if(user.getMoney() < parkFee) {
            throw new NotEnoughMoneyException(user.getMoney());
        }
    }
}

