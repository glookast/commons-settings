package com.glookast.commons.settings;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.glookast.commons.settings.groups.background_transfer.BackgroundTransferGroup;
import com.glookast.commons.settings.groups.capture.CaptureSettingsGroup;
import com.glookast.commons.settings.groups.general.GeneralSettingsGroup;
import com.glookast.commons.settings.groups.loop_record.LoopRecordSettingsGroup;
import com.glookast.commons.settings.groups.media_processor.MediaProcessorGroup;
import com.glookast.commons.settings.groups.metadata.MetadataSettingsGroup;
import com.glookast.commons.settings.groups.network.NetworkSettingsGroup;
import com.glookast.commons.settings.groups.processes_settings.ProcessesSettingsGroup;
import com.glookast.commons.settings.groups.service_settings.ServiceSettingsGroup;
import com.glookast.commons.settings.groups.storage_manager.StorageManagerGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, defaultImpl = Settings.class, property = "type")
public class Settings {

    private GeneralSettingsGroup general;

    private MetadataSettingsGroup metadata;

    private NetworkSettingsGroup network;

    private CaptureSettingsGroup capture;

    private LoopRecordSettingsGroup loopRecord;

    private BackgroundTransferGroup backgroundTransfer;

    private MediaProcessorGroup mediaProcessor;

    private ProcessesSettingsGroup processesSettings;

    private StorageManagerGroup storageManager;

    private ServiceSettingsGroup serviceSettings;

}
