package com.rpd.ms_books_catalogue;

import com.rpd.ms_books_catalogue.model.Book;
import com.rpd.ms_books_catalogue.repository.BookRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Profile("dev")
public class DatabaseSeeder {

    @Bean
    CommandLineRunner initDatabase(BookRepository bookRepository) {
        return args -> {
            bookRepository.deleteAll();

            List<Book> books = List.of(
                    new Book("El Señor de los Anillos", "J.R.R. Tolkien", LocalDate.of(1954, 7, 29), "Fantasía", 12.0, "978-0618640157", 5, true),
                    new Book("Cien años de soledad", "Gabriel García Márquez", LocalDate.of(1967, 6, 5), "Realismo mágico", 50.0, "978-0307474728", 5, true),
                    new Book("1984", "George Orwell", LocalDate.of(1949, 6, 8), "Ciencia ficción", 18.0, "978-0451524935", 4, true),
                    new Book("Moby-Dick", "Herman Melville", LocalDate.of(1851, 10, 18), "Aventura", 28.4,  "978-1503280786", 3, false)
            );
            bookRepository.saveAll(books);
            System.out.println("✅ Base de datos inicializada con datos de prueba");
        };
    }
}
