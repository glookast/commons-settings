package com.glookast.commons.settings.groups.network;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class NetworkSettingsGroupTest {

    @Test
    public void to_JSON_minimal() throws JsonProcessingException {
        NetworkSettingsGroup networkSettingsGroup = NetworkSettingsGroup.builder().build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(networkSettingsGroup);
        assertNotNull(json);
    }

}