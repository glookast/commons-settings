package com.glookast.commons.settings.groups.media_processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MediaProcessorGroupTest {

    @Test
    public void toJSON_minimal() throws JsonProcessingException {

        MediaProcessorGroup mediaProcessorGroup = MediaProcessorGroup.builder()
                .mp4MuxerCompatibilityMode(MP4MuxerCompatibilityMode.builder()
                        .selectedValue("STANDARD")
                        .build())
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(mediaProcessorGroup);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\n" +
                "  \"enabledFragmentedMP4\": false,\n" +
                "  \"userUMIDInMXForMP4OutputFilename\": false,\n" +
                "  \"aggregateAllMP4AudioChannels\": false,\n" +
                "  \"refreshPeriodInSecondsForMOVGrowing\": 0,\n" +
                "  \"MP4MuxerCompatibilityMode\": {\n" +
                "    \"value\": \"STANDARD\"\n" +
                "  }\n" +
                "}"), mapper.readTree(json));

        MediaProcessorGroup mediaProcessorGroupFromJSON = mapper.readValue(json, MediaProcessorGroup.class);
        assertNotNull(mediaProcessorGroupFromJSON);
        assertEquals(mediaProcessorGroup, mediaProcessorGroupFromJSON);

    }

    @Test
    public void toJSON() throws JsonProcessingException {

        MediaProcessorGroup mediaProcessorGroup = MediaProcessorGroup.builder()
                .enabledFragmentedMP4(true)
                .mp4MuxerCompatibilityMode(MP4MuxerCompatibilityMode.builder()
                        .options(new HashSet<>(Arrays.asList("STANDARD", "SONY_PSP")))
                        .selectedValue("STANDARD")
                        .defaultValue("STANDARD")
                        .build())
                .userUMIDInMXForMP4OutputFilename(true)
                .aggregateAllMP4AudioChannels(true)
                .refreshPeriodInSecondsForMOVGrowing(10)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(mediaProcessorGroup);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\n" +
                "  \"enabledFragmentedMP4\": true,\n" +
                "  \"userUMIDInMXForMP4OutputFilename\": true,\n" +
                "  \"aggregateAllMP4AudioChannels\": true,\n" +
                "  \"refreshPeriodInSecondsForMOVGrowing\": 10,\n" +
                "  \"MP4MuxerCompatibilityMode\": {\n" +
                "    \"options\": [\n" +
                "      \"STANDARD\",\n" +
                "      \"SONY_PSP\"\n" +
                "    ],\n" +
                "    \"value\": \"STANDARD\",\n" +
                "    \"default\": \"STANDARD\"\n" +
                "  }\n" +
                "}"), mapper.readTree(json));

        MediaProcessorGroup mediaProcessorGroupFromJSON = mapper.readValue(json, MediaProcessorGroup.class);
        assertNotNull(mediaProcessorGroupFromJSON);
        assertEquals(mediaProcessorGroup, mediaProcessorGroupFromJSON);

    }

}