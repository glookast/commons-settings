package com.glookast.commons.settings.groups.general;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LanguagePreferenceTest {

    @Test
    public void fromJSON_required() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        LanguagePreference languagePreference = mapper
                .readValue("{\"value\":\"some language\"}", LanguagePreference.class);
        assertNotNull(languagePreference);
        assertEquals("some language", languagePreference.selectedValue.toString());

    }

    @Test
    public void fromJSON() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        LanguagePreference languagePreference = mapper.readValue("{\n" +
                "      \"options\": [\n" +
                "        \"A\",\n" +
                "        \"B\",\n" +
                "        \"C\"\n" +
                "      ],\n" +
                "      \"value\": \"some language\",\n" +
                "      \"default\": \"Z\"\n" +
                "    }", LanguagePreference.class);
        assertNotNull(languagePreference);
        assertEquals("some language", languagePreference.selectedValue.toString());
        assertEquals(new HashSet<>(
                        Arrays.asList(
                                new Locale("A"),
                                new Locale("B"),
                                new Locale("C"))),
                languagePreference.options);
        assertEquals(new Locale("Z"), languagePreference.defaultValue);

    }

    @Test
    public void toJSON_required() throws JsonProcessingException {

        LanguagePreference languagePreference = LanguagePreference.builder()
                .selectedValue(new Locale("some language"))
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(languagePreference);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\"value\":\"some language\"}"), mapper.readTree(json));

        LanguagePreference settingsFromJSON = mapper.readValue(json, LanguagePreference.class);
        assertNotNull(settingsFromJSON);
        assertEquals(languagePreference, settingsFromJSON);

    }

    @Test
    public void toJSON_include_options() throws JsonProcessingException {

        LanguagePreference languagePreference = LanguagePreference.builder()
                .selectedValue(new Locale("some language"))
                .options(new HashSet<>(Arrays.asList(new Locale("A"), new Locale("B"), new Locale("C"))))
                .defaultValue(new Locale("Z"))
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(languagePreference);
        assertNotNull(json);

        LanguagePreference settingsFromJSON = mapper.readValue(json, LanguagePreference.class);
        assertNotNull(settingsFromJSON);
        assertEquals(languagePreference, settingsFromJSON);

    }

}