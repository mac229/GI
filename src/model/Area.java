package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inf109782 on 12.12.2016.
 */
public class Area {

    private int id;
    private String name;
    private List<ProvinceData> provinceData = new ArrayList<>();

    public Area(String id, String name) {
        this.id = Integer.parseInt(id);
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ProvinceData> getProvinceData() {
        return provinceData;
    }

    public void addProvinceData(ProvinceData provinceData) {
        this.provinceData.add(provinceData);
    }
}
