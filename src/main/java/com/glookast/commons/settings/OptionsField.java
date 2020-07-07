package com.glookast.commons.settings;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class OptionsField<T> {

    @JsonProperty("selected")
    @NonNull
    public T selectedValue;

    @JsonProperty("default")
    public T defaultValue;

    public Set<T> options;

}
