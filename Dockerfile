FROM adoptopenjdk/openjdk12:latest

RUN apt-get update && \
    apt-get install -y sqlite3

COPY . /ichnaea
WORKDIR /ichnaea

EXPOSE 7000
# When the container starts: build, test and run the app.
CMD ./gradlew build && ./gradlew test && ./gradlew run
