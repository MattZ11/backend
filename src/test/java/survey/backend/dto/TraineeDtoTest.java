package survey.backend.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TraineeDtoTest {

    @Test
    void testDefaultConstructor() {
        //  when
        var traineeDto = new TraineeDto();
        //  then
        assertAll(
                () -> assertNull(traineeDto.getId(), "id"),             // assertNull on vérifie que l'attribut de l'objet par défaut est nul
                () -> assertNull(traineeDto.getFirstName(), "firstname"),
                () -> assertNull(traineeDto.getLastName(), "lastname"),
                () -> assertNull(traineeDto.getEmail(), "email"),
                () -> assertNull(traineeDto.getPhoneNumber(), "phone number"),
                () -> assertNull(traineeDto.getBirthDate(), "birthdate"));

    }

    // TO DO: Builder
    // TO DO: all args constructor
    @Test
    void testAllArgsConstructor() {
        // given
        int id = 123;
        String lastName = "Doe";
        String firstName = "John";
        String email = "john.doe@example.org";
        String phoneNumber = "+33600000001";
        var birthDate = LocalDate.of(1952, 2, 29);
        // when
        var traineeDto = new TraineeDto(id, lastName, firstName, email, phoneNumber, birthDate);

        //  then
        assertAll(
                () -> assertEquals(id, traineeDto.getId(), "id"),             // assertNull on vérifie que l'attribut de l'objet par défaut est nul
                () -> assertEquals(firstName, traineeDto.getFirstName(), "firstname"),
                () -> assertEquals(lastName, traineeDto.getLastName(), "lastname"),
                () -> assertNull(traineeDto.getEmail(), "email"),
                () -> assertNull(traineeDto.getPhoneNumber(), "phone number"),
                () -> assertNull(traineeDto.getBirthDate(), "birthdate")
        );


    }
}