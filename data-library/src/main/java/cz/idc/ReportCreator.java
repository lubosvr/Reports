package cz.idc;

import java.util.List;
import java.util.stream.Collectors;

public class ReportCreator {

    public Report createReport(String country, String timescale, List<Computer> computerList) {
        List<Computer> filtered = computerList.stream()
                .filter(computer -> computer.getCountry().equals(country))
                .filter(computer -> computer.getTimescale().equals(timescale))
                .collect(Collectors.toList());
        return new Report(country, timescale, filtered);
    }
}
