package com.technaxis.manager.service.impl;

import com.technaxis.manager.exception.NotFoundException;
import com.technaxis.manager.model.FileData;
import com.technaxis.manager.model.enums.ImageExtension;
import com.technaxis.manager.repository.FileDataRepository;
import com.technaxis.manager.service.FileDataService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileDataServiceImpl implements FileDataService {

    private static final String FILE_UPLOAD_FOLDER = "/data";
    private final FileDataRepository fileDataRepository;
    private final HttpServletRequest request;

    /**
     * Upload image file to folder
     *
     * @param multipartFile file
     * @return file model
     */
    @Override
    public FileData upload(final MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return null;
        }

        final String uploadFolder = request.getServletContext().getRealPath(FILE_UPLOAD_FOLDER);
        final String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        final String uuid = UUID.randomUUID().toString();
        final List<String> extensions = Arrays.stream(ImageExtension.values()).map(Enum::toString).collect(Collectors.toList());

        if (extension == null || !extensions.contains(extension)) {
            throw new IllegalArgumentException("Invalid file type");
        }

        if (!new File(uploadFolder).exists()) {
            new File(uploadFolder).mkdir();
        }

        try {
            multipartFile.transferTo(new File(uploadFolder + "/" + uuid + "." + extension));
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return fileDataRepository.save(FileData.builder()
                .originalName(multipartFile.getOriginalFilename())
                .uuid(uuid)
                .extension(ImageExtension.valueOf(extension.toUpperCase()))
                .build());
    }

    /**
     * Get file by id
     *
     * @param id file identifier
     * @return file model
     */
    @Override
    public FileData getById(final Long id) {
        return fileDataRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    /**
     * Get file by uuid
     *
     * @param uuid file uuid
     * @return file model
     */
    @Override
    public FileData getByUuid(final String uuid) {
        return fileDataRepository.findByUuid(uuid).orElseThrow(NotFoundException::new);
    }

}
