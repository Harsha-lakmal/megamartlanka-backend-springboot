package lk.MegaMartLanka.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class OrderDto {
    List<Long> itemIds;
}

