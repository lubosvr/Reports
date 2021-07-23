package cz.idc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HtmlExporterTest {

    private Report report;


    @BeforeEach
    public void setUp() {
        List<Computer> computerList = Arrays.asList(
                new Computer("Czech Republic", "2010 Q3", "Dell", new BigDecimal("2525.011404")),
                new Computer("Czech Republic", "2010 Q3", "Acer", new BigDecimal("2924.742632"))
        );
        report = new Report("Czech Republic", "2010 Q3", computerList);
    }

    @Test
    void checkExporter() {
        HtmlExporter exporter = new HtmlExporter();
        String html = exporter.convertToString(report);
        String expected = "<table>\n" +
                " <tr>\n" +
                "  <th>Vendor</th>\n" +
                "  <th>Units</th>\n" +
                "  <th>Share</th>\n" +
                " </tr>\n" +
                " <tr>\n" +
                "  <td>Dell</td>\n" +
                "  <td>2525.011404</td>\n" +
                "  <td>46.3 %</td>\n" +
                " </tr>\n" +
                " <tr>\n" +
                "  <td>Acer</td>\n" +
                "  <td>2924.742632</td>\n" +
                "  <td>53.7 %</td>\n" +
                " </tr>\n" +
                " <tr>\n" +
                "  <td>Total</td>\n" +
                "  <td>5449.754036</td>\n" +
                "  <td>100 %</td>\n" +
                " </tr>\n" +
                "</table>\n";
        assertEquals(expected, html);
    }
}