package com.example.AtmReportService.model;

public enum FileExtension {
    XML("xml"),
    CSV("csv"),
    JSON("json"),
    YAML("yaml");

    private final String extension;

    FileExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public static FileExtension fromString(String extension) {
        for (FileExtension fileExtension : FileExtension.values()) {
            if (fileExtension.getExtension().equalsIgnoreCase(extension)) {
                return fileExtension;
            }
        }
        throw new IllegalArgumentException("Unknown file extension: " + extension);
    }
}
