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