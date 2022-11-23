package survey.backend.dto;

//   ajouter controller pour les Poe (id, title, beginDate, endDate, poeType)
//   avec 3 méthodes minimum: find all, find by id, add one

//  tester le tout avec postman
import lombok.*;

import java.time.LocalDate;
import java.util.EnumSet;

@Builder                //  essayer de comprendre la pertinence de tout ça
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


public class PoeDto {

    private Integer id;
    private String title;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Enum  poeType;
}
