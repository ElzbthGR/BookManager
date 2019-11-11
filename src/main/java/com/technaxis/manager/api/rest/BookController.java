package com.technaxis.manager.api.rest;

import com.technaxis.manager.dto.form.BookForm;
import com.technaxis.manager.dto.form.EditBookForm;
import com.technaxis.manager.model.Book;
import com.technaxis.manager.service.BookService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api("Book")
@RestController
@RequestMapping(BookController.ROOT_PATH)
@RequiredArgsConstructor
public class BookController {

    static final String ROOT_PATH = "/api/v1/books";
    private static final String ONE_BOOK_PATH = ROOT_PATH + "/{bookId}";
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> add(@RequestBody @Valid final BookForm book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @GetMapping(ONE_BOOK_PATH)
    public ResponseEntity<Book> get(@PathVariable final Long bookId) {
        Optional<Book> book = bookService.findBook(bookId);
        return book.isPresent()
                ? ResponseEntity.ok(book.get())
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(ONE_BOOK_PATH + "/edit")
    public ResponseEntity<Book> update(@RequestBody @Valid final EditBookForm book,
                                       @PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.updateBook(bookId, book));
    }

    @PostMapping(ONE_BOOK_PATH)
    public ResponseEntity<Book> markBookAsRead(@PathVariable final Long bookId) {
        return ResponseEntity.ok(bookService.markAsRead(bookId));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(Pageable pageable) {
        return ResponseEntity.ok(bookService.findAll(pageable));
    }

    @PostMapping("/search")
    public ResponseEntity<List<Book>> searchByTitleOrAuthor(String query) {
        return ResponseEntity.ok(bookService.search(query));
    }

}
