package cz.idc;

import java.math.BigDecimal;

public class HtmlExporter implements Exporter {

    protected void printHeader(StringBuilder builder) {
        builder.append(" <tr>\n");
        builder.append("  <th>Vendor</th>\n");
        builder.append("  <th>Units</th>\n");
        builder.append("  <th>Share</th>\n");
        builder.append(" </tr>\n");
    }

    protected void printRow(StringBuilder builder, ReportLine reportLine) {
        builder.append(" <tr>\n");
        builder.append("  <td>").append(reportLine.getVendor()).append("</td>\n");
        builder.append("  <td>").append(reportLine.getUnits()).append("</td>\n");
        builder.append("  <td>").append(reportLine.getShare().multiply(new BigDecimal(100)).setScale(1, BigDecimal.ROUND_HALF_UP)).append(" %</td>\n");
        builder.append(" </tr>\n");
    }

    protected void printRows(StringBuilder builder, Report report) {
        report.getLines().forEach(reportLine -> printRow(builder, reportLine));
    }

    protected void printFooter(StringBuilder builder, Report report) {
        builder.append(" <tr>\n");
        builder.append("  <td>Total</td>\n");
        builder.append("  <td>").append(report.countUnits()).append("</td>\n");
        builder.append("  <td>").append(report.countShares().multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP)).append(" %</td>\n");
        builder.append(" </tr>\n");
    }

    @Override
    public String convertToString(Report report) {
        StringBuilder builder = new StringBuilder();
        builder.append("<table>\n");
        printHeader(builder);
        printRows(builder, report);
        printFooter(builder, report);
        builder.append("</table>\n");

        return new String(builder);
    }
}
