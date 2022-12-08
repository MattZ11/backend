package survey.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.TraineeDto;
import survey.backend.entities.Trainee;
import survey.backend.error.NoDataFoundError;
import survey.backend.error.BadRequestError;
import survey.backend.service.TraineeService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;


@RestController     // "prépare" le framework à intégrer un contrôleur
@RequestMapping("api/trainee")      //   permet de diriger toutes les requêtes contenues dans Request vers "api/trainee" sur le serveur
public class TraineeController {

    @Autowired // DI (dependency Injection)
    private TraineeService traineeService;

    /**
     * list of trainees
     * route: /api/trainee
     * @return list of trainees
     */
    @GetMapping
    public Iterable<Trainee> getAll(){
        return traineeService.findAll();
    }

    /**
     * a trainee by its id
     * route: /api/trainee/{id}
     * @param id
     * @return a trainee
     */
    @GetMapping("{id}")
    public Trainee getById(@PathVariable("id") int id){
        Optional<Trainee> optTrainee = traineeService.findById(id);
        if (optTrainee.isPresent()){
            return optTrainee.get();
        } else {
            throw NoDataFoundError.withId("Trainee", id);
        }
    }

    /**
     * search trainees with criteria
     * route: /api/trainee/search?fn=some_firstName&ln=some_lastName
     * @param firstName (optional)
     * @param lastName (optional)
     * @return trainees corresponding
     */

    @GetMapping("search")
    public Iterable<Trainee> search(
            @RequestParam(name="ln", required = false) String lastName,
            @RequestParam(name="fn", required = false) String firstName
    ){
        int size = 0;

        if (lastName == null && firstName == null) {
            throw new BadRequestError("search with no args not permitted"); // 400 bad request
        }

        Iterable<Trainee> iTrainees = traineeService.search(lastName, firstName);   //  Service results

        // Get element number
        if (iTrainees instanceof Collection) {
            size = ((Collection<Trainee>) iTrainees).size();
        }


        if (size == 0) {
            throw NoDataFoundError.noResults("Trainee search", lastName + " " + firstName);
        }

        return iTrainees;
    }
    /*
    Version simplifiée

    @GetMapping("search")
    public Iterable<Trainee> search(
            @RequestParam(name="ln", required = false) String lastName,
            @RequestParam(name="fn", required = false) String firstName
    ) {
        // return 400 BAD REQUEST if both params are null
        if (lastName == null && firstName == null) {
            throw new BadRequestError ("Search with no args not permitted, dummy.");
        }else{

            return traineeService.search(lastName, firstName);
        }
    }
    */

    /**
     * add new trainee with data in json body
     * route: POST /api/trainee
     * @param traineeDto
     * @return trainee added/completed
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trainee add(@Valid @RequestBody TraineeDto traineeDto){
        // TODO: traineeDto must be valid
        return traineeService.add(traineeDto);
    }

    /**
     * update trainee with data in json body
     * route: PUT /api/trainee
     * @param traineeDto
     * @return
     */
    @PutMapping
    public Trainee update(@Valid @RequestBody TraineeDto traineeDto) {
        // TODO: traineeDto must be valid
        return traineeService.update(traineeDto)
                .orElseThrow(() -> NoDataFoundError.withId("Trainee", Math.toIntExact(traineeDto.getId())));
    }

    /**
     * delete trainee with its id
     * route: DELETE /api/trainee/{id}
     * @param id
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        if (!traineeService.delete(id)) {
            throw NoDataFoundError.withId("Trainee", id);
        }
    }

}



//package survey.backend.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import survey.backend.dto.TraineeDto;
//import survey.backend.error.NoDataFoundError;
//import survey.backend.service.TraineeService;
//
//import javax.validation.Valid;
//import java.time.LocalDate;
//import java.util.Optional;
//import java.util.Set;
//
//@RestController     // "prépare" le framework à intégrer un contrôleur
//@RequestMapping("api/trainee")     //   permet de diriger toutes les requêtes contenues dans Request vers "api/trainee" sur le serveur
//public class TraineeController {
//
//    @Autowired // DI (Dependency Injection)
//    private TraineeService traineeService;
//
//    /**
//     * list of trainees
//     * route: /api/string
//     * @return list of trainees
//     */
//
//    @GetMapping     // traduit l'ouverture de la page
//    public Iterable<TraineeDto> getAll() {
//        return traineeService.findAll();
//    }
//
//    /**
//     * a trainee by its id
//     * route : api/trainee/{id}
//     * @param id
//     * @return a trainee
//     */
//
//
//    @GetMapping("{id}")    //   permet d'insérer un paramètre comme identifiant de l'arborescence (route)
//
//    public Trainee getById(@PathVariable("id") int id){
//        Optional<Trainee> optTrainee = traineeService.findById(id);
//        if(optTrainee.isPresent()) {
//            return optTrainee.get();
//        }else{
//            throw NoDataFoundError.withId("Trainee", id);   //  2 paramètres
//        }
//
//    //modifications dues à la création de l'interface service
//
//    //public Optional<TraineeDto> getById(@PathVariable("id") int id) {     // @Pathvariable permet de faire en sorte que ce paramètre soit une variable
//      // return traineeService.findById(id);
//        //       return Optional.empty();
//        //       return Optional.of(TraineeDto.builder()     //"One trainee " + id;
//        //              .id(id)
//        //              .lastName("Presley")
//        //             .firstName("Elvis")
//        //             .birthDate(LocalDate.of(1925, 8, 24))
//        //             .build());
//
//
//    }
//
//    /**
//     * search trainees with criteria
//     * route: /api/trainee/search?&fn=some_firstname&ln=some_lastname
//     * @param firstName (optional)
//     * @param lastName (optional)
//     * @return trainees corresponding
//     */
//
//    @GetMapping("search")
//    //  crée une arborescence fille supplémentaire sur le serveur, et correspond à ce qui sera
//    //  affiché à l'adresse correspondant sur la page web'
//    public Iterable<Trainee> search(
//            @RequestParam(name="fn", required = false) String firstName,     // @RequestParam permet de créer une référence correspondant à un paramètre
//            @RequestParam(name="ln", required = false) String lastName        // ("ln") peut être remplacé par (name="ln", required = false) lorsqu'il y a plusieurs paramètres ?
//    ) {
//        return traineeService.search(lastName, firstName);
//    }
//
//
//    @PostMapping    //Post: on veut entrer des données
//    @ResponseStatus(HttpStatus.CREATED)
//    public Trainee addTrainee(@Valid @RequestBody TraineeDto traineeDto){   //création de la méthode d'ajout de stagiaire
//        // TO DO: add in under layer
//        // traineeDto.setId(54321);          //modifications dues à l'instauration de l'interface services
//        //        return traineeDto;
//        return traineeService.add(traineeDto);
//
//
//    }
//
//    /**
//     * update trainee with data in json body
//     * route: PUT /api/trainee
//     * @param traineeDto
//     * @return
//     */
//    @PutMapping
//    public Trainee update(@Valid @RequestBody TraineeDto traineeDto) {
//        return traineeService.update(traineeDto)
//                .orElseThrow(() -> NoDataFoundError.withId("Trainee", Math.toIntExact(traineeDto.getId()));
//    }
//
//    @DeleteMapping("{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable ("id") int id) {   // TO DO : delete this object if exist
//        if (!traineeService.delete(id)) {
//            throw NoDataFoundError.withId("Trainee", id);
//        }
//    }
//
//    //mon essai
//    /*@DeleteMapping("delete")    //  raccorder au Search ?
//    public choice(@PathVariable("delete") any choice) {                //  id/lastName/firstName/birthDate
//    return "Entry "+ choice.type + choice +" deleted. No regrets ?"
//
//    @PutMapping                 //  raccorder au Search ?
//
//    @PatchMapping               //  raccorder au Search ?*/
//
//
//
//    //  mon essai mais à mettre dans ErrorHandler
//   /* @ExceptionHandler({NoDataFoundError.class})
//    public void handleException() {
//
//    }*/
//
//}
