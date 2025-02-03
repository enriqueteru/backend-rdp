package com.rpd.ms_books_catalogue.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "books")
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private LocalDate publicationDate;
    private String category;
    private Double price;
    private String isbn;
    private int rating;
    private boolean visible;

    public Book(String title, String author, LocalDate publicationDate, String category, Double price, String isbn, int rating, boolean visible) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.category = category;
        this.price = price;
        this.isbn = isbn;
        this.rating = rating;
        this.visible = visible;
    }
}
