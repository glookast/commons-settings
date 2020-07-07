package com.glookast.commons.settings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
                "      \"selected\": \"ja\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"network\": {\n" +
                "    \"hostIpAddress\": {\n" +
                "      \"selected\": \"192.168.5.128\",\n" +
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

}