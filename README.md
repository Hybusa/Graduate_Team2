## Бэк для сайта ADS.
Open API документация находится в фаиле openapi-new.yaml.
Фронт запускается через докер контейнер командной
"docker run --rm -p 3000:3000 ghcr.io/bizinmitya/front-react-avito:v1.19"

## Стек технологий
- Java 11
- Spring Boot
- Spring Security
- Mapstruct
- Hibernate
- PostgreSQL
- Liquibase
- Docker

## Функционал проекта
- Авторизация и аутентификация пользователей.
- Редактирование профиля пользователя
- Загрузка аватарок пользователя
- Распределение ролей между пользователями: пользователь и администратор.
- CRUD для объявлений на сайте: администратор может удалять или редактировать все объявления, а пользователи — только свои.
- Под каждым объявлением пользователи могут оставлять отзывы.
- В заголовке сайта можно осуществлять поиск объявлений по названию.
- Показывать и сохранять картинки объявлений.

