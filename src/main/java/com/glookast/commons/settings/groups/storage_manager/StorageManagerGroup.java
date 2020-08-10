package com.glookast.commons.settings.groups.storage_manager;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StorageManagerGroup {

    @NonNull
    @Builder.Default
    private Boolean fileSystemEventsProcessingEnabled = true;

    @NonNull
    @Builder.Default
    private Integer storageManagerRefreshIntervalInSeconds = 60;

}
