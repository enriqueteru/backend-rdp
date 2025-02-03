package com.rpd.ms_books_catalogue.controller;

import com.rpd.ms_books_catalogue.dto.ResponseDTO;
import com.rpd.ms_books_catalogue.model.Book;
import com.rpd.ms_books_catalogue.repository.BookRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/search")
    public List<Book> searchByAttribute(@RequestParam(required = false) String title,
                                        @RequestParam(required = false) String author,
                                        @RequestParam(required = false) String category,
                                        @RequestParam(required = false) String isbn,
                                        @RequestParam(required = false) Integer rating,
                                        @RequestParam(required = false) Double price,
                                        @RequestParam(required = false) Boolean visible) {
        if (title != null) return bookRepository.findByTitleContainingIgnoreCase(title);
        if (author != null) return bookRepository.findByAuthorContainingIgnoreCase(author);
        if (category != null) return bookRepository.findByCategoryContainingIgnoreCase(category);
        if (isbn != null) return bookRepository.findByIsbn(isbn);
        if (rating != null) return bookRepository.findByRating(rating);
        if (visible != null) return bookRepository.findByVisible(visible);
        if (price != null && price >= 0) return bookRepository.findByPriceBefore(price);
        return bookRepository.findAll();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPublicationDate(updatedBook.getPublicationDate());
            book.setCategory(updatedBook.getCategory());
            book.setIsbn(updatedBook.getIsbn());
            book.setRating(updatedBook.getRating());
            book.setVisible(updatedBook.isVisible());
            return bookRepository.save(book);
        }).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));
        bookRepository.deleteById(book.getId());
    }

    @PutMapping("/{id}/soft-delete")
    public ResponseDTO softDeleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found."));
        book.setVisible(false);
        bookRepository.save(book);
        return new ResponseDTO("Book with id " + id + " was successfully soft deleted.", true);
    }
}
