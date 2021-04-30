package com.alex.watchshop.request;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.Set;

public class BuyWatchRequest {

    private final Map<Long, Long> watches;

    @JsonCreator
    private BuyWatchRequest(@JsonProperty("watches") Map<Long, Long> watches) {
        this.watches = requireNonNull(watches);
    }

    public Set<Long> watchIds() {
        return watches.keySet();
    }

    public long amountFor(long watchId) {
        return requireNonNull(watches.get(watchId));
    }

    public static BuyWatchRequest buyWatchRequest(Map<Long, Long> watches) {
        return new BuyWatchRequest(watches);
    }
}
