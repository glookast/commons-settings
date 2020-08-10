package com.glookast.commons.settings.groups.background_transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BackgroundTransferGroup {

    @NonNull
    private String temporaryFolder;

    @Builder.Default
    private Boolean streamingEnabled = false;

    @NonNull
    private StreamingBitRate streamingBitRate;

    @NonNull
    private NoSignalPolicy noSignalPolicy;

    private String externalImageURI;

    @NonNull
    private String defaultExternalImageURI;

}
