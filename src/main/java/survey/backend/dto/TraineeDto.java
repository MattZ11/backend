package survey.backend.dto;

import lombok.*;
import survey.backend.entities.Trainee;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

@Builder                //  essayer de comprendre la pertinence de tout ça
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TraineeDto {
    private Long id; //  id n'est créé qu'une fois la donnée créée, pas entrée par le front
    //  on utilise Integer au lieu de int car Integer est un type Objet et donc nullable ?

    @NotBlank // inclut NotNull
    private String lastName;

    @NotBlank
    private String firstName;

    @NotNull
    @Email
    private String email;

    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
    private String phoneNumber;

    @Past                           // nullables par défaut sauf NotBlank
    private Date birthDate;

    /*private id: number = 0;
    private lastName: string = '';
    private firstName: string = '';
    private email: string = '';
    private phoneNumber: string = '';
    private birthDate!: Date;*/

    public Trainee toTrainee() {
        Trainee trainee = new Trainee();
        trainee.setId(this.id);
        trainee.setLastName(this.lastName);
        trainee.setFirstName(this.firstName);
        trainee.setPhoneNumber(this.phoneNumber);
        trainee.setEmail(this.email);
        trainee.setBirthDate(this.birthDate);

        return trainee;
    }
}
