package com.alex.watchshop.service;

import static com.alex.watchshop.model.Discount.discount;
import static com.alex.watchshop.model.Watch.watch;
import static com.alex.watchshop.request.BuyWatchRequest.buyWatchRequest;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.alex.watchshop.repository.WatchRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

class WatchServiceTest {

    private final WatchRepository watchRepository = mock(WatchRepository.class);

    private final WatchService watchService = new WatchService(watchRepository);


    @Test
    void should_calculate_total_price() {
        // given
        var michaelKors = watch("Michael Kors", BigDecimal.valueOf(80), discount(2, BigDecimal.valueOf(120)));
        var swatch = watch("Swatch", BigDecimal.valueOf(50));
        given(watchRepository.findAllById(Set.of(2L, 3L))).willReturn(List.of(michaelKors, swatch));

        // when
        var totalPrice = watchService.buy(buyWatchRequest(Map.of(michaelKors.getId(), 4L, swatch.getId(), 3L)));

        // then
        assertEquals(BigDecimal.valueOf(240 + 150), totalPrice);
    }

    @Test
    void should_return_zero_when_empty_set_of_ids_is_sent() {
        // when
        var totalPrice = watchService.buy(buyWatchRequest(Map.of()));

        // when
        assertEquals(ZERO, totalPrice);
    }
}