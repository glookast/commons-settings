package com.glookast.commons.settings.groups.general;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Locale;

public class LanguageSettingDeserializer extends StdDeserializer<LanguageSetting> {

    public LanguageSettingDeserializer() {
        this(null);
    }

    public LanguageSettingDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    // TODO This needs some SERIOUS study on localization...
    public LanguageSetting deserialize(JsonParser parser, DeserializationContext context) throws IOException {

        JsonNode node = parser.getCodec().readTree(parser);
        String[] ISO3LanguageParts = node.get("selected").asText().split("_");

        try {
            Locale locale = new Locale(ISO3LanguageParts[1], ISO3LanguageParts[0]);
            for (LanguageSetting.Option option : LanguageSetting.Option.values()) {
                if (option.locale.toString().equals(locale.toString())) {
                    return LanguageSetting.builder().selected(option).build();
                }
            }
        } catch (Exception ex) {
            // default to en_GB
            // log.warn("Failed to parse language option, reverting to en_GB default");
        }
        return LanguageSetting.builder().build();
    }

}
