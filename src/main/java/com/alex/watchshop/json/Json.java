package com.alex.watchshop.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public final class Json {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static ObjectNode object() {
        return OBJECT_MAPPER.createObjectNode();
    }

}
