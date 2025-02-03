package com.rpd.ms_books_catalogue.controller;

import com.rpd.ms_books_catalogue.model.Book;
import com.rpd.ms_books_catalogue.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book(
                "El Señor de los Anillos",
                "J.R.R. Tolkien",
                LocalDate.of(1954, 7, 29),
                "Fantasía",
                12.0,
                "978-0618640157",
                5,
                true
        );

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());
    }


    @Test
    void testGetAllBooks() throws Exception {
        Book book1 = new Book("El Señor de los Anillos", "J.R.R. Tolkien",
                LocalDate.of(1954, 7, 29), "Fantasía", 12.0,
                "978-0618640157", 5, true);

        Book book2 = new Book("Cien años de soledad", "Gabriel García Márquez",
                LocalDate.of(1967, 6, 5), "Realismo mágico", 50.0,
                "978-0307474728", 5, true);

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
}

    @Test
    void testGetBookById() throws Exception {
        Book book =  new Book("El Señor de los Anillos", "El Señor de los Anillos", LocalDate.of(1954, 7, 29), "Fantasía", 12.0, "978-0618640157", 5, true);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("El Señor de los Anillos"));
    }

    @Test
    void testCreateBook() throws Exception {
        Book book =  new Book("El Señor de los Anillos", "El Señor de los Anillos", LocalDate.of(1954, 7, 29), "Fantasía", 12.0, "978-0618640157", 5, true);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"El Señor de los Anillos\",\"author\":\"El Señor de los Anillos\",\"isbn\":\"978-0618640157\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateBook() throws Exception {
        Book book =  new Book("El Señor de los Anillos", "J.R.R. Tolkien", LocalDate.of(1954, 7, 29), "Fantasía", 12.0, "978-0618640157", 5, true);
        Book updatedBook = new Book("New Title", "Author1", LocalDate.of(1954, 7, 29), "Fantasía", 12.0, "978-0618640157", 5, true);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        mockMvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Title\",\"author\":\"Author1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Title"));
    }

    @Test
    void testSoftDeleteBook() throws Exception {
        Book book =  new Book("El Señor de los Anillos", "J.R.R. Tolkien", LocalDate.of(1954, 7, 29), "Fantasía", 12.0, "978-0618640157", 5, true);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        mockMvc.perform(put("/api/books/1/soft-delete"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Book with id 1 was successfully soft deleted."));
    }
}