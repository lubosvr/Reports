package cz.idc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Application {

    private static void writeToFile(String content) {
        String filename = ClassLoader.getSystemResource("data.html").getFile();
        File file = new File(filename);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("<html>");
            writer.write(content);
            writer.write("</html>");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] s) {
        HtmlExporter exporter = new HtmlExporter();
        ReportCreator reportCreator = new ReportCreator();
        Reader<Computer> reader = new Reader<>(Computer.class);

        Optional<List<Computer>> read = reader.read(ClassLoader.getSystemResource("data.csv").getFile());
        if (read.isPresent()) {
            Report report = reportCreator.createReport("Slovakia", "2010 Q4", read.get());
            String html = exporter.convertToString(report);
            writeToFile(html);
            System.out.println(html);
        }
    }
}
