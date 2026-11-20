package software.ulpgc.katas;

import com.google.gson.Gson;
import io.javalin.Javalin;
import software.ulpgc.katas.adapters.endpoints.PeopleEndpoint;
import software.ulpgc.katas.io.TsvPersonReader;
import software.ulpgc.katas.service.PeopleService;
import software.ulpgc.katas.service.PersonReport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class PeopleWebServer {
    static void main(String[] args) {
        try (var input = Files.newInputStream(Path.of("people.tsv"))) {
            var result = new TsvPersonReader().readAll(input);
            var report = PersonReport.from(result);
            var service = new PeopleService(report);
            Javalin.create(config ->
                    new PeopleEndpoint(service, new Gson()).register(config))
                    .start(8081);
        } catch (IOException e) {
            System.err.println("No se pudo leer people.tsv: " + e.getMessage());
        }
    }
}
