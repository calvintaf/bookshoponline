package zw.co.onlinebooks.bookshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.onlinebooks.bookshop.persistance.entity.Category;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDto {
    private Long id;
    private String title;

    public CategoryResponseDto(Category category){
        this.id = category.getId();
        this.title = category.getTitle();
    }

}
