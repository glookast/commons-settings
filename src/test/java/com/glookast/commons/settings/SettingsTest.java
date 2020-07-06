package com.glookast.commons.settings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glookast.commons.settings.groups.general.LanguageSetting;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SettingsTest {

    @Test
    public void toJSON() throws JsonProcessingException {

        Settings settings = Settings.builder().build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(settings);
        assertNotNull(json);

        JsonNode jsonNode = mapper.readTree("{\n" +
                "  \"general\": {\n" +
                "    \"language\": {\n" +
                "      \"selected\": \"EN_GB\",\n" +
                "      \"options\": [\n" +
                "        \"EN_GB\",\n" +
                "        \"PT_PT\",\n" +
                "        \"JA_JP\"\n" +
                "      ]\n" +
                "    }\n" +
                "  },\n" +
                "  \"network\": {\n" +
                "    \"hostIpAddress\": {\n" +
                "      \"selected\": \"127.0.0.1\",\n" +
                "      \"options\": [\n" +
                "        \"127.0.0.1\"\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}");
        assertEquals(mapper.readTree(json), mapper.readTree(String.valueOf(jsonNode)));

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
                "      \"selected\": \"ja_JP\",\n" +
                "      \"options\": [\n" +
                "        \"EN_GB\",\n" +
                "        \"PT_PT\",\n" +
                "        \"JA_JP\"\n" +
                "      ]\n" +
                "    }\n" +
                "  },\n" +
                "  \"network\": {\n" +
                "    \"hostIpAddress\": {\n" +
                "      \"selected\": \"127.0.0.1\",\n" +
                "      \"options\": [\n" +
                "        \"127.0.0.1\"\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        Settings settings = mapper.readValue(json, Settings.class);
        assertNotNull(settings);
        assertEquals(settings.getGeneral().getLanguage().getSelected(), LanguageSetting.Option.JA_JP);

    }

}