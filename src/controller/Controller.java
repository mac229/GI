package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import main.CsvReader;
import model.Area;
import model.ProvinceData;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private StackedBarChart stackedBarChart;
    @FXML
    private ListView<String> provincesListView;
    @FXML
    private ListView<String> areasListView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Area> areas = CsvReader.readData();

        setSelectionMode();
        setProvinceListView(areas);
        setAreasListView(areas);

        setCharts(areas);
    }

    private void setCharts(List<Area> areas) {
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Desktop");

        dataSeries1.getData().add(new XYChart.Data("2014", 567));
        dataSeries1.getData().add(new XYChart.Data("2015", 540));

        stackedBarChart.getData().add(dataSeries1);

        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("Phone");

        dataSeries2.getData().add(new XYChart.Data("2014", 65));
        dataSeries2.getData().add(new XYChart.Data("2015", 120));

        stackedBarChart.getData().add(dataSeries2);

        XYChart.Series dataSeries3 = new XYChart.Series();
        dataSeries3.setName("Tablet");

        dataSeries3.getData().add(new XYChart.Data("2014", 23));
        dataSeries3.getData().add(new XYChart.Data("2015", 36));

        stackedBarChart.getData().add(dataSeries3);
    }

    private void setProvinceListView(List<Area> areas) {
        List<ProvinceData> provinceDataList = areas.get(0).getProvinceData();
        for (int i = 1; i < provinceDataList.size(); i++) {
            provincesListView.getItems().add(provinceDataList.get(i).getProvince());
        }
    }

    private void setAreasListView(List<Area> areas) {
        for (Area area : areas) {
            areasListView.getItems().add(area.getName());
        }
    }

    private void setSelectionMode() {
        provincesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        areasListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
