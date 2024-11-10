package ait.phonebook.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//Описание класса: ResponseMessageDto представляет ответ от сервера или системы.
// Может содержать сообщение о статусе, данные ответа и другую полезную информацию.
//
//Методы:
//
//Геттеры и сеттеры для управления данными ответа.
@Getter
@Setter
@ToString
@Builder
public class ResponseMessageDto {
    private String message;
}