package com.technaxis.manager.service;

import com.technaxis.manager.model.Book;
import com.technaxis.manager.model.FileData;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import com.technaxis.manager.dto.form.BookForm;
import com.technaxis.manager.dto.form.EditBookForm;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BookService {

    Book addBook(BookForm newBook);

    Book updateBook(Long id, EditBookForm updatedBook);

    Optional<Book> findBook(Long id);

    Book markAsRead(Long id);

    List<Book> findAll(Pageable pageable);

    List<Book> search(String query);

}
