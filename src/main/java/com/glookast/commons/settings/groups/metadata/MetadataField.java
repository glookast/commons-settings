package com.glookast.commons.settings.groups.metadata;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetadataField {

    @NonNull
    Integer id;

    @NonNull
    String name;

    @NonNull
    String displayName;

    @NonNull
    MetadataFieldType type;

    String options;

}
