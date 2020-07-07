package com.glookast.commons.settings.groups.metadata;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetadataSettingsGroup {

    @NonNull
    @Builder.Default
    private Set<MetadataField> fields = new HashSet<>();

    @Builder.Default
    private Set<MetadataField> defaultFields = new HashSet<>();

    @Builder.Default
    private Boolean xmlExportEnabled = false;

    // THIS PATH POINTS TO A FILE ON THE TARGET, NOT THE SOURCE
    // This means a remote file browser will have to be used to browse files on the service
    // Alternatively we could also support uploading a XSLT to the service
    private Path xmlExportXSLTFilePath;

}
