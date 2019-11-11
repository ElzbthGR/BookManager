package com.technaxis.manager.service.impl;

import com.technaxis.manager.model.Book;
import com.technaxis.manager.model.FileData;
import com.technaxis.manager.service.BookService;
import com.technaxis.manager.service.FileDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.technaxis.manager.dto.form.BookForm;
import com.technaxis.manager.dto.form.EditBookForm;
import com.technaxis.manager.repository.BookRepository;
import com.technaxis.manager.repository.FileDataRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final FileDataService fileDataService;

    /**
     * Add new book
     *
     * @param form dto for creating book
     * @return created model
     * @throws IOException
     */
    @Override
    public Book addBook(final BookForm form) {
        final FileData image = form.getCoverId() != null ? fileDataService.getById(form.getCoverId()) : null;
        final Book book = Book.builder()
                .title(form.getTitle())
                .description(form.getDescription())
                .author(form.getAuthor())
                .printYear(form.getPrintYear())
                .isbn(form.getIsbn())
                .readAlready(false)
                .image(image)
                .build();

        return bookRepository.save(book);
    }

    /**
     * Update book
     *
     * @param updatedBook dto of updating book
     * @return updated book
     */
    @Override
    public Book updateBook(final Long id, final EditBookForm updatedBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setDescription(updatedBook.getDescription());
                    book.setPrintYear(updatedBook.getPrintYear());
                    book.setIsbn(updatedBook.getIsbn());
                    book.setReadAlready(false);
                    return bookRepository.save(book);
                }).get();
    }

    /**
     * Find book by id
     *
     * @param id book identifier
     * @return Optional of book
     */
    @Override
    public Optional<Book> findBook(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * Mark book as read: change var readAlready as true
     *
     * @param id book identifier
     * @return read book
     */
    @Override
    public Book markAsRead(Long id) {
        return bookRepository.findById(id).map(book -> {
            book.setReadAlready(true);
            return bookRepository.save(book);
        }).get();
    }

    /**
     * Paged list of books
     *
     * @param pageable pagination information
     * @return paged list
     */
    @Override
    public List<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).getContent();
    }

    /**
     * Search by title, author or isbn
     *
     * @param query search query
     * @return book model
     */
    @Override
    public List<Book> search(String query) {
        return bookRepository.findByTitleContainingOrAuthorContainingOrIsbnContaining(query, query, query);
    }

}
