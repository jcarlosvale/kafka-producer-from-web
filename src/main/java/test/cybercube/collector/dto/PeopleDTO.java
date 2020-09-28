package test.cybercube.collector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
Represents the People Data from the request and the seed from the application.yml
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeopleDTO {

    @NotNull @NotNull @Size(min = 3)
    private String firstName;

    @NotNull @Size(min = 3)
    private String lastName;

    @NotNull @Min(18)
    private Integer age;

    private Double baseSeed;
}
