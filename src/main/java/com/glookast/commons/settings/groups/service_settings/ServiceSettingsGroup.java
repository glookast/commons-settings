package com.glookast.commons.settings.groups.service_settings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.SortedSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceSettingsGroup {

    @NonNull
    private SortedSet<ServiceSettingsChannelConfiguration> channelConfigurations;

    @Setter(AccessLevel.NONE)
    @NonNull
    private Integer numberOfCPUs;

}
