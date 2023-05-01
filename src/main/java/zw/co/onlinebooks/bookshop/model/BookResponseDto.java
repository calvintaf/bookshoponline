package zw.co.onlinebooks.bookshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.onlinebooks.bookshop.persistance.entity.Book;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDto {
    private Long id;
    private String description;
    private String title;
    private BigDecimal price;
    private CategoryResponseDto categoryResponseDto;

    public BookResponseDto(Book book) {
        this.id = book.getId();
        this.description = book.getDescription();
        this.title = book.getTitle();
        this.price = book.getPrice();
        this.categoryResponseDto = new CategoryResponseDto(book.getCategory());
    }
}
