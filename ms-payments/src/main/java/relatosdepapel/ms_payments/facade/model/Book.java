package relatosdepapel.ms_payments.facade.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Book {

    private Long id;
    private String title;
    private String author;
    private LocalDate publicationDate;
    private String category;
    private Double price;
    private String isbn;
    private int rating;
    private boolean visible;

}
