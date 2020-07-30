package com.glookast.commons.settings.groups.loop_record;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoopRecordChannelConfiguration implements Comparable<LoopRecordChannelConfiguration> {

    @NonNull
    private Integer channel;

    @NonNull
    private Boolean enabled;

    @NonNull
    private UUID videoFormatId;

    @NonNull
    private LoopRecordDuration maxDuration;

    @Override
    public int compareTo(LoopRecordChannelConfiguration o) {
        return Integer.compare(this.channel, o.channel);
    }
}
