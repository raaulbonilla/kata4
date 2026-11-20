package software.ulpgc.katas.service;

import java.util.List;

public final class PeopleServiceCheck {
    static void main(String[] args) {
        var report = new PersonReport(
                List.of(
                        new PersonReport.Entry("first", "2017-01-01"),
                        new PersonReport.Entry("second", "2006-01-01")
                ),
                new PersonReport.Summary(2,0)
        );
        var service = new PeopleService(report);
        if (service.report().people().size() != 2) {
            throw new AssertionError();
        }
        if (service.findByName("FIRST").isEmpty()) {
            throw new AssertionError();
        }
        if (service.findByName("missing").isPresent()) {
            throw new AssertionError();
        }
        System.out.println("PeopleService: OK");
    }
}
