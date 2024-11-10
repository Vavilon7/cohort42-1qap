package ait.phonebook.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
//Описание класса: ContactsDto вероятно представляет собой коллекцию объектов ContactDto.
// Это может быть список контактов или другая структура, которая объединяет несколько объектов контактов.
//
//Методы:
//
//Добавление контакта — метод для добавления нового контакта в список.
//Получение всех контактов — метод для извлечения списка контактов.
@Getter
@Setter
@ToString
@Builder
public class ContactsDto {
    private List<ContactDto> contacts;
}