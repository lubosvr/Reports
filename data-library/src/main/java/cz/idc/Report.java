package cz.idc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Report {
    private final String country;
    private final String timescale;
    private final List<ReportLine> lines;
    private BigDecimal totalUnits = new BigDecimal(0);
    private BigDecimal totalShares = new BigDecimal(0);

    public Report(String country, String timescale, List<Computer> computerList) {
        this.country = country;
        this.timescale = timescale;
        this.lines = new ArrayList<>(computerList.size());
        fillReportLines(computerList);
    }

    public Report(String country, String timescale, List<ReportLine> reportLines, Comparator<ReportLine> comparator) {
        this.country = country;
        this.timescale = timescale;
        this.lines = new ArrayList<>(reportLines);
        lines.sort(comparator);
    }

    public String getCountry() {
        return country;
    }

    public String getTimescale() {
        return timescale;
    }

    public List<ReportLine> getLines() {
        return new ArrayList<>(lines);
    }

    public Report sortByVendor() {
        return new Report(country, timescale, lines, Comparator.comparing(ReportLine::getVendor));
    }

    public Report sortByUnits() {
        return new Report(country, timescale, lines, Comparator.comparing(ReportLine::getUnits));
    }

    public int getRowOfVendor(String vendor) {
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).getVendor().equals(vendor)) {
                return i + 1;
            }
        }
        return -1;
    }

    public Optional<ReportLine> findVendor(String vendor) {
        return lines.stream()
                .filter(reportLine -> reportLine.getVendor().equals(vendor))
                .findAny();
    }

    public BigDecimal countUnits() {
        return totalUnits;
    }

    public BigDecimal countShares() {
        return totalShares;
    }

    private void fillReportLines(List<Computer> computerList) {
        for (Computer computer : computerList) {
            totalUnits = totalUnits.add(computer.getUnits());
        }
        for (Computer computer : computerList) {
            ReportLine reportLine = new ReportLine(computer, totalUnits);
            lines.add(reportLine);
            totalShares = totalShares.add(reportLine.getShare());
        }
    }

    @Override
    public String toString() {
        return "Report{" +
                "country='" + country + '\'' +
                ", timescale='" + timescale + '\'' +
                ", lines=" + lines +
                ", totalUnits=" + totalUnits +
                ", totalShares=" + totalShares +
                '}';
    }
}
