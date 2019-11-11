package com.technaxis.manager.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ImageExtension {
    PNG("png"),
    JPG("jpg"),
    JPEG("jpeg");

    private String extension;
}
