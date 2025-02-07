package com.example.AtmReportService.model;

import java.util.HashMap;
import java.util.Map;

public enum AtmFileType {
    ATM_A("A", "xml"),
    ATM_B("B", "csv"),
    ATM_C("C", "json"),
    ATM_D("D", "yaml");

    private static final Map<String, AtmFileType> extensionToAtm = new HashMap<>();

    static {
        for (AtmFileType type : values()) {
            extensionToAtm.put(type.fileExtension, type);
        }
    }

    private final String atmId;
    private final String fileExtension;

    AtmFileType(String atmId, String fileExtension) {
        this.atmId = atmId;
        this.fileExtension = fileExtension;
    }

    public String getAtmId() {
        return atmId;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public static AtmFileType fromExtension(String extension) {
        return extensionToAtm.get(extension.toLowerCase());
    }
}
