package ait.phonebook.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//Описание класса: AuthenticationBodyDto скорее всего является DTO (Data Transfer Object),
// который используется для передачи данных аутентификации. Это может включать такие поля,
// как имя пользователя, пароль или другие данные, необходимые для аутентификации.
//
//Методы:
//
//Конструкторы — если есть конструкторы, они используются для создания объекта этого класса.
//Геттеры и сеттеры — методы для получения и установки значений полей класса (например, getUsername(), setPassword()).
@Getter
@Setter
@ToString
@Builder
//тоже самое RegistrationBodyDto
public class AuthenticationBodyDto {
    private String username;
    private String password;
}