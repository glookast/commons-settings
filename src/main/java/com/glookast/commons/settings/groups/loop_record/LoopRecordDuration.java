package com.glookast.commons.settings.groups.loop_record;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoopRecordDuration {

    @NonNull
    private Integer value;

    @Builder.Default
    private ChronoUnit unit = ChronoUnit.HOURS;

    /**
     * Helper method to get the duration of the loop record buffer
     *
     * @return duration
     */
    @JsonIgnore
    public Duration getDuration() {
        return Duration.of(this.value, this.unit);
    }

}
