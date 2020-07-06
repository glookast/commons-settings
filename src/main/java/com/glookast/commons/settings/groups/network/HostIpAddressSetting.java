package com.glookast.commons.settings.groups.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class HostIpAddressSetting {

    @Builder.Default
    private String selected = "127.0.0.1";

    @Builder.Default
    private List<String> options = Collections.singletonList("127.0.0.1");

}
