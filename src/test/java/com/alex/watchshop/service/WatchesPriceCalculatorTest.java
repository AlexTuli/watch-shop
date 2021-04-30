package com.alex.watchshop.service;

import static com.alex.watchshop.model.Discount.discount;
import static com.alex.watchshop.model.Watch.watch;
import static com.alex.watchshop.service.WatchesPriceCalculator.calculatePrice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WatchesPriceCalculatorTest {

    @Test
    void should_calculate_price_without_discount_when_watches_does_not_have_discount() {
        // given
        var watch = watch("Rolex", BigDecimal.valueOf(150));

        // when
        var totalPrice = calculatePrice(watch, 3);

        // then
        assertEquals(BigDecimal.valueOf(450), totalPrice);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "3  | 450",
        "6  | 800",
        "12 | 1500",
        "15 | 1950"
    }, delimiter = '|')
    void should_calculate_price_with_discount(int watchesAmount, long expectedPrice) {
        // given
        var discount = discount(4, BigDecimal.valueOf(500));
        var watch = watch("Rolex", BigDecimal.valueOf(150), discount);

        // when
        var totalPrice = calculatePrice(watch, watchesAmount);

        // then
        assertEquals(BigDecimal.valueOf(expectedPrice), totalPrice);
    }

    @ParameterizedTest
    @CsvSource({"-1", "0"})
    void should_throw_exception_when_watches_amount_is_not_positive(int watchesAmount) {
        // given
        var watch = watch("Rolex", BigDecimal.valueOf(150));

        // when
        assertThrows(IllegalArgumentException.class, () -> calculatePrice(watch, watchesAmount));
    }

    @Test
    void should_throw_err_when_watch_is_null() {
        assertThrows(NullPointerException.class, () -> calculatePrice(null, 1));
    }
}