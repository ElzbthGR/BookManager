package com.technaxis.manager.service;

import com.technaxis.manager.model.FileData;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface FileDataService {

    FileData upload(MultipartFile multipartFile);

    FileData getById(Long id);

    FileData getByUuid(String uuid);
}
