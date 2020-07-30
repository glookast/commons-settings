package com.glookast.commons.settings.groups.capture;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptureSettingsGroup {

    @NonNull
    private CaptureCard captureCard;

    @NonNull
    private ChannelCount numberOfChannels;

    @NonNull
    @JsonProperty("LTCTimecodeSource")
    private LTCTimecodeSource LTCTimecodeSource;

    @NonNull
    private Integer gangRollControlDelayInFrames;

    @NonNull
    @Builder.Default
    private Boolean transcodeSecondaryResolutionsWhileIngesting = false;

    @NonNull
    private Integer headFrameOffset;

    @NonNull
    @JsonProperty("AJA4kInputMode")
    private AJA4kInputMode AJA4kInputMode;

    private Set<ChannelInput> channelInputs;

}
