package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import main.CsvReader;
import model.Area;
import model.ProvinceData;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private StackedBarChart stackedBarChart;
    @FXML
    private ListView<String> provincesListView;
    @FXML
    private ListView<String> areasListView;

    private List<Area> areas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        areas = CsvReader.readData();
        stackedBarChart.setAnimated(false);

        setSelectionMode();
        setProvinceListView();
        setAreasListView();
    }

    @FXML
    public void onProvinceClick(MouseEvent event) {
        refreshChart();
    }

    @FXML
    public void onAreaClick(MouseEvent event) {
        refreshChart();
    }

    private void setChart() {
        ObservableList<String> selectedAreas = areasListView.getSelectionModel().getSelectedItems();
        ObservableList<String> selectedProvinces = provincesListView.getSelectionModel().getSelectedItems();

        List<XYChart.Series> seriesList = new ArrayList<>();
        for (String areaName : selectedAreas) {
            XYChart.Series dataSeries = new XYChart.Series();
            dataSeries.setName(areaName);
            seriesList.add(dataSeries);
        }

        for (XYChart.Series series : seriesList) {
            String areaName = series.getName();
            for (Area area : areas) {
                if (areaName.equals(area.getName())) {
                    for (ProvinceData province : area.getProvinceData()) {
                        if (selectedProvinces.contains(province.getProvince())) {
                            series.getData().add(new XYChart.Data(province.getProvince(), province.getValue2014()));
                        }
                    }
                }
            }
        }

        stackedBarChart.getData().addAll(seriesList);
    }

    private void refreshChart() {
        stackedBarChart.getData().clear();
        setChart();
    }

    private void setProvinceListView() {
        List<ProvinceData> provinceDataList = areas.get(0).getProvinceData();
        for (int i = 1; i < provinceDataList.size(); i++) {
            provincesListView.getItems().add(provinceDataList.get(i).getProvince());
        }
    }

    private void setAreasListView() {
        for (Area area : areas) {
            areasListView.getItems().add(area.getName());
        }
    }

    private void setSelectionMode() {
        provincesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        areasListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
