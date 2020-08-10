package com.glookast.commons.settings.groups.background_transfer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BackgroundTransferGroupTest {

    @Test
    public void toJSON_minimal() throws JsonProcessingException {

        BackgroundTransferGroup backgroundTransferGroup = BackgroundTransferGroup.builder()
                .temporaryFolder("temporary/folder")
                .streamingBitRate(StreamingBitRate.builder()
                        .selectedValue(1000)
                        .build())
                .noSignalPolicy(NoSignalPolicy.builder()
                        .selectedValue("BLACK_FRAME")
                        .build())
                .defaultExternalImageURI("default/external/image.jpg")
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(backgroundTransferGroup);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\n" +
                "  \"temporaryFolder\": \"temporary/folder\",\n" +
                "  \"streamingEnabled\": false,\n" +
                "  \"streamingBitRate\": {\n" +
                "    \"value\": 1000\n" +
                "  },\n" +
                "  \"noSignalPolicy\": {\n" +
                "    \"value\": \"BLACK_FRAME\"\n" +
                "  },\n" +
                "  \"defaultExternalImageURI\": \"default/external/image.jpg\"\n" +
                "}"), mapper.readTree(json));

        BackgroundTransferGroup backgroundTransferGroupFromJSON = mapper.readValue(json, BackgroundTransferGroup.class);
        assertNotNull(backgroundTransferGroupFromJSON);
        assertEquals(backgroundTransferGroup, backgroundTransferGroupFromJSON);

    }

    @Test
    public void toJSON() throws JsonProcessingException {

        BackgroundTransferGroup backgroundTransferGroup = BackgroundTransferGroup.builder()
                .temporaryFolder("temporary/folder")
                .streamingBitRate(StreamingBitRate.builder()
                        .options(new HashSet<>(Arrays.asList(1000, 2000, 5000)))
                        .defaultValue(1000)
                        .selectedValue(1000)
                        .build())
                .noSignalPolicy(NoSignalPolicy.builder()
                        .options(new HashSet<>(Arrays.asList("BLACK_FRAME", "LAST_FRAME", "EXTERNAL_IMAGE")))
                        .defaultValue("BLACK_FRAME")
                        .selectedValue("BLACK_FRAME")
                        .build())
                .externalImageURI("external/image.jpg")
                .defaultExternalImageURI("default/external/image.jpg")
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(backgroundTransferGroup);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\n" +
                "  \"temporaryFolder\": \"temporary/folder\",\n" +
                "  \"streamingEnabled\": false,\n" +
                "  \"streamingBitRate\": {\n" +
                "    \"options\": [\n" +
                "      2000,\n" +
                "      1000,\n" +
                "      5000\n" +
                "    ],\n" +
                "    \"value\": 1000,\n" +
                "    \"default\": 1000\n" +
                "  },\n" +
                "  \"noSignalPolicy\": {\n" +
                "    \"options\": [\n" +
                "      \"LAST_FRAME\",\n" +
                "      \"EXTERNAL_IMAGE\",\n" +
                "      \"BLACK_FRAME\"\n" +
                "    ],\n" +
                "    \"value\": \"BLACK_FRAME\",\n" +
                "    \"default\": \"BLACK_FRAME\"\n" +
                "  },\n" +
                "  \"externalImageURI\": \"external/image.jpg\",\n" +
                "  \"defaultExternalImageURI\": \"default/external/image.jpg\"\n" +
                "}"), mapper.readTree(json));

        BackgroundTransferGroup backgroundTransferGroupFromJSON = mapper.readValue(json, BackgroundTransferGroup.class);
        assertNotNull(backgroundTransferGroupFromJSON);
        assertEquals(backgroundTransferGroup, backgroundTransferGroupFromJSON);

    }

}