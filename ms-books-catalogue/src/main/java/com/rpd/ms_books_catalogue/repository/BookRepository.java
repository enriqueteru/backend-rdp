package com.rpd.ms_books_catalogue.repository;

import com.rpd.ms_books_catalogue.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByCategoryContainingIgnoreCase(String category);
    List<Book> findByIsbn(String isbn);
    List<Book> findByRating(int rating);
    List<Book> findByVisible(boolean visible);
    List<Book> findByPriceBefore(Double price);
}
