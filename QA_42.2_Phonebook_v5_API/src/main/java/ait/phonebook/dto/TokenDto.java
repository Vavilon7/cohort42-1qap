package ait.phonebook.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//Описание класса: TokenDto предназначен для работы с токенами аутентификации.
// Это могут быть JWT токены или другие виды токенов для доступа к защищённым ресурсам.
//
//Методы:
//
//Геттеры и сеттеры для управления токенами.
@Getter
@Setter
@ToString
@Builder
public class TokenDto {
    private String token;
}