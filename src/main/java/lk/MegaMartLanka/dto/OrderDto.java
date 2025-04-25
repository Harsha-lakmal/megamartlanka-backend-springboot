package lk.MegaMartLanka.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class OrderDto {
    List<Long> itemIds;
}
