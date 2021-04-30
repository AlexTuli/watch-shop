package com.alex.watchshop.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Objects;

public class WatchPriceResponse {

    private final BigDecimal totalPrice;

    @JsonCreator
    public WatchPriceResponse(@JsonProperty("totalPrice") BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static WatchPriceResponse watchPriceResponse(BigDecimal totalPrice) {
        return new WatchPriceResponse(totalPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final var that = (WatchPriceResponse) o;
        return Objects.equals(totalPrice, that.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalPrice);
    }
}
