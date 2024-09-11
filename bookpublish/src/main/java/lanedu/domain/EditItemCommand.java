package lanedu.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class EditItemCommand {

    private String contents;
    private String title;
    private Integer price;
}
