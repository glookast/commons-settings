package com.glookast.commons.settings.groups.processes_settings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProcessesSettingsGroupTest {

    @Test
    public void fromJSON() throws JsonProcessingException {

        String json = "{\n" +
                "    \"serialControlServiceEnabled\": true,\n" +
                "    \"SDIPlayerEnabled\": true,\n" +
                "    \"VTRControllerEnabled\": true,\n" +
                "    \"selectableOnGuiEnabled\": true,\n" +
                "    \"defaultPlayoutResolution\": \"Resolution #2\"\n" +
                "  }";

        ObjectMapper mapper = new ObjectMapper();
        ProcessesSettingsGroup group = mapper.readValue(json, ProcessesSettingsGroup.class);
        assertEquals(true, group.getSerialControlServiceEnabled());
        assertEquals(true, group.getSdiPlayerEnabled());
        assertEquals(true, group.getVtrControllerEnabled());
        assertEquals(true, group.getSelectableOnGuiEnabled());
        assertEquals(ProcessesSettingsGroup.PlayoutResolution.RESOLUTION_2, group.getDefaultPlayoutResolution());
        assertNotNull(group);

    }

    @Test
    public void should_fail_if_invalid_resolution() {

        String json = "{\n" +
                "    \"serialControlServiceEnabled\": true,\n" +
                "    \"SDIPlayerEnabled\": true,\n" +
                "    \"VTRControllerEnabled\": true,\n" +
                "    \"selectableOnGuiEnabled\": true,\n" +
                "    \"defaultPlayoutResolution\": \"dummy\"\n" +
                "  }";

        Assertions.assertThrows(JsonProcessingException.class, () -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.readValue(json, ProcessesSettingsGroup.class);
        });

    }

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
                "  \"selectableOnGuiEnabled\": true,\n" +
                "  \"defaultPlayoutResolution\": \"Resolution #1\"\n" +
                "}"), mapper.readTree(json));

        ProcessesSettingsGroup ProcessesSettingsGroupFromJSON = mapper.readValue(json, ProcessesSettingsGroup.class);
        assertNotNull(ProcessesSettingsGroupFromJSON);
        assertEquals(processesSettingsGroup, ProcessesSettingsGroupFromJSON);

    }

}