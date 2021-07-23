package cz.idc;

import java.util.List;
import java.util.Optional;

public class Application {

    public static void main(String[] s) {
        Exporter exporter = new HtmlExporter();
        ReportCreator reportCreator = new ReportCreator();
        Reader<Computer> reader = new Reader<>(Computer.class);

        Optional<List<Computer>> read = reader.read(ClassLoader.getSystemResource("data.csv").getFile());
        if (read.isPresent()) {
            Report report = reportCreator.createReport("Slovakia", "2010 Q4", read.get());
            Optional<ReportLine> acer = report.findVendor("Acer");
            System.out.println("Acer in Slovakia in 2010 Q4: " + (acer.isPresent() ? acer.get() : "no data"));
            System.out.println("Row with data for Acer in Slovakia in 2010 Q4: " + report.getRowOfVendor("Acer"));
            System.out.println("Report sorted by vendor: " + report.sortByVendor());
            System.out.println("Report sorted by unit values: " + report.sortByUnits());
            System.out.println("Report in HTML: " + exporter.convertToString(report));
        }
    }
}
