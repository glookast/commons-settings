package com.glookast.commons.settings.groups.general;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.jqwik.api.*;
import org.junit.jupiter.api.Assertions;

public class LanguagePreferencePBTest {

    @Provide
    Arbitrary<String> randomString() {
        return Arbitraries.strings().all().unique().injectNull(0.25);
    }

    @Provide
    Arbitrary<String> randomAlphaString() {
        return Arbitraries.strings().alpha().unique().injectNull(0.25);
    }

    @Property
    public void shouldFailOnInvalidJSON(@ForAll("randomString") String value) {

        ObjectMapper mapper = new ObjectMapper();
        Assertions.assertThrows(Exception.class, () -> {
            mapper.readValue(value, LanguagePreference.class);
        });

    }

    @Property
    public void shouldNOTFailOnInvalidJSONProperties(@ForAll("randomAlphaString") String property,
                                                     @ForAll("randomAlphaString") String value) {

        ObjectMapper mapper = new ObjectMapper();
        Assertions.assertDoesNotThrow(() -> {
            String json = "{\n" +
                    "      \"options\": [\n" +
                    "        \"A\",\n" +
                    "        \"B\",\n" +
                    "        \"C\"\n" +
                    "      ],\n" +
                    "      \"value\": \"" + value + "\",\n" +
                    "      \"" + property + "\": \"" + value + "\"\n" +
                    "    }";
            mapper.readValue(json, LanguagePreference.class);
        });

    }

}
