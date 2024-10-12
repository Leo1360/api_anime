FROM openjdk:17-alpine
RUN mkdir -p /opt/api_anime
WORKDIR /opt/api_anime
COPY ./release/api_anime-v1.0.jar ./app.jar 
CMD [ "java", "-jar", "app.jar" ]