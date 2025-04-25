package lk.MegaMartLanka.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ItemDto {
    private String name;
    private String description;
    private Double price;
    private Long categoryId;
}
