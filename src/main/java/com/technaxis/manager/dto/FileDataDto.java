package com.technaxis.manager.dto;

import com.technaxis.manager.model.enums.ImageExtension;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDataDto {

    private String id;

    private String uuid;

    private ImageExtension extension;
}
