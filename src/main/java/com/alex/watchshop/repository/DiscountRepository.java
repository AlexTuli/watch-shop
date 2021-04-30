package com.alex.watchshop.repository;

import com.alex.watchshop.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

}
