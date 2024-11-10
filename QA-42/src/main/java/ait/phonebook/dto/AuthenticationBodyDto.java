package ait.phonebook.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
//тоже самое RegistrationBodyDto
public class AuthenticationBodyDto {
    private String username;
    private String password;
}
