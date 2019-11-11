package com.technaxis.manager.dto.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookForm {

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotEmpty
    private String author;

    @NotEmpty
    private String isbn;

    @NotNull
    private Long printYear;

    private Long coverId;
}
