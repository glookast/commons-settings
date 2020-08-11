package com.glookast.commons.settings.groups.service_settings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceSettingsChannelConfiguration implements Comparable<ServiceSettingsChannelConfiguration> {

    @NonNull
    private Integer channel;

    @NonNull
    private ProcessorConfiguration captureConfiguration;

    @NonNull
    private ProcessorConfiguration ingestConfiguration;

    @Builder.Default
    private Integer ingestReadingSpeedFactor = 0;

    @Builder.Default
    private Boolean appliesToAllChannels = false;

    @Override
    public int compareTo(ServiceSettingsChannelConfiguration o) {
        return Integer.compare(this.channel, o.channel);
    }

}
