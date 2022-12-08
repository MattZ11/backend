package survey.backend.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
    private int status; //  définit le statut ("status": 400 etc)

    private String error;   //  définit le message standard ("error": "POE not valid")

    private List<AbstractErrorDetailMessage> details;    //  définit les détails de l'erreur ("details": "field" : "title"
                                                                                            //  "error":length must be at least 3 but was 2"
                                                                                            //  permet des actions ciblées de marquage d'erreur dans le front

}