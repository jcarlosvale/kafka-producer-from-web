package test.cybercube.collector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/*
Represents the People Data from the request and the seed from the application.yml
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeopleDTO {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private Double baseSeed;
}
