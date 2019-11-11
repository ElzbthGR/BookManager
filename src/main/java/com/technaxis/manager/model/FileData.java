package com.technaxis.manager.model;

import com.technaxis.manager.model.enums.ImageExtension;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files")
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalName;

    private String uuid;

    @Enumerated(EnumType.STRING)
    private ImageExtension extension;
}
