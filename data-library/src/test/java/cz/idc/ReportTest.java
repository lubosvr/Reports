package cz.idc;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportTest {

    private List<Computer> computerList;

    @BeforeEach
    public void setUp() {
        computerList = Arrays.asList(
                new Computer("Czech Republic", "2010 Q3", "Dell", new BigDecimal("2525.011404")),
                new Computer("Czech Republic", "2010 Q3", "Acer", new BigDecimal("2924.742632")),
                new Computer("Czech Republic", "2010 Q3", "Apple", new BigDecimal("0"))
        );
    }

    @Test
    void checkConstructor() {
        Report report = new Report("Czech Republic", "2010 Q3", computerList);

        assertEquals("Czech Republic", report.getCountry());
        assertEquals("2010 Q3", report.getTimescale());

        assertEquals(new BigDecimal("2525.011404"), report.getLines().get(0).getUnits());
        assertEquals(new BigDecimal("0.463"), report.getLines().get(0).getShare());
        assertEquals(new BigDecimal("2924.742632"), report.getLines().get(1).getUnits());
        assertEquals(new BigDecimal("0.537"), report.getLines().get(1).getShare());
    }

    @Test
    void checkSortByUnit() {
        Report report = new Report("Czech Republic", "2010 Q3", computerList);
        Report sortedReport = report.sortByUnits();

        assertEquals("Apple", sortedReport.getLines().get(0).getVendor());
    }

    @Test
    void checkSortByVendor() {
        Report report = new Report("Czech Republic", "2010 Q3", computerList);
        Report sortedReport = report.sortByVendor();

        assertEquals("Acer", sortedReport.getLines().get(0).getVendor());
    }

    @Test
    void checkGetRowOfVendor() {
        Report report = new Report("Czech Republic", "2010 Q3", computerList);

        assertEquals(2, report.getRowOfVendor("Acer"));
        assertEquals(-1, report.getRowOfVendor("XXX"));
    }

    @Test
    void checkFindVendor() {
        Report report = new Report("Czech Republic", "2010 Q3", computerList);

        assertEquals(Optional.of(new ReportLine("Acer", new BigDecimal("2924.742632"), new BigDecimal("0.537"))), report.findVendor("Acer"));
        assertEquals(Optional.empty(), report.findVendor("XXX"));
    }
}