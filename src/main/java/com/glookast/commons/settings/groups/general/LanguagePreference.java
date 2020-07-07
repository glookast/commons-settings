package com.glookast.commons.settings.groups.general;

import com.glookast.commons.settings.OptionsField;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Locale;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder

// This SHOULD conform to IETF BCP 47 language tag
// https://www.iana.org/assignments/language-subtag-registry/language-subtag-registry
// https://en.wikipedia.org/wiki/IETF_language_tag
public class LanguagePreference extends OptionsField<Locale> {


}
