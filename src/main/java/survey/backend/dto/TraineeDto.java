package survey.backend.dto;

import lombok.*;

import java.time.LocalDate;

@Builder                //  essayer de comprendre la pertinence de tout ça
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TraineeDto {
    private Integer id; //  id n'est créé qu'une fois la donnée créée, pas entrée par le front
                        //  on utilise Integer au lieu de int car Integer est un type Objet et donc nullable ?
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;

    /*private id: number = 0;
    private lastName: string = '';
    private firstName: string = '';
    private email: string = '';
    private phoneNumber: string = '';
    private birthDate!: Date;*/
}
