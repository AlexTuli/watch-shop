package com.alex.watchshop.resource;

import static com.alex.watchshop.model.Discount.discount;
import static com.alex.watchshop.model.Watch.watch;
import static org.assertj.core.api.Assertions.assertThat;

import com.alex.watchshop.repository.DiscountRepository;
import com.alex.watchshop.repository.WatchRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WatchesResourceIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private WatchRepository watchRepository;
    @Autowired
    private TestRestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void should_calculate_prices() throws JsonProcessingException {
        // given
        var firstDiscount = discountRepository.save(discount(3, BigDecimal.valueOf(200)));
        var secondDiscount = discountRepository.save(discount(2, BigDecimal.valueOf(120)));

        var rolex = watchRepository.save(watch("Rolex", BigDecimal.valueOf(100), firstDiscount));
        var michael = watchRepository.save(watch("Michael Kors", BigDecimal.valueOf(80), secondDiscount));
        var swatch = watchRepository.save(watch("Swatch", BigDecimal.valueOf(50)));
        var casio = watchRepository.save(watch("Casio", BigDecimal.valueOf(30)));

        var request = objectMapper.createObjectNode()
            .set("watches", objectMapper.createObjectNode()
                .put(rolex.getId().toString(), "3")
                .put(swatch.getId().toString(), "5"));

        // when
        var response = restTemplate.postForEntity("http://localhost:" + port + "/watch/checkout", request,
            String.class);

        // then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(objectMapper.readTree(response.getBody()).get("totalPrice").asLong())
            .isEqualTo(450L);
    }

    @Test
    void should_return_zero_when_no_watches_are_sent() throws JsonProcessingException {
        // given
        var request = objectMapper.createObjectNode()
            .set("watches", objectMapper.createObjectNode());

        // when
        var response = restTemplate.postForEntity("http://localhost:" + port + "/watch/checkout", request,
            String.class);

        // then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(objectMapper.readTree(response.getBody()).get("totalPrice").asLong())
            .isEqualTo(0L);
    }
}
