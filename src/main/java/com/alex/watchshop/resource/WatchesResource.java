package com.alex.watchshop.resource;

import static com.alex.watchshop.json.Json.object;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.alex.watchshop.json.Json;
import com.alex.watchshop.request.BuyWatchRequest;
import com.alex.watchshop.response.WatchPriceResponse;
import com.alex.watchshop.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WatchesResource {

    private final WatchService watchService;

    @Autowired
    public WatchesResource(WatchService watchService) {
        this.watchService = watchService;
    }

    @PostMapping(value = "/watch/checkout", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody String checkout(@RequestBody BuyWatchRequest request) {
        return object()
            .put("totalPrice", watchService.buy(request))
            .toString();
    }

}
