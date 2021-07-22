package cz.idc;

import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByName;

public class Computer {
    @CsvBindByName
    private String country;

    @CsvBindByName
    private String timescale;

    @CsvBindByName
    private String vendor;

    @CsvBindByName
    private BigDecimal units;

    public Computer() {
    }

    public Computer(String country, String timescale, String vendor, BigDecimal units) {
        this.country = country;
        this.timescale = timescale;
        this.vendor = vendor;
        this.units = units;
    }

    public String getCountry() {
        return country;
    }

    public String getTimescale() {
        return timescale;
    }

    public String getVendor() {
        return vendor;
    }

    public BigDecimal getUnits() {
        return units;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Computer computer = (Computer) o;

        if (country != null ? !country.equals(computer.country) : computer.country != null) return false;
        if (timescale != null ? !timescale.equals(computer.timescale) : computer.timescale != null) return false;
        if (vendor != null ? !vendor.equals(computer.vendor) : computer.vendor != null) return false;
        return units != null ? units.equals(computer.units) : computer.units == null;
    }

    @Override
    public int hashCode() {
        int result = country != null ? country.hashCode() : 0;
        result = 31 * result + (timescale != null ? timescale.hashCode() : 0);
        result = 31 * result + (vendor != null ? vendor.hashCode() : 0);
        result = 31 * result + (units != null ? units.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Data{" +
                "country='" + country + '\'' +
                ", timescale=" + timescale +
                ", vendor='" + vendor + '\'' +
                ", units=" + units +
                '}';
    }
}
