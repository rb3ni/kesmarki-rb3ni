package KesmarkiApp.controller;

import KesmarkiApp.domain.enums.StreetType;
import KesmarkiApp.dto.AddressInfo;
import KesmarkiApp.dto.PersonInfo;
import KesmarkiApp.dto.command.AddressCreateCommand;
import KesmarkiApp.dto.command.AddressUpdateCommand;
import KesmarkiApp.dto.command.PersonCreateCommand;
import KesmarkiApp.exceptionhandling.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AddressControllerIT {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void testSaveAddress_Success() {
        ResponseEntity<AddressInfo> resultResponseEntity = restTemplate.postForEntity("/api/addresses/people/1",
                new AddressCreateCommand("Magyarország", "1111",
                        "Gellért", StreetType.SQUARE, "1"), AddressInfo.class);

        AddressInfo addressInfo = Objects.requireNonNull(resultResponseEntity.getBody());

        assertThat(resultResponseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);
        assertThat(addressInfo.getCountry()).isEqualTo("Magyarország");
        assertThat(addressInfo.getPerson().getFirstName()).isEqualTo("János");
        assertThat(addressInfo.getStreetType().name()).isEqualTo("SQUARE");
    }

    @Test
    void testSaveAddress_AllValidation() {
        ResponseEntity<ValidationError[]> resultResponseEntity = restTemplate.postForEntity("/api/addresses/people/1",
                new AddressCreateCommand("", " ",
                        null, null, "  "), ValidationError[].class);

        List<String> fields = Arrays.stream(Objects.requireNonNull(resultResponseEntity.getBody()))
                .map(ValidationError::getField)
                .collect(Collectors.toList());
        List<String> errorMessages = Arrays.stream(Objects.requireNonNull(resultResponseEntity.getBody()))
                .map(ValidationError::getErrorMessage)
                .collect(Collectors.toList());

        assertThat(resultResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(fields).contains("country", "streetName", "streetType", "streetNumber", "postcode");
        assertThat(errorMessages).contains("must be not blank", "must be not null");
    }

    @Test
    void testGetAddressById_Success() {
        testSaveAddress_Success();

        ResponseEntity<AddressInfo> resultResponseEntity = restTemplate.getForEntity("/api/addresses/1", AddressInfo.class);
        AddressInfo addressInfo = Objects.requireNonNull(resultResponseEntity.getBody());

        assertThat(resultResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(addressInfo.getStreetName()).isEqualTo("Gellért");
        assertThat(addressInfo.getPerson().getNationality()).isEqualTo("magyar");
    }

    @Test
    void testGetAddressById_AddressNotFoundException() {
        ResponseEntity<ValidationError[]> resultResponseEntity = restTemplate.getForEntity("/api/addresses/1",
                ValidationError[].class);

        ValidationError validationError = Arrays.stream(Objects.requireNonNull(resultResponseEntity.getBody()))
                .findFirst().get();

        assertThat(resultResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(validationError.getField()).contains("addressId");
        assertThat(validationError.getErrorMessage()).contains("Address with id 1 is not found.");
    }

    @Test
    void testModifyAddress_Success() {
        testSaveAddress_Success();

        restTemplate.put("/api/addresses/1", new AddressUpdateCommand("Magyarország", "1081",
                "Hős", StreetType.STREET, "5"), Void.class);

        ResponseEntity<AddressInfo> resultResponseEntity = restTemplate.getForEntity("/api/addresses/1", AddressInfo.class);
        AddressInfo addressInfo = Objects.requireNonNull(resultResponseEntity.getBody());

        assertThat(resultResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(addressInfo.getCountry()).isEqualTo("Magyarország");
        assertThat(addressInfo.getPostcode()).isEqualTo("1081");
        assertThat(addressInfo.getStreetName()).isEqualTo("Hős");
        assertThat(addressInfo.getStreetType().name()).isEqualTo("STREET");
        assertThat(addressInfo.getStreetNumber()).isEqualTo("5");
    }

    @Test
    void testDeleteAddress_Success() {
        testSaveAddress_Success();

        restTemplate.delete("/api/addresses/1");

        ResponseEntity<ValidationError[]> resultResponseEntity = restTemplate.getForEntity("/api/addresses/1",
                ValidationError[].class);

        ValidationError validationError = Arrays.stream(Objects.requireNonNull(resultResponseEntity.getBody()))
                .findFirst().get();

        assertThat(resultResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(validationError.getField()).contains("addressId");
        assertThat(validationError.getErrorMessage()).contains("Address with id 1 is not found.");
    }

    @BeforeEach
    void init() {
        restTemplate.postForEntity("/api/people",
                new PersonCreateCommand("János", "Tóth",
                        LocalDate.of(1995, Month.AUGUST, 10), "magyar"), PersonInfo.class);

        restTemplate.postForEntity("/api/people",
                new PersonCreateCommand("Enikő", "Tóth",
                        LocalDate.of(1987, Month.AUGUST, 25), "magyar"), PersonInfo.class);
    }
}
