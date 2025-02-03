package relatosdepapel.ms_payments.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import relatosdepapel.ms_payments.controller.data.OrderItemRequest;
import relatosdepapel.ms_payments.facade.model.Book;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class BooksFacade {

    @Value("${getBooks.url}")
    private String getBooksUrl;

    private final RestTemplate restTemplate;

    public Book getBook(OrderItemRequest orderItemRequest) {
        try {
            String id = String.valueOf(orderItemRequest.getBookId());
            return restTemplate.getForObject(String.format(getBooksUrl, id), Book.class);
        } catch (HttpClientErrorException e) {
            log.error(e.getResponseBodyAsString());
            return null;
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}
