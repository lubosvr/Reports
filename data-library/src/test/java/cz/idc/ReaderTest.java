package cz.idc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class ReaderTest {

    private Reader<Computer> reader = new Reader<>(Computer.class);

    @Test
    void checkConstructor() {
        URL systemResource = ClassLoader.getSystemResource("data.csv");
        Optional<List<Computer>> computers = reader.read(systemResource.getFile());
        assertTrue(computers.isPresent());
        List<Computer> computerList = computers.get();
        assertEquals( 28, computerList.size(), "size should be 28");
        Computer firstComputer = computerList.get(0);
        assertEquals( "Czech Republic", firstComputer.getCountry(), "country wrongly parsed");
        assertEquals( "2010 Q3", firstComputer.getTimescale(), "timescale wrongly parsed");
        assertEquals( "Fujitsu Siemens", firstComputer.getVendor(), "vendor wrongly parsed");
        assertEquals( new BigDecimal("2924.742632"), firstComputer.getUnits(), "units wrongly parsed");
    }

}