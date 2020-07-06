package com.glookast.commons.settings.groups.general;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonDeserialize(using = LanguageSettingDeserializer.class)
public class LanguageSetting {

    @Builder.Default
    private Option selected = Option.EN_GB;

    private final List<String> options = Arrays.stream(Option.values()).map(Enum::toString).collect(Collectors.toList());

    public enum Option {

        EN_GB(new Locale.Builder().setLanguage("en").setRegion("GB").build()),
        PT_PT(new Locale.Builder().setLanguage("pt").setRegion("PT").build()),
        JA_JP(new Locale.Builder().setLanguage("jp").setRegion("JA").build());

        public final Locale locale;

        Option(Locale locale) {
            this.locale = locale;
        }

    }

}
