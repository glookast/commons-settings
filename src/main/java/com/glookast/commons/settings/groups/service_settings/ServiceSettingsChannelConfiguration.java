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

    // no channel set means this is a global configuration
    private Integer channel;

    @NonNull
    private ProcessorConfiguration captureConfiguration;

    @NonNull
    private ProcessorConfiguration ingestConfiguration;

    @Builder.Default
    private Integer ingestReadingSpeedFactor = 0;

    @Override
    public int compareTo(ServiceSettingsChannelConfiguration o) {
        if (this.channel != null && o.channel != null) {
            return Integer.compare(this.channel, o.channel);
        }
        return 0;
    }

}
