package com.technaxis.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String title;

    private String description;

    private String author;

    private String isbn;

    private Long printYear;

    private Boolean readAlready;

    private FileDataDto cover;
}
