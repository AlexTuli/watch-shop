package com.alex.watchshop.repository;

import com.alex.watchshop.model.Watch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchRepository extends JpaRepository<Watch, Long> {

}
