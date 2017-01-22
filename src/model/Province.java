package model;

/**
 * Created by Maciej on 2016-12-11.
 */
public class Province {

    private String province;
    private double value2005;
    private double value2010;
    private double value2013;
    private double value2014;

    public Province(String province, String value2005, String value2010, String value2013, String value2014) {
        this.province = province;
        try {
            if (!value2005.isEmpty()) {
                this.value2005 = Double.parseDouble(value2005);
            }
            if (!value2010.isEmpty()) {
                this.value2010 = Double.parseDouble(value2010);
            }
            if (!value2013.isEmpty()) {
                this.value2013 = Double.parseDouble(value2013);
            }
            this.value2014 = Double.parseDouble(value2014);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getProvince() {
        return province;
    }

    public double getValue2005() {
        return value2005;
    }

    public double getValue2010() {
        return value2010;
    }

    public double getValue2013() {
        return value2013;
    }

    public double getValue2014() {
        return value2014;
    }
}
