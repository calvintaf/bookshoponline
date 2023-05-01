package zw.co.onlinebooks.bookshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequestDto {
    private String description;
    private String title;
    private BigDecimal price;
    private Long categoryId;
}


