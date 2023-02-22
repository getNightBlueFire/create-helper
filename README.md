## Создано с помощью
Java™ SE Development Kit 11.0.5

[Spring Framework](https://spring.io/)

Git - управление версиями

GitHub - репозиторий

[Redis](https://redis.io/) - СУБД

[Telegram Bots](https://core.telegram.org/bots) - взаимодействие с Telegram

[Apache Maven](https://maven.apache.org/) - сборка, управление зависимостями

[Apache POI](https://poi.apache.org/) - создание файлов Word и Excel

[Lombok](https://projectlombok.org/) - упрощение кода, замена стандартных java-методов аннотациями

[Heroku](https://www.heroku.com/) - деплой, хостинг

Полный список зависимостей и используемые версии компонентов можно найти в ```pom.xml```

## Сборка и запуск
Перед сборкой необходимо создать бота с помощью [BotFather](https://t.me/botfather) и сохранить его имя и токен (они понадобятся для запуска)

Зарегистрировать webhook в Telegram - для этого нужно отправить в любом браузере ссылку вида:
```
https://api.telegram.org/bot<токен бота от Botfather>/setWebhook?url=<URL веб-приложения на Heroku>
```
