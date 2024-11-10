package ait.phonebook.utils;

import java.security.SecureRandom;
import java.util.Random;
//Описание класса: Utils — это утилитарный класс, который может содержать вспомогательные
// методы для обработки данных или выполнения других задач, общих для проекта.
//
//Методы:
//
//Общие методы — могут включать методы для обработки строк, работы с датами и другие вспомогательные функции.
public class Utils {

    public static String getRandomEmail() {
        char[] chars = "0123456789abcdef".toCharArray();
        Random random = new SecureRandom();
        char[] result = new char[8];
        for (int i = 0; i < result.length; i++) {
            int randomIndex = random.nextInt(chars.length);
            result[i] = chars[randomIndex];
        }
        String email = new String(result) + "@test.com";
        return email;
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || "".equals(value);
    }
}