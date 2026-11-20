package software.ulpgc.katas.adapters.endpoints;

import com.google.gson.Gson;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import software.ulpgc.katas.service.PeopleService;
import software.ulpgc.katas.service.PersonReport;

public final class PeopleEndpoint {
    private final PeopleService service;
    private final Gson gson;

    public PeopleEndpoint(PeopleService service, Gson gson) {
        this.service = service;
        this.gson = gson;
    }

    public void register(JavalinConfig config) {
        config.routes.get("/people", context ->
                send(context, 200, service.report()));
        config.routes.get("/people/{name}", this::findByName);
    }

    private void findByName(Context context) {
        String name = context.pathParam("name").trim();
        if (name.isBlank()) {
            send(context, 400, new ErrorMessage("El nombre es obligatorio"));
            return;
        }
        var person = service.findByName(name);
        if (person.isEmpty()) {
            send(context, 404, new ErrorMessage("No existe la persona solicitada"));
            return;
        }
        send(context, 200, person.get());
    }

    private void send(Context context, int status, Object body) {
        context.status(status);
        context.contentType("application/json");
        context.result(gson.toJson(body));
    }

    private record ErrorMessage(String error) {}
}
