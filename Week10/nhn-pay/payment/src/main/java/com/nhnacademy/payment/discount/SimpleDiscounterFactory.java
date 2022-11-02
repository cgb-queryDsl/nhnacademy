package com.nhnacademy.payment.discount;

public class SimpleDiscounterFactory implements DiscounterFactory {
    @Override
    public Discountable getDiscounter(DiscountCode discountCode) {
        if (DiscountCode.FIXED_RATE.equals(discountCode)) {   // 네이버검색 할인
            return new FixedRate();
        } else if (DiscountCode.VARIABLE_RATE.equals(discountCode)) { // 다나와검색 할인
            return new VariableRate();
        } else {
            return Discountable.NONE;
        }
    }
}
