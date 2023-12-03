FROM bellsoft/liberica-openjdk-alpine-musl:17
COPY /target/dungeons-*.jar /dungeon/dungeon.jar
ENTRYPOINT ["java", "-jar", "/dungeon/dungeon.jar"]