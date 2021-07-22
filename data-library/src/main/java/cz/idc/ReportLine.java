package cz.idc;

import java.math.BigDecimal;

public class ReportLine {
    private String vendor;
    private BigDecimal units;
    private BigDecimal share;

    ReportLine(String vendor, BigDecimal units, BigDecimal share) {
        this.vendor = vendor;
        this.units = units;
        this.share = share;
    }

    public ReportLine(Computer computer, BigDecimal total) {
        this.vendor = computer.getVendor();
        this.units = computer.getUnits();
        this.share = computer.getUnits().divide(total, 3, BigDecimal.ROUND_HALF_UP);
    }

    public String getVendor() {
        return vendor;
    }

    public BigDecimal getUnits() {
        return units;
    }

    public BigDecimal getShare() {
        return share;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReportLine that = (ReportLine) o;

        if (vendor != null ? !vendor.equals(that.vendor) : that.vendor != null) return false;
        if (units != null ? !units.equals(that.units) : that.units != null) return false;
        return share != null ? share.equals(that.share) : that.share == null;
    }

    @Override
    public int hashCode() {
        int result = vendor != null ? vendor.hashCode() : 0;
        result = 31 * result + (units != null ? units.hashCode() : 0);
        result = 31 * result + (share != null ? share.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReportLine{" +
                "vendor='" + vendor + '\'' +
                ", units=" + units +
                ", share=" + share +
                '}';
    }
}
