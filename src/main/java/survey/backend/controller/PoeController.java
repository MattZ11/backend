package survey.backend.controller;

//   ajouter controller pour les Poe (id, title, beginDate, endDate, poeType)
//   avec 3 méthodes minimum: find all, find by id, add one

//  tester le tout avec postman


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.PoeDto;
import survey.backend.dto.TraineeDto;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("api/poe")
public class PoeController {

   /* @GetMapping
    public Set<PoeDto> list();

    {
        return Set.of(
                PoeDto.builder()
                        .id(1)
                        .title("JAVA Fullstack")
                        .beginDate(LocalDate.of(2020, 1, 8))
                        .endDate(LocalDate.of(2020, 3, 30))
                        .poeType()
                        .build(),
                PoeDto.builder()
                        .id(2)
                        .title("Data BI")
                        .beginDate(LocalDate.of(1935, 1, 8))
                        .endDate(LocalDate.of(1935, 1, 8))
                        .poeType()
                        .build(),
                PoeDto.builder()
                        .id(3)
                        .title("Presley")
                        .beginDate(LocalDate.of(1935, 1, 8))
                        .endDate(LocalDate.of(1935, 1, 8))
                        .poeType()
                        .build(),
                PoeDto.builder()
                        .id(4)
                        .title("Presley")
                        .beginDate(LocalDate.of(1935, 1, 8))
                        .endDate(LocalDate.of(1935, 1, 8))
                        //.poeType()
                        .build()
        );
    }*/


    @GetMapping("find")
    //  crée une arborescence fille supplémentaire sur le serveur, et correspond à ce qui sera
    //  affiché à l'adresse correspondant sur la page web'
    public String find(
            @RequestParam(name="id", required = false) String id    // @RequestParam permet de créer une référence correspondant à un paramètre

    ) {
        return "Search result: id =" + id;
    }


    @PostMapping    //Post: on veut entrer des données
    @ResponseStatus(HttpStatus.CREATED)
    public PoeDto addPoe(@RequestBody PoeDto poeDto){   //création de la méthode d'ajout de POE
        // TO DO: add in under layer
        poeDto.setId(54321);
        return poeDto;


    }

}