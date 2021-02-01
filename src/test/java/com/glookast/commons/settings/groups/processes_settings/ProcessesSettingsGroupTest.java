package com.glookast.commons.settings.groups.processes_settings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProcessesSettingsGroupTest {

    @Test
    public void toJSON_minimal() throws JsonProcessingException {

        ProcessesSettingsGroup processesSettingsGroup = ProcessesSettingsGroup.builder().build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(processesSettingsGroup);
        assertNotNull(json);

        assertEquals(mapper.readTree("{\n" +
                "  \"serialControlServiceEnabled\": false,\n" +
                "  \"SDIPlayerEnabled\": false,\n" +
                "  \"VTRControllerEnabled\": false,\n" +
                "  \"SelectableOnGuiEnabled\": true,\n" +
                "  \"DefaultPlayoutResolution\": \"Resolution #1\"\n" +
                "}"), mapper.readTree(json));

        ProcessesSettingsGroup ProcessesSettingsGroupFromJSON = mapper.readValue(json, ProcessesSettingsGroup.class);
        assertNotNull(ProcessesSettingsGroupFromJSON);
        assertEquals(processesSettingsGroup, ProcessesSettingsGroupFromJSON);

    }

}