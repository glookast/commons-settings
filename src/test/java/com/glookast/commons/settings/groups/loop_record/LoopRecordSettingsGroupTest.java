package com.glookast.commons.settings.groups.loop_record;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LoopRecordSettingsGroupTest {

    @Test
    public void toJSON_defaults() throws JsonProcessingException {

        LoopRecordSettingsGroup loopRecordSettingsGroup = LoopRecordSettingsGroup.builder().build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(loopRecordSettingsGroup);
        assertNotNull(json);

        assertEquals(mapper.readTree("{}"), mapper.readTree(json));

        LoopRecordSettingsGroup settingsFromJSON = mapper.readValue(json, LoopRecordSettingsGroup.class);
        assertNotNull(settingsFromJSON);
        assertEquals(loopRecordSettingsGroup, settingsFromJSON);

    }

    @Test
    public void toJSON_including_channel_configurations() throws JsonProcessingException {

        UUID channelAVideoFormatId = UUID.randomUUID();
        UUID channelBVideoFormatId = UUID.randomUUID();

        LoopRecordSettingsGroup loopRecordSettingsGroup = LoopRecordSettingsGroup.builder()
                .channelConfigurations(
                        new TreeSet<>(
                                Arrays.asList(
                                        LoopRecordChannelConfiguration.builder()
                                                .channel(1)
                                                .enabled(true)
                                                .videoFormatId(channelAVideoFormatId)
                                                .maxDuration(LoopRecordDuration.builder()
                                                        .value(1)
                                                        .build()
                                                ).build(),
                                        LoopRecordChannelConfiguration.builder()
                                                .channel(2)
                                                .enabled(false)
                                                .videoFormatId(channelBVideoFormatId)
                                                .maxDuration(LoopRecordDuration.builder()
                                                        .value(30)
                                                        .unit(ChronoUnit.MINUTES)
                                                        .build()
                                                ).build()
                                )))
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(loopRecordSettingsGroup);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\n" +
                "  \"channelConfigurations\": [\n" +
                "    {\n" +
                "      \"channel\": 1,\n" +
                "      \"enabled\": true,\n" +
                "      \"videoFormatId\": \"" + channelAVideoFormatId + "\",\n" +
                "      \"maxDuration\": {\n" +
                "        \"value\": 1,\n" +
                "        \"unit\": \"HOURS\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"channel\": 2,\n" +
                "      \"enabled\": false,\n" +
                "      \"videoFormatId\": \"" + channelBVideoFormatId + "\",\n" +
                "      \"maxDuration\": {\n" +
                "        \"value\": 30,\n" +
                "        \"unit\": \"MINUTES\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}"), mapper.readTree(json));

        LoopRecordSettingsGroup settingsFromJSON = mapper.readValue(json, LoopRecordSettingsGroup.class);
        assertNotNull(settingsFromJSON);
        assertEquals(loopRecordSettingsGroup, settingsFromJSON);

    }

}