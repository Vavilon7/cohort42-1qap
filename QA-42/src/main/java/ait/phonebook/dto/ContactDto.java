package ait.phonebook.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ContactDto {
    private String id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String description;
}
