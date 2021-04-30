package com.alex.watchshop.service;

import static com.alex.watchshop.service.WatchesPriceCalculator.calculatePrice;
import static java.math.BigDecimal.ZERO;

import com.alex.watchshop.repository.WatchRepository;
import com.alex.watchshop.request.BuyWatchRequest;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchService {

    private final WatchRepository watchRepository;

    @Autowired
    public WatchService(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }


    public BigDecimal buy(BuyWatchRequest watchesToBuy) {
        if (watchesToBuy.watchIds().isEmpty()) {
            return ZERO;
        }
        final var watches = watchRepository.findAllById(watchesToBuy.watchIds());

        return watches.stream()
            .map(watch -> calculatePrice(watch, watchesToBuy.amountFor(watch.getId())))
            .reduce(ZERO, BigDecimal::add);

    }

}
