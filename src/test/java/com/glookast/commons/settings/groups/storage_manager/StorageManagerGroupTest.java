package com.glookast.commons.settings.groups.storage_manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StorageManagerGroupTest {

    @Test
    public void toJSON_defaults() throws JsonProcessingException {

        StorageManagerGroup storageManagerGroup = StorageManagerGroup.builder().build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(storageManagerGroup);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\n" +
                "  \"fileSystemEventsProcessingEnabled\": true,\n" +
                "  \"storageManagerRefreshIntervalInSeconds\": 60\n" +
                "}"), mapper.readTree(json));

        StorageManagerGroup storageManagerGroupFromJSON = mapper.readValue(json, StorageManagerGroup.class);
        assertNotNull(storageManagerGroupFromJSON);
        assertEquals(storageManagerGroup, storageManagerGroupFromJSON);

    }

    @Test
    public void toJSON() throws JsonProcessingException {

        StorageManagerGroup storageManagerGroup = StorageManagerGroup.builder()
                .fileSystemEventsProcessingEnabled(false)
                .storageManagerRefreshIntervalInSeconds(6)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(storageManagerGroup);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\n" +
                "  \"fileSystemEventsProcessingEnabled\": false,\n" +
                "  \"storageManagerRefreshIntervalInSeconds\": 6\n" +
                "}"), mapper.readTree(json));

        StorageManagerGroup storageManagerGroupFromJSON = mapper.readValue(json, StorageManagerGroup.class);
        assertNotNull(storageManagerGroupFromJSON);
        assertEquals(storageManagerGroup, storageManagerGroupFromJSON);

    }

}