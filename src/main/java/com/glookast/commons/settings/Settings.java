package com.glookast.commons.settings;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.glookast.commons.settings.groups.capture.CaptureSettingsGroup;
import com.glookast.commons.settings.groups.general.GeneralSettingsGroup;
import com.glookast.commons.settings.groups.metadata.MetadataSettingsGroup;
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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, defaultImpl = Settings.class, property = "type")
public class Settings {

    private GeneralSettingsGroup general;

    private MetadataSettingsGroup metadata;

    private NetworkSettingsGroup network;

    private CaptureSettingsGroup capture;

}
