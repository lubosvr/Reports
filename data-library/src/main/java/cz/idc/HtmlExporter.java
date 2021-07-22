package cz.idc;

import java.math.BigDecimal;

public class HtmlExporter implements Exporter {

    private void printHeader(StringBuilder builder) {
        builder.append(" <tr>\n");
        builder.append("  <th>Vendor</th>\n");
        builder.append("  <th>Units</th>\n");
        builder.append("  <th>Share</th>\n");
        builder.append(" </tr>\n");
    }

    private void printRow(StringBuilder builder, ReportLine reportLine) {
        builder.append(" <tr>\n");
        builder.append("  <td>" + reportLine.getVendor() + "</td>\n");
        builder.append("  <td>" + reportLine.getUnits() + "</td>\n");
        builder.append("  <td>" + reportLine.getShare().multiply(new BigDecimal(100)).setScale(1) + " %</td>\n");
        builder.append(" </tr>\n");
    }

    private void printRows(StringBuilder builder, Report report) {
        report.getLines().forEach(reportLine -> printRow(builder, reportLine));
    }

    private void printFooter(StringBuilder builder, Report report) {
        builder.append(" <tr>\n");
        builder.append("  <td>Total</td>\n");
        builder.append("  <td>" + report.countUnits() + "</td>\n");
        builder.append("  <td>" + report.countShares().multiply(new BigDecimal(100)).setScale(0) + " %</td>\n");
        builder.append(" </tr>\n");
    }

    @Override
    public String convertToString(Report report) {
        StringBuilder builder = new StringBuilder();
        builder.append("<table border=\"1\">\n");
        printHeader(builder);
        printRows(builder, report);
        printFooter(builder, report);
        builder.append("</table>\n");

        return new String(builder);
    }
}
