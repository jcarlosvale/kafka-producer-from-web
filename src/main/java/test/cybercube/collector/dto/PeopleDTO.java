package test.cybercube.collector.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
Represents the People Data from the request and the seed from the application.yml
 */
@Data
@NoArgsConstructor
@Builder
public class PeopleDTO {
    private String firstName;
    private String lastName;
    private Double baseSeed;
}