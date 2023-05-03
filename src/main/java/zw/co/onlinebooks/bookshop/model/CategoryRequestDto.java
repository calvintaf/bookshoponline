package zw.co.onlinebooks.bookshop.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class CategoryRequestDto {

    @NotBlank
    @ApiModelProperty(value = "Category Title", example = "Educational")
    private String title;
}
