package com.alex.watchshop.service;

import static java.util.Objects.requireNonNull;

import com.alex.watchshop.model.Discount;
import com.alex.watchshop.model.Watch;
import java.math.BigDecimal;

public final class WatchesPriceCalculator {

    public static BigDecimal calculatePrice(Watch watch, long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive, but was " + amount);
        }
        requireNonNull(watch);
        final var price = watch.getPrice();
        final var discount = watch.getDiscount();

        return discount.filter(d -> isDiscountApplied(d, amount))
            .map(d -> calculateDiscount(price, amount, discount.get()))
            .orElseGet(() -> price.multiply(BigDecimal.valueOf(amount)));
    }

    private static boolean isDiscountApplied(Discount discount, long watchesAmount) {
        return (watchesAmount / discount.getCount()) > 0;
    }

    private static BigDecimal calculateDiscount(BigDecimal watchPrice,
                                                long watchesAmount,
                                                Discount discount) {
        final long discountApplied = watchesAmount / discount.getCount();
        return discount.getDiscountAmount()
            .multiply(BigDecimal.valueOf(discountApplied))
            .add(watchPrice.multiply(BigDecimal.valueOf(watchesAmount - discountApplied * discount.getCount())));
    }

}
