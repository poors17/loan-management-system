FROM openjdk:21

WORKDIR /app

COPY . .

RUN chmod +x mvnw && ./mvnw clean install

CMD java -jar target/*.jar