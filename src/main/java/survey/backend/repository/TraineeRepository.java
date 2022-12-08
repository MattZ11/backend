package survey.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import survey.backend.entities.Trainee;

public interface TraineeRepository extends CrudRepository<Trainee, Long> {  // Long est le type de la clé de la bdd
    @Query(
            value = "SELECT id, last_name, first_Name, birth_date, phone_number, email FROM trainee WHERE last_name = :lastName AND first_name = :firstName",
            nativeQuery = true
    )
    public Iterable<Trainee> listByLastNameAndFirstName(@Param(value = "lastName") String lastName, @Param(value = "firstName") String FirstName);



    @Query(
            value = "SELECT t FROM Trainee t WHERE t.lastName = :lastName AND t.firstName = :firstName"
    )
    public Iterable<Trainee> byLastNameAndFirstName(@Param(value = "lastName") String lastName, @Param(value = "firstName") String FirstName);

    public Iterable<Trainee> findByLastName(String lastName);

    public Iterable<Trainee> findByFirstName(String firstName);


}
