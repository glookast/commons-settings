package com.glookast.commons.settings.groups.capture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CaptureSettingsGroupTest {

    @Test
    public void toJSON_minimal() throws JsonProcessingException {

        CaptureSettingsGroup captureSettingsGroup = CaptureSettingsGroup.builder()
                .captureCard(CaptureCard.builder().selectedValue("Physical").build())
                .numberOfChannels(ChannelCount.builder().selectedValue(1).build())
                .LTCTimecodeSource(LTCTimecodeSource.builder().selectedValue("SDI Signal").build())
                .gangRollControlDelayInFrames(40)
                .headFrameOffset(1)
                .AJA4kInputMode(AJA4kInputMode.builder().selectedValue("Square").build())
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(captureSettingsGroup);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\n" +
                "  \"captureCard\": {\n" +
                "    \"value\": \"Physical\"\n" +
                "  },\n" +
                "  \"numberOfChannels\": {\n" +
                "    \"value\": 1\n" +
                "  },\n" +
                "  \"gangRollControlDelayInFrames\": 40,\n" +
                "  \"transcodeSecondaryResolutionsWhileIngesting\": false,\n" +
                "  \"headFrameOffset\": 1,\n" +
                "  \"LTCTimecodeSource\": {\n" +
                "    \"value\": \"SDI Signal\"\n" +
                "  },\n" +
                "  \"AJA4kInputMode\": {\n" +
                "    \"value\": \"Square\"\n" +
                "  }\n" +
                "}"), mapper.readTree(json));

        CaptureSettingsGroup settingsFromJSON = mapper.readValue(json, CaptureSettingsGroup.class);
        assertNotNull(settingsFromJSON);
        assertEquals(captureSettingsGroup, settingsFromJSON);

    }

    @Test
    public void toJSON_includingChannelInputs() throws JsonProcessingException {

        CaptureSettingsGroup captureSettingsGroup = CaptureSettingsGroup.builder()
                .captureCard(CaptureCard.builder().selectedValue("Physical").build())
                .numberOfChannels(ChannelCount.builder().selectedValue(1).build())
                .LTCTimecodeSource(LTCTimecodeSource.builder().selectedValue("SDI Signal").build())
                .gangRollControlDelayInFrames(40)
                .headFrameOffset(1)
                .AJA4kInputMode(AJA4kInputMode.builder().selectedValue("Square").build())
                .channelInputs(
                        new HashSet<>(
                                Arrays.asList(
                                        ChannelInput.builder()
                                                .channel(1)
                                                .inputURI("/this/is/a/path")
                                                .build(),
                                        ChannelInput.builder()
                                                .channel(2)
                                                .inputURI("/this/is/another/path")
                                                .build()
                                )))
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(captureSettingsGroup);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\n" +
                "  \"captureCard\": {\n" +
                "    \"value\": \"Physical\"\n" +
                "  },\n" +
                "  \"numberOfChannels\": {\n" +
                "    \"value\": 1\n" +
                "  },\n" +
                "  \"gangRollControlDelayInFrames\": 40,\n" +
                "  \"transcodeSecondaryResolutionsWhileIngesting\": false,\n" +
                "  \"headFrameOffset\": 1,\n" +
                "  \"channelInputs\": [\n" +
                "    {\n" +
                "      \"channel\": 2,\n" +
                "      \"inputURI\": \"/this/is/another/path\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"channel\": 1,\n" +
                "      \"inputURI\": \"/this/is/a/path\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"LTCTimecodeSource\": {\n" +
                "    \"value\": \"SDI Signal\"\n" +
                "  },\n" +
                "  \"AJA4kInputMode\": {\n" +
                "    \"value\": \"Square\"\n" +
                "  }\n" +
                "}"), mapper.readTree(json));

        CaptureSettingsGroup settingsFromJSON = mapper.readValue(json, CaptureSettingsGroup.class);
        assertNotNull(settingsFromJSON);
        assertEquals(captureSettingsGroup, settingsFromJSON);

    }

}