package com.glookast.commons.settings;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.glookast.commons.settings.groups.general.GeneralSettingsGroup;
import com.glookast.commons.settings.groups.network.NetworkSettingsGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Settings {

    @Builder.Default
    private GeneralSettingsGroup general = GeneralSettingsGroup.builder().build();

    @Builder.Default
    private NetworkSettingsGroup network = NetworkSettingsGroup.builder().build();

}
