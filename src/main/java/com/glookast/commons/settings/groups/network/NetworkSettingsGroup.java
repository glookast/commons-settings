package com.glookast.commons.settings.groups.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class NetworkSettingsGroup {

    @Builder.Default
    private HostIpAddressSetting hostIpAddress = HostIpAddressSetting.builder().build();

}
