package cz.idc;

import java.io.IOException;
import java.io.Writer;

public class HtmlWriterExporter extends HtmlExporter {

    public void writeToWriter(Writer writer, Report report) throws IOException{
        writer.write("<html>");
        writer.write(convertToString(report));
        writer.write("</html>");
    }

    @Override
    public String convertToString(Report report) {
        return "<html>" + super.convertToString(report) + "</html>";
    }
}
