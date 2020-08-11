package com.glookast.commons.settings.groups.service_settings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Collections;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessorConfiguration {

    @NonNull
    private GroupAffinity groupAffinity;

    @Builder.Default
    private Set<Integer> selectedCPUs = Collections.emptySet();

    @Setter(AccessLevel.NONE)
    private String processorAffinityMask;

    @NonNull
    private ProcessorPriority priority;

}
