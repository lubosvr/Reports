package cz.idc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import com.opencsv.bean.CsvToBeanBuilder;

public class Reader<ROW> {

    private final Class<ROW> type;

    public Reader(Class<ROW> type) {
        this.type = type;
    }

    public Optional<List<ROW>> read(String filename) {
        try {
            List<ROW> computerRows = new CsvToBeanBuilder<ROW>(new FileReader(filename))
                    .withType(type)
                    .build()
                    .parse();
            return Optional.of(computerRows);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
