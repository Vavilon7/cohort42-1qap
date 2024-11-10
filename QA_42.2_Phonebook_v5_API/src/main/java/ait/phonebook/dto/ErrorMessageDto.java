package ait.phonebook.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//Описание класса: ErrorMessageDto — это DTO, которое используется для передачи информации об ошибках.
// Обычно содержит поле для сообщения об ошибке и, возможно, код ошибки.
//
//Методы:
//
//Геттеры и сеттеры для получения и установки сообщения об ошибке и кода ошибки.
@Getter
@Setter
@ToString
@Builder
public class ErrorMessageDto {
    private Object message;
    private String error;
    private String path;
    private String timestamp;
    private int status;
}