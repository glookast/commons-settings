package com.glookast.commons.settings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glookast.commons.settings.groups.background_transfer.BackgroundTransferGroup;
import com.glookast.commons.settings.groups.background_transfer.NoSignalPolicy;
import com.glookast.commons.settings.groups.background_transfer.StreamingBitRate;
import com.glookast.commons.settings.groups.media_processor.MP4MuxerCompatibilityMode;
import com.glookast.commons.settings.groups.media_processor.MediaProcessorGroup;
import com.glookast.commons.settings.groups.processes_settings.ProcessesSettingsGroup;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SettingsTest {

    @Test
    public void toJSON_empty_settings() throws JsonProcessingException {

        Settings settings = Settings.builder().build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(settings);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\"type\":\"Settings\"}"), mapper.readTree(json));

        // parse the resulting JSON into equivalent settings object
        Settings settingsFromJSON = mapper.readValue(json, Settings.class);
        assertNotNull(settingsFromJSON);
        assertEquals(settings, settingsFromJSON);

    }

    @Test
    public void fromEmptyJSON() throws JsonProcessingException {

        String json = "{}";
        ObjectMapper mapper = new ObjectMapper();
        Settings settings = mapper.readValue(json, Settings.class);
        assertNotNull(settings);

    }

    @Test
    public void fromJSON() throws JsonProcessingException {

        String json = "{\n" +
                "  \"general\": {\n" +
                "    \"language\": {\n" +
                "      \"value\": \"ja\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"network\": {\n" +
                "    \"hostIpAddress\": {\n" +
                "      \"value\": \"192.168.5.128\",\n" +
                "      \"default\": \"127.0.0.1\",\n" +
                "      \"options\": [\n" +
                "        \"127.0.0.1\",\n" +
                "        \"192.168.5.128\"\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        Settings settings = mapper.readValue(json, Settings.class);
        assertNotNull(settings);
        assertEquals(new Locale.Builder().setLanguage("ja").build(), settings.getGeneral().getLanguage().getSelectedValue());
        assertEquals("192.168.5.128", settings.getNetwork().getHostIpAddress().getSelectedValue());
        assertEquals("127.0.0.1", settings.getNetwork().getHostIpAddress().getDefaultValue());
        assertEquals(new HashSet<>(Arrays.asList("127.0.0.1", "192.168.5.128")), settings.getNetwork().getHostIpAddress().getOptions());

    }

    @Test
    public void toJSON_include_background_transfer() throws JsonProcessingException {

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

        Settings settings = Settings.builder()
                .backgroundTransfer(backgroundTransferGroup)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(settings);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\n" +
                "  \"type\": \"Settings\",\n" +
                "  \"backgroundTransfer\": {\n" +
                "    \"temporaryFolder\": \"temporary/folder\",\n" +
                "    \"streamingEnabled\": false,\n" +
                "    \"streamingBitRate\": {\n" +
                "      \"options\": [\n" +
                "        2000,\n" +
                "        1000,\n" +
                "        5000\n" +
                "      ],\n" +
                "      \"value\": 1000,\n" +
                "      \"default\": 1000\n" +
                "    },\n" +
                "    \"noSignalPolicy\": {\n" +
                "      \"options\": [\n" +
                "        \"LAST_FRAME\",\n" +
                "        \"EXTERNAL_IMAGE\",\n" +
                "        \"BLACK_FRAME\"\n" +
                "      ],\n" +
                "      \"value\": \"BLACK_FRAME\",\n" +
                "      \"default\": \"BLACK_FRAME\"\n" +
                "    },\n" +
                "    \"externalImageURI\": \"external/image.jpg\",\n" +
                "    \"defaultExternalImageURI\": \"default/external/image.jpg\"\n" +
                "  }\n" +
                "}"), mapper.readTree(json));

        Settings settingsFromJSON = mapper.readValue(json, Settings.class);
        assertNotNull(settingsFromJSON);
        assertEquals(settings, settingsFromJSON);

    }

    @Test
    public void toJSON_include_media_processor() throws JsonProcessingException {

        MediaProcessorGroup mediaProcessorGroup = MediaProcessorGroup.builder()
                .fragmentedMP4Enabled(true)
                .mp4MuxerCompatibilityMode(MP4MuxerCompatibilityMode.builder()
                        .options(new HashSet<>(Arrays.asList("STANDARD", "SONY_PSP")))
                        .selectedValue("STANDARD")
                        .defaultValue("STANDARD")
                        .build())
                .userUMIDInMXForMP4OutputFilenameEnabled(true)
                .aggregateAllMP4AudioChannelsEnabled(true)
                .refreshPeriodInSecondsForMOVGrowing(10)
                .build();

        Settings settings = Settings.builder()
                .mediaProcessor(mediaProcessorGroup)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(settings);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\n" +
                "  \"type\": \"Settings\",\n" +
                "  \"mediaProcessor\": {\n" +
                "    \"fragmentedMP4Enabled\": true,\n" +
                "    \"userUMIDInMXForMP4OutputFilenameEnabled\": true,\n" +
                "    \"aggregateAllMP4AudioChannelsEnabled\": true,\n" +
                "    \"refreshPeriodInSecondsForMOVGrowing\": 10,\n" +
                "    \"MP4MuxerCompatibilityMode\": {\n" +
                "      \"options\": [\n" +
                "        \"STANDARD\",\n" +
                "        \"SONY_PSP\"\n" +
                "      ],\n" +
                "      \"value\": \"STANDARD\",\n" +
                "      \"default\": \"STANDARD\"\n" +
                "    }\n" +
                "  }\n" +
                "}"), mapper.readTree(json));

        Settings settingsFromJSON = mapper.readValue(json, Settings.class);
        assertNotNull(settingsFromJSON);
        assertEquals(settings, settingsFromJSON);

    }

    @Test
    public void toJSON_include_processes_settings() throws JsonProcessingException {

        ProcessesSettingsGroup processesSettingsGroup = ProcessesSettingsGroup.builder()
                .serialControlServiceEnabled(true)
                .sdiPlayerEnabled(true)
                .vtrControllerEnabled(true)
                .build();

        Settings settings = Settings.builder()
                .processesSettings(processesSettingsGroup)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(settings);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\n" +
                "  \"type\": \"Settings\",\n" +
                "  \"processesSettings\": {\n" +
                "    \"serialControlServiceEnabled\": true,\n" +
                "    \"SDIPlayerEnabled\": true,\n" +
                "    \"VTRControllerEnabled\": true\n" +
                "  }\n" +
                "}"), mapper.readTree(json));

        Settings settingsFromJSON = mapper.readValue(json, Settings.class);
        assertNotNull(settingsFromJSON);
        assertEquals(settings, settingsFromJSON);

    }

}