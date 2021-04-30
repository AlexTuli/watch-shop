package com.alex.watchshop.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Discount {

    @Id
    @GeneratedValue
    private long id;
    private int count;
    private BigDecimal discountAmount;

    public Discount() {

    }

    public Discount(int count, BigDecimal discountAmount) {
        this.count = count;
        this.discountAmount = discountAmount;
    }

    public static Discount discount(int count, BigDecimal discountAmount) {
        return new Discount(count, discountAmount);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public long getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Discount discount = (Discount) o;
        return id == discount.id && count == discount.count && Objects.equals(discountAmount,
            discount.discountAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, count, discountAmount);
    }
}
