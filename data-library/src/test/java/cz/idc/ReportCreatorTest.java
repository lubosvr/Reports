package cz.idc;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class ReportCreatorTest {

    @Test
    void check() {
        List<Computer> computerList = Arrays.asList(
                new Computer("cz", "1", "v1", new BigDecimal(1)),
                new Computer("cz", "2", "v1", new BigDecimal(1)),
                new Computer("xx", "1", "v1", new BigDecimal(1)),
                new Computer("cz", "1", "v2", new BigDecimal(2))
        );
        List<ReportLine> expectedLines = Arrays.asList(
                new ReportLine("v1", new BigDecimal(1), new BigDecimal(".333")),
                new ReportLine("v2", new BigDecimal(2), new BigDecimal(".667"))
        );
        ReportCreator reportCreator = new ReportCreator();
        Report report = reportCreator.createReport("cz", "1", computerList);
        assertEquals(expectedLines, report.getLines());
    }

}