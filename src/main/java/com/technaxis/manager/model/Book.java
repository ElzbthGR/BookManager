package com.technaxis.manager.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String author;

    private String isbn;

    private Long printYear;

    private Boolean readAlready;

    @OneToOne
    @JoinColumn(name = "image_id")
    private FileData image;

}
