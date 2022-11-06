package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.repository.Exit;
import com.nhnacademy.gw1.parking.domain.User;
import com.nhnacademy.gw1.parking.exception.InvalidExitGateException;
import com.nhnacademy.gw1.parking.exception.NotEnoughMoneyException;

public class ExitServiceV1 {

    private static final long BASIC_PARK_FEE = 1_000L;
    private static final long MAX_ONE_DAY_PARK_FEE = 10_000L;
    private static final long ADDITIONAL_FEE = 500L;

    private static final long MIN_PARK_TIME = 1_800L;   // 1800초 = 30분
    private static final long MAX_PARK_TIME = 12_600L;  // 12600초 = 3시간 30분
    private static final long ONE_DAY = 86_400L;        // 86400초 = 1일
    private static final long TEN_MINUTES = 600L;

    public boolean isExit(User user, long parkTime, String exitGate) {
        Exit exit = new Exit();
        if (isValidExit(exitGate, exit)) {
            pay(user, parkTime);
            return true;
        }
        throw new InvalidExitGateException(exitGate);
    }

    public long pay(User user, long parkTime) {
        long parkFee = 0;

        while (true) {
            if (parkTime <= ONE_DAY) {
                break;
            }
            parkFee = addOneDayMaxFee(parkFee);
            parkTime -= ONE_DAY;
        }
        parkFee = calculateWithinOndDayFee(parkTime, parkFee);

        checkUserCanPay(user, parkFee);
        user.subtractMoney(parkFee);

        return parkFee;
    }

    private boolean isValidExit(String exitGate, Exit exit) {
        return !exit.getExit(exitGate).contains("invalid");
    }

    private long calculateWithinOndDayFee(long parkTime, long parkFee) {
        if (parkTime <= MIN_PARK_TIME) {
            parkFee = addBasicFee(parkFee);
        } else if (parkTime < MAX_PARK_TIME) {
            long tmp = parkTime - MIN_PARK_TIME;
            parkFee = addBasicFee(parkFee);
            parkFee = calculateBetweenMinMaxFee(parkFee, tmp);
        } else {
            parkFee = addOneDayMaxFee(parkFee);
        }
        return parkFee;
    }

    private long addOneDayMaxFee(long parkFee) {
        parkFee += MAX_ONE_DAY_PARK_FEE;
        return parkFee;
    }

    private long addBasicFee(long parkFee) {
        parkFee += BASIC_PARK_FEE;
        return parkFee;
    }

    private long calculateBetweenMinMaxFee(long parkFee, long tmp) {
        while(true) {
            parkFee += ADDITIONAL_FEE;
            tmp -= TEN_MINUTES;
            if(tmp <= 0)
                break;
        }
        return parkFee;
    }

    private void checkUserCanPay(User user, long parkFee) {
        if(user.getMoney() < parkFee) {
            throw new NotEnoughMoneyException(user.getMoney());
        }
    }
}
