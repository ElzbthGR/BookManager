package com.technaxis.manager.api.rest;

import com.technaxis.manager.model.FileData;
import com.technaxis.manager.service.FileDataService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api("File")
@RestController
@RequestMapping(FileController.ROOT_PATH)
@RequiredArgsConstructor
public class FileController {

    static final String ROOT_PATH = "/api/v1/file";
    private final FileDataService fileDataService;

    @PostMapping
    public ResponseEntity<FileData> upload(@RequestBody final MultipartFile file) {
        return ResponseEntity.ok(fileDataService.upload(file));
    }
}
