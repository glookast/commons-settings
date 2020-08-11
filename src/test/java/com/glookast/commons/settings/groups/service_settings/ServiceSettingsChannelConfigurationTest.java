package com.glookast.commons.settings.groups.service_settings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ServiceSettingsChannelConfigurationTest {

    @Test
    public void test() throws JsonProcessingException {

        ServiceSettingsChannelConfiguration config = ServiceSettingsChannelConfiguration.builder()
                .channel(1)
                .captureConfiguration(ProcessorConfiguration.builder()
                        .groupAffinity(GroupAffinity.builder()
                                .selectedValue("Group 0")
                                .build())
                        .priority(ProcessorPriority.builder()
                                .selectedValue("NORMAL")
                                .build())
                        .build())
                .ingestConfiguration(ProcessorConfiguration.builder()
                        .groupAffinity(GroupAffinity.builder()
                                .selectedValue("Group 0")
                                .build())
                        .priority(ProcessorPriority.builder()
                                .selectedValue("NORMAL")
                                .build())
                        .build())
                .build();

        assertNotNull(config);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(config);
        assertNotNull(json);

    }

}