package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.TraineeDto;
import survey.backend.service.TraineeService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@RestController     // "prépare" le framework à intégrer un contrôleur
@RequestMapping("api/trainee")     //permet de diriger toutes les requêtes contenues dans Request vers "api/trainee" sur le serveur
public class TraineeController {

    @Autowired // DI (Dependency Injection)
    private TraineeService traineeService;

    /**
     * list of trainees
     * route: /api/string
     * @return list of trainees
     */

    @GetMapping     // traduit l'ouverture de la page
    public Set<TraineeDto> list() {
        return traineeService.findAll();
//        return Set.of(
//                TraineeDto.builder()
//                        .id(2)
//                        .lastName("Presley")
//                        .firstName("Elvis")
//                        .birthDate(LocalDate.of(1935, 1, 8))
//                        .build(),
//                TraineeDto.builder()
//                        .id(42)
//                        .lastName("Berry")
//                        .firstName("Chuck")
//                        .birthDate(LocalDate.of(1936, 4, 22))
//                        .build(),
//                TraineeDto.builder()
//                        .id(50)
//                        .lastName("Joplin")
//                        .firstName("Janis")
//                        .birthDate(LocalDate.of(1940, 1, 4))
//                        .build(),
//                TraineeDto.builder()
//                        .id(70)
//                        .lastName("Hendrix")
//                        .firstName("Jimmi")
//                        .birthDate(LocalDate.of(1942, 11, 23))
//                        .build(),
//                TraineeDto.builder()
//                        .id(1)
//                        .lastName("Simone")
//                        .firstName("Nina")
//                        .birthDate(LocalDate.of(1932, 4, 12))
//                        .build(),
//                TraineeDto.builder()
//                        .id(8)
//                        .lastName("Mercury")
//                        .firstName("Freddie")
//                        .birthDate(LocalDate.of(1946, 9, 5))
//                        .build(),
//                TraineeDto.builder()
//                        .id(124)
//                        .lastName("Power")
//                        .firstName("Max")
//                        .birthDate(LocalDate.of(1954, 7, 15))
//                        .build(),
//                TraineeDto.builder()
//                        .id(4)
//                        .lastName("Brown")
//                        .firstName("James")
//                        .birthDate(LocalDate.of(1938, 2, 14))
//                        .build()
//
//        );
//
    }

    @PostMapping    //Post: on veut entrer des données
    @ResponseStatus(HttpStatus.CREATED)
    public TraineeDto addTrainee(@RequestBody TraineeDto traineeDto){   //création de la méthode d'ajout de stagiaire
        // TO DO: add in under layer
        // traineeDto.setId(54321);          //modifications dues à l'instauration de l'interface services
        //        return traineeDto;
        return traineeService.add(traineeDto);


    }

    /**
     * a trainee by its id
     * route : api/trainee/{id}
     * @param id
     * @return a trainee
     */


    @GetMapping("{id}")    //   permet d'insérer un paramètre comme identifiant de l'arborescence (route)

    public TraineeDto one(@PathVariable("id")int id){
        Optional<TraineeDto> optTraineeDto = traineeService.findById(id);
        if(optTraineeDto.isPresent()) {
            return optTraineeDto.get();
        }else{
            throw new IllegalArgumentException(
                    "Trainee with id " + id + " not found");

        }

    //modifications dues à la création de l'interface service

    //public Optional<TraineeDto> one(@PathVariable("id") int id) {     // @Pathvariable permet de faire en sorte que ce paramètre soit une variable
      // return traineeService.findById(id);
        //       return Optional.empty();
        //       return Optional.of(TraineeDto.builder()     //"One trainee " + id;
        //              .id(id)
        //              .lastName("Presley")
        //             .firstName("Elvis")
        //             .birthDate(LocalDate.of(1925, 8, 24))
        //             .build());


    }

    /**
     * search trainees with criteria
     * route: /api/trainee/search?&fn=some_firstname&ln=some_lastname
     * @param firstName (optional)
     * @param lastName (optional)
     * @return trainees corresponding
     */

    @GetMapping("search")
    //  crée une arborescence fille supplémentaire sur le serveur, et correspond à ce qui sera
    //  affiché à l'adresse correspondant sur la page web'
    public String search(
            @RequestParam(name="fn", required = false) String firstName,     // @RequestParam permet de créer une référence correspondant à un paramètre
            @RequestParam(name="ln", required = false) String lastName        // ("ln") peut être remplacé par (name="ln", required = false) lorsqu'il y a plusieurs paramètres ?
    ) {
        return "Search result: fn =" + firstName + " ; ln =" + lastName;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable ("id") int id) {
        // TO DO : delete this object if exists
    }

    //mon essai
    /*@DeleteMapping("delete")    //  raccorder au Search ?
    public choice(@PathVariable("delete") any choice) {                //  id/lastName/firstName/birthDate
    return "Entry "+ choice.type + choice +" deleted. No regrets ?"

    @PutMapping                 //  raccorder au Search ?

    @PatchMapping               //  raccorder au Search ?*/

}
