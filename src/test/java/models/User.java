package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    long id;
    String name;
    String username;
    String email;
    Address address;
    String phone;
    String website;
    Company company;
}

