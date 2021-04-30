package com.alex.watchshop.model;

import static java.util.Optional.ofNullable;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "Watch.findByIds", query = "SELECT w FROM Watch w where id in :ids")
})
public class Watch {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal price;
    @ManyToOne
    private Discount discount;

    public Watch() {

    }

    private Watch(String name, BigDecimal price, Discount discount) {
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Optional<Discount> getDiscount() {
        return ofNullable(discount);
    }

    public static Watch watch(String name, BigDecimal price) {
        return new Watch(name, price, null);
    }

    public static Watch watch(String name, BigDecimal price, Discount discount) {
        return new Watch(name, price, discount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Watch watch = (Watch) o;
        return Objects.equals(id, watch.id) && Objects.equals(name, watch.name)
            && Objects.equals(price, watch.price) && Objects.equals(discount, watch.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, discount);
    }
}
