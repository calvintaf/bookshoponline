package zw.co.onlinebooks.bookshop.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class BookRequestDto {
    @NotBlank
    @ApiModelProperty(value = "Description", example = "Fundamentals of learning to code in react")
    private String description;

    @NotBlank
    @ApiModelProperty(value = "Title", example = "Introduction to React")
    private String title;

    @NotBlank
    @ApiModelProperty(value = "Price", example = "15.30")
    private BigDecimal price;

    @NotBlank
    @ApiModelProperty(value = "Category", example = "1")
    private Long categoryId;
}


