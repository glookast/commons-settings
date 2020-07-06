package com.glookast.commons.settings.groups.general;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class GeneralSettingsGroup {

    @Builder.Default
    private LanguageSetting language = LanguageSetting.builder().build();

}
