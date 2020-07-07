package com.glookast.commons.settings.groups.metadata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MetadataSettingsGroupTest {

    @Test
    public void toJSON_minimal() throws JsonProcessingException {

        MetadataSettingsGroup metadataSettingsGroup = MetadataSettingsGroup.builder().build();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(metadataSettingsGroup);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\"fields\":[],\"defaultFields\":[],\"xmlExportEnabled\":false}"), mapper.readTree(json));

        MetadataSettingsGroup settingsFromJSON = mapper.readValue(json, MetadataSettingsGroup.class);
        assertNotNull(settingsFromJSON);
        assertEquals(metadataSettingsGroup, settingsFromJSON);

    }

    @Test
    public void toJSON() throws JsonProcessingException {

        Set<MetadataField> metadataFields = new HashSet<>();

        metadataFields.add(MetadataField.builder()
                .id(1)
                .name("Scene")
                .displayName("Scene")
                .type(MetadataFieldType.builder().selectedValue("Text box").build())
                .build());
        metadataFields.add(MetadataField.builder()
                .id(2)
                .name("Scene2")
                .displayName("Scene2")
                .type(
                        MetadataFieldType.builder()
                                .selectedValue("Dropdown")
                                .options(new HashSet<>(Arrays.asList("Dropdown", "Text area", "Text box")))
                                .build())
                .options("A; B; C")
                .build());

        Set<MetadataField> defaultMetadataFields = new HashSet<>();

        defaultMetadataFields.add(MetadataField.builder()
                .id(1)
                .name("Default")
                .displayName("Default")
                .type(MetadataFieldType.builder().selectedValue("Dropdown").build())
                .build());
        defaultMetadataFields.add(MetadataField.builder()
                .id(2)
                .name("Default2")
                .displayName("Default2")
                .type(MetadataFieldType.builder().selectedValue("Text box").build())
                .build());

        MetadataSettingsGroup metadataSettingsGroup = MetadataSettingsGroup.builder()
                .fields(metadataFields)
                .defaultFields(defaultMetadataFields)
                .xmlExportEnabled(true)
                .xmlExportXSLTFilePath(Paths.get("/", "path", "to", "a", "file"))
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(metadataSettingsGroup);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\n" +
                "  \"fields\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Scene\",\n" +
                "      \"displayName\": \"Scene\",\n" +
                "      \"type\": {\n" +
                "        \"value\": \"Text box\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"Scene2\",\n" +
                "      \"displayName\": \"Scene2\",\n" +
                "      \"type\": {\n" +
                "        \"options\": [\n" +
                "          \"Dropdown\",\n" +
                "          \"Text box\",\n" +
                "          \"Text area\"\n" +
                "        ],\n" +
                "        \"value\": \"Dropdown\"\n" +
                "      },\n" +
                "      \"options\": \"A; B; C\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"defaultFields\": [\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"Default2\",\n" +
                "      \"displayName\": \"Default2\",\n" +
                "      \"type\": {\n" +
                "        \"value\": \"Text box\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Default\",\n" +
                "      \"displayName\": \"Default\",\n" +
                "      \"type\": {\n" +
                "        \"value\": \"Dropdown\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"xmlExportEnabled\": true,\n" +
                "  \"xmlExportXSLTFilePath\": \"file:///path/to/a/file\"\n" +
                "}"), mapper.readTree(json));

        MetadataSettingsGroup settingsFromJSON = mapper.readValue(json, MetadataSettingsGroup.class);
        assertNotNull(settingsFromJSON);
        assertEquals(metadataSettingsGroup, settingsFromJSON);

    }

}