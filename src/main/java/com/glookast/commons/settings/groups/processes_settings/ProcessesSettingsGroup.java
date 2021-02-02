package com.glookast.commons.settings.groups.processes_settings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    public enum PlayoutResolutions {
        RESOLUTION_1(1),
        RESOLUTION_2(2);

        private String resId;

        PlayoutResolutions(int res) {
            resId = Integer.toString(res);
        }

        @Override
        public String toString() {
            return "Resolution #" + resId;
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
    private String defaultPlayoutResolution = PlayoutResolutions.RESOLUTION_1.toString();
}
