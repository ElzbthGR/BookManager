package com.technaxis.manager.dto.form;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditBookForm {

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotEmpty
    private String isbn;

    @NotEmpty
    private Long printYear;

}
