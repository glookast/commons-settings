package com.glookast.commons.settings.groups.processes_settings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessesSettingsGroup {

    public enum PlayoutResolution {
        RESOLUTION_1("Resolution #1"),
        RESOLUTION_2("Resolution #2");

        private final String resolution;

        PlayoutResolution(String value) {
            resolution = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return resolution;
        }
    }


    @Builder.Default
    @JsonProperty("SDIPlayerEnabled")
    private Boolean sdiPlayerEnabled = false;

    @Builder.Default
    private Boolean serialControlServiceEnabled = false;

    @Builder.Default
    @JsonProperty("VTRControllerEnabled")
    private Boolean vtrControllerEnabled = false;

    @Builder.Default
    @JsonProperty("selectableOnGuiEnabled")
    private Boolean selectableOnGuiEnabled = true;

    @Builder.Default
    @JsonProperty("defaultPlayoutResolution")
    private PlayoutResolution defaultPlayoutResolution = PlayoutResolution.RESOLUTION_1;
}
