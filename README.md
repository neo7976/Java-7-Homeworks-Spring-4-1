# Задача Dockerfile

Давайте соберем наш первый докер образ на основе нашего приложения авторизации, которое мы писали во [втором домашнем задании](../../spring_boot_rest/task1/README.md)(возьем чисто серверное нашего приложение без html из прошлого задания). Для этого мы сначала напишем наш Dockerfile, а затем, для удобства, напишем манифест для docker-compose

## Описание

1. Первым делом нам надо собрать jar архив с нашим spring boot приложением. Для этого в терминале в корне нашего проект выполните команду:

Для gradle: `./gradlew clean build` (если пишет Permission denied тогда сначала выполните `chmod +x ./gradlew`)

Для maven: `./mvnw clean package` (если пишет Permission denied тогда сначала выполните `chmod +x ./mvnw`)

2. Теперь можно начинать писать `Dockerfile`. Базовым образом возьмите `openjdk:8-jdk-alpine` и не забудьте открыть докеру порт(`EXPOSE`), на котором работает ваше приложение

3. Добавьте собранный jar в ваш образ(`ADD`). Если вы собирали с помощью maven, тогда jar будет лежать в папке `target`, а если gradle - в `build/libs`

4. Для удобства сборки образа и запуска контейнера нашего приложения, напишем `docker-compose.yml`. Контейнер назовите как вам больше нравится, а в его конфигурациях пропишите следующее:
- добавим `build: ./` который скажет docker-compose что надо сначала собрать образ для этого контейнера
- добавим соответствие порта на хост машине и порта в контейнере для нашего приложения (аналог аргумента `-p` у команды `docker run`)

5. Два полученных файла добавьте в репозиторий вашего приложения и пришлите ссылка на него.