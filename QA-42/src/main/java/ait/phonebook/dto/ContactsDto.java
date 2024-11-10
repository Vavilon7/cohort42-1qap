package ait.phonebook.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class ContactsDto {
    private List<ContactDto> contacts;
}
