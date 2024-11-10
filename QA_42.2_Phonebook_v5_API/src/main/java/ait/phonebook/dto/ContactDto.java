package ait.phonebook.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//Описание класса: ContactDto также DTO, предназначенный для передачи данных о контакте.
// Это могут быть такие поля, как имя, адрес электронной почты, телефонный номер и так далее.
//
//Методы:
//
//Конструкторы — используются для создания объекта контакта.
//Геттеры и сеттеры — методы для управления полями контакта, например, getEmail(), setPhoneNumber().
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