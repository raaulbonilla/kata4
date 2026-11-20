package software.ulpgc.katas.service;

import java.util.Optional;

public final class PeopleService {
    private final PersonReport report;

    public PeopleService(PersonReport report) {
        this.report = report;
    }

    public PersonReport report() {
        return report;
    }

    public Optional<PersonReport.Entry> findByName(String name) {
        return report.people().stream()
                .filter(person -> person.name().equalsIgnoreCase(name))
                .findFirst();
    }
}
