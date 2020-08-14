package com.glookast.commons.settings.groups.service_settings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ServiceSettingsGroupTest {

    @Test
    public void toJSON_minimal() throws JsonProcessingException {

        ServiceSettingsChannelConfiguration channelConfiguration = ServiceSettingsChannelConfiguration.builder()
                .channel(1)
                .captureConfiguration(ProcessorConfiguration.builder()
                        .groupAffinity(GroupAffinity.builder()
                                .selectedValue("Group 0")
                                .build())
                        .processorAffinityMask("Default")
                        .priority(ProcessorPriority.builder()
                                .selectedValue("NORMAL")
                                .build())
                        .build())
                .ingestConfiguration(ProcessorConfiguration.builder()
                        .groupAffinity(GroupAffinity.builder()
                                .selectedValue("Group 0")
                                .build())
                        .processorAffinityMask("Default")
                        .priority(ProcessorPriority.builder()
                                .selectedValue("NORMAL")
                                .build())
                        .build())
                .build();

        ServiceSettingsGroup serviceSettingsGroup = ServiceSettingsGroup.builder()
                .channelConfigurations(new TreeSet<>(Collections.singletonList(
                        channelConfiguration
                )))
                .numberOfCPUs(4)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(serviceSettingsGroup);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\n" +
                "  \"channelConfigurations\": [\n" +
                "    {\n" +
                "      \"channel\": 1,\n" +
                "      \"captureConfiguration\": {\n" +
                "        \"groupAffinity\": {\n" +
                "          \"value\": \"Group 0\"\n" +
                "        },\n" +
                "        \"processorAffinityMask\": \"Default\",\n" +
                "        \"priority\": {\n" +
                "          \"value\": \"NORMAL\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"ingestConfiguration\": {\n" +
                "        \"groupAffinity\": {\n" +
                "          \"value\": \"Group 0\"\n" +
                "        },\n" +
                "        \"processorAffinityMask\": \"Default\",\n" +
                "        \"priority\": {\n" +
                "          \"value\": \"NORMAL\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"ingestReadingSpeedFactor\": 0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"numberOfCPUs\": 4\n" +
                "}"), mapper.readTree(json));

        ServiceSettingsGroup ServiceSettingsGroupFromJSON = mapper.readValue(json, ServiceSettingsGroup.class);
        assertNotNull(ServiceSettingsGroupFromJSON);
        assertEquals(serviceSettingsGroup, ServiceSettingsGroupFromJSON);

    }

    @Test
    public void toJSON_minimal_single_configuration() throws JsonProcessingException {

        ServiceSettingsChannelConfiguration channelConfiguration = ServiceSettingsChannelConfiguration.builder()
                .captureConfiguration(ProcessorConfiguration.builder()
                        .groupAffinity(GroupAffinity.builder()
                                .selectedValue("Group 0")
                                .build())
                        .processorAffinityMask("Default")
                        .priority(ProcessorPriority.builder()
                                .selectedValue("NORMAL")
                                .build())
                        .build())
                .ingestConfiguration(ProcessorConfiguration.builder()
                        .groupAffinity(GroupAffinity.builder()
                                .selectedValue("Group 0")
                                .build())
                        .processorAffinityMask("Default")
                        .priority(ProcessorPriority.builder()
                                .selectedValue("NORMAL")
                                .build())
                        .build())
                .build();

        ServiceSettingsGroup serviceSettingsGroup = ServiceSettingsGroup.builder()
                .channelConfigurations(new TreeSet<>(Collections.singletonList(
                        channelConfiguration
                )))
                .numberOfCPUs(4)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(serviceSettingsGroup);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\n" +
                "  \"channelConfigurations\": [\n" +
                "    {\n" +
                "      \"captureConfiguration\": {\n" +
                "        \"groupAffinity\": {\n" +
                "          \"value\": \"Group 0\"\n" +
                "        },\n" +
                "        \"processorAffinityMask\": \"Default\",\n" +
                "        \"priority\": {\n" +
                "          \"value\": \"NORMAL\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"ingestConfiguration\": {\n" +
                "        \"groupAffinity\": {\n" +
                "          \"value\": \"Group 0\"\n" +
                "        },\n" +
                "        \"processorAffinityMask\": \"Default\",\n" +
                "        \"priority\": {\n" +
                "          \"value\": \"NORMAL\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"ingestReadingSpeedFactor\": 0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"numberOfCPUs\": 4\n" +
                "}"), mapper.readTree(json));

        ServiceSettingsGroup ServiceSettingsGroupFromJSON = mapper.readValue(json, ServiceSettingsGroup.class);
        assertNotNull(ServiceSettingsGroupFromJSON);
        assertEquals(serviceSettingsGroup, ServiceSettingsGroupFromJSON);

    }

    @Test
    public void toJSON_GET() throws JsonProcessingException {

        Set<String> priorityOptions = new LinkedHashSet<>(); // preserve insertion order
        priorityOptions.add("Not Specified");
        priorityOptions.add("REALTIME");
        priorityOptions.add("HIGH");
        priorityOptions.add("ABOVE NORMAL");
        priorityOptions.add("NORMAL");
        priorityOptions.add("BELOW NORMAL");
        priorityOptions.add("IDLE");

        ServiceSettingsChannelConfiguration channelConfiguration = ServiceSettingsChannelConfiguration.builder()
                .channel(1)
                .captureConfiguration(ProcessorConfiguration.builder()
                        .groupAffinity(GroupAffinity.builder()
                                .selectedValue("Group 0")
                                .build())
                        .selectedCPUs(new TreeSet<>(Arrays.asList(1, 3, 5)))
                        .processorAffinityMask("Default")
                        .priority(ProcessorPriority.builder()
                                .options(priorityOptions)
                                .selectedValue("NORMAL")
                                .defaultValue("NORMAL")
                                .build())
                        .build())
                .ingestConfiguration(ProcessorConfiguration.builder()
                        .groupAffinity(GroupAffinity.builder()
                                .selectedValue("Group 0")
                                .build())
                        .selectedCPUs(new TreeSet<>(Arrays.asList(2, 6, 7)))
                        .processorAffinityMask("Default")
                        .priority(ProcessorPriority.builder()
                                .options(priorityOptions)
                                .selectedValue("NORMAL")
                                .defaultValue("NORMAL")
                                .build())
                        .build())
                .build();

        ServiceSettingsGroup serviceSettingsGroup = ServiceSettingsGroup.builder()
                .channelConfigurations(new TreeSet<>(Collections.singletonList(
                        channelConfiguration
                )))
                .numberOfCPUs(8)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(serviceSettingsGroup);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\n" +
                "  \"channelConfigurations\": [\n" +
                "    {\n" +
                "      \"channel\": 1,\n" +
                "      \"captureConfiguration\": {\n" +
                "        \"groupAffinity\": {\n" +
                "          \"value\": \"Group 0\"\n" +
                "        },\n" +
                "        \"selectedCPUs\": [\n" +
                "          1,\n" +
                "          3,\n" +
                "          5\n" +
                "        ],\n" +
                "        \"processorAffinityMask\": \"Default\",\n" +
                "        \"priority\": {\n" +
                "          \"options\": [\n" +
                "            \"Not Specified\",\n" +
                "            \"REALTIME\",\n" +
                "            \"HIGH\",\n" +
                "            \"ABOVE NORMAL\",\n" +
                "            \"NORMAL\",\n" +
                "            \"BELOW NORMAL\",\n" +
                "            \"IDLE\"\n" +
                "          ],\n" +
                "          \"value\": \"NORMAL\",\n" +
                "          \"default\": \"NORMAL\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"ingestConfiguration\": {\n" +
                "        \"groupAffinity\": {\n" +
                "          \"value\": \"Group 0\"\n" +
                "        },\n" +
                "        \"selectedCPUs\": [\n" +
                "          2,\n" +
                "          6,\n" +
                "          7\n" +
                "        ],\n" +
                "        \"processorAffinityMask\": \"Default\",\n" +
                "        \"priority\": {\n" +
                "          \"options\": [\n" +
                "            \"Not Specified\",\n" +
                "            \"REALTIME\",\n" +
                "            \"HIGH\",\n" +
                "            \"ABOVE NORMAL\",\n" +
                "            \"NORMAL\",\n" +
                "            \"BELOW NORMAL\",\n" +
                "            \"IDLE\"\n" +
                "          ],\n" +
                "          \"value\": \"NORMAL\",\n" +
                "          \"default\": \"NORMAL\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"ingestReadingSpeedFactor\": 0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"numberOfCPUs\": 8\n" +
                "}"), mapper.readTree(json));

        ServiceSettingsGroup ServiceSettingsGroupFromJSON = mapper.readValue(json, ServiceSettingsGroup.class);
        assertNotNull(ServiceSettingsGroupFromJSON);
        assertEquals(serviceSettingsGroup, ServiceSettingsGroupFromJSON);

    }

    @Test
    public void fromJSON_POST() throws JsonProcessingException {

        ServiceSettingsChannelConfiguration channelConfiguration = ServiceSettingsChannelConfiguration.builder()
                .channel(1)
                .captureConfiguration(ProcessorConfiguration.builder()
                        .groupAffinity(GroupAffinity.builder()
                                .selectedValue("Group 0")
                                .build())
                        .selectedCPUs(new TreeSet<>(Arrays.asList(1, 3, 5)))
                        .priority(ProcessorPriority.builder()
                                .selectedValue("NORMAL")
                                .build())
                        .build())
                .ingestConfiguration(ProcessorConfiguration.builder()
                        .groupAffinity(GroupAffinity.builder()
                                .selectedValue("Group 0")
                                .build())
                        .selectedCPUs(new TreeSet<>(Arrays.asList(2, 6, 7)))
                        .priority(ProcessorPriority.builder()
                                .selectedValue("NORMAL")
                                .build())
                        .build())
                .build();

        ServiceSettingsGroup serviceSettingsGroup = ServiceSettingsGroup.builder()
                .channelConfigurations(new TreeSet<>(Collections.singletonList(
                        channelConfiguration
                )))
                .numberOfCPUs(8)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(serviceSettingsGroup);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\n" +
                "  \"channelConfigurations\": [\n" +
                "    {\n" +
                "      \"channel\": 1,\n" +
                "      \"captureConfiguration\": {\n" +
                "        \"groupAffinity\": {\n" +
                "          \"value\": \"Group 0\"\n" +
                "        },\n" +
                "        \"selectedCPUs\": [\n" +
                "          1,\n" +
                "          3,\n" +
                "          5\n" +
                "        ],\n" +
                "        \"priority\": {\n" +
                "          \"value\": \"NORMAL\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"ingestConfiguration\": {\n" +
                "        \"groupAffinity\": {\n" +
                "          \"value\": \"Group 0\"\n" +
                "        },\n" +
                "        \"selectedCPUs\": [\n" +
                "          2,\n" +
                "          6,\n" +
                "          7\n" +
                "        ],\n" +
                "        \"priority\": {\n" +
                "          \"value\": \"NORMAL\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"ingestReadingSpeedFactor\": 0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"numberOfCPUs\": 8\n" +
                "}"), mapper.readTree(json));

        ServiceSettingsGroup ServiceSettingsGroupFromJSON = mapper.readValue(json, ServiceSettingsGroup.class);
        assertNotNull(ServiceSettingsGroupFromJSON);
        assertEquals(serviceSettingsGroup, ServiceSettingsGroupFromJSON);

    }

}