# language: ru

@all @LoginPage @BeforeLogin
Функция: Login Page

#  Предыстория:
#    Если нет кнопки 'LOGIN' то нажимаем 'Sign Out'

  @loginPositive
  Сценарий: Проверка успешной авторизации
    * нажимаем на кнопку 'LOGIN' в заголовке
    * заполняем данные пользователя
      | Name     | manuel@gm.com |
      | Password | Manuel1234$   |
    * нажимаем на кнопку 'login'
    * проверяем наличие кнопки 'Sign Out'

  @loginPositive
  Сценарий: Проверка успешной авторизации TestUser c параметром
    * нажимаем на кнопку 'LOGIN' в заголовке
    * заполняем поля авторизации пользователем TestUser
    * нажимаем на кнопку 'login'
    * проверяем наличие кнопки 'Sign Out'

  @paramsLoginTest
  Структура сценария: Проверка успешной авторизации в параметризованном тесте
    * нажимаем на кнопку 'LOGIN' в заголовке
    * заполняем данные пользователя
      | Name     | <Name>     |
      | Password | <Password> |
    * нажимаем на кнопку 'login'
    * проверяем наличие кнопки 'Sign Out'
    Примеры:
      | Name          | Password    |
      | manuel@gm.com | Manuel1234$ |
      | manuel@gm.com | Manuel1234$ |
      | manuel@gm.com | Manuel1234$ |

  @negativeLoginTest
  Сценарий:  Проверка авторизации c неверным паролем
    * нажимаем на кнопку 'LOGIN' в заголовке
    * заполняем данные пользователя
      | Name     | manuel@gm.com |
      | Password | Manuel4$      |
    * нажимаем на кнопку 'login'
    * проверяем текст всплывающего уведомления - "Wrong email or password"
    * проверяем текст ошибки - "Login Failed with code 401"
    * проверяем текст ошибки "Login Failed with code 401"

  @negativeLoginTest
  Сценарий:  Проверка авторизации RandomUser c неверным паролем
    * нажимаем на кнопку 'LOGIN' в заголовке
    * заполняем поля авторизации пользователем RandomUser
    * нажимаем на кнопку 'login'
    * проверяем текст всплывающего уведомления - "Wrong email or password"
    * проверяем текст ошибки - "Login Failed with code 401"
    * проверяем текст ошибки "Login Failed with code 401"

  @registerTest
  Сценарий: Проверка успешной регистрации
    * нажимаем на кнопку 'LOGIN' в заголовке
    * заполняем данные пользователя с случайным e-mail
    * нажимаем на кнопку 'registration'
    * проверяем наличие кнопки 'Sign Out'

  @registerTest
  Сценарий: Проверка успешной регистрации пользователя
    * нажимаем на кнопку 'LOGIN' в заголовке
    * заполняем поля авторизации пользователем RandomUser
    * нажимаем на кнопку 'registration'
    * проверяем наличие кнопки 'Sign Out'
