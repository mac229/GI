package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import main.CsvReader;
import model.Area;
import model.Province;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    public static final List<String> stacked = Arrays.asList("Kina", "Muzea", "Teatry", "Galerie sztuki", "Ośrodki sportowe");
    public static final String STACKED = "Ośrodki kulturalne";

    @FXML
    private ListView<String> provincesListView;
    @FXML
    private GridPane gridPane;
    @FXML
    private ListView<String> areasListView;

    private List<Area> areas;
    private HashMap<String, StackedBarChart> charts = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        areas = CsvReader.readData();

        setSelectionMode();
        setProvinceListView();
        setAreasListView();
    }

    private StackedBarChart<String, Number> createChart(String id) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        StackedBarChart<String, Number> stackedBarChart = new StackedBarChart<>(xAxis, yAxis);
        stackedBarChart.setAnimated(false);
        stackedBarChart.setId(id);
        stackedBarChart.setMinSize(300, 400);

        stackedBarChart.setTitle(id);
        xAxis.setLabel("Województwa");
        return stackedBarChart;
    }

    private void setSize(StackedBarChart<String, Number> stackedBarChart) {
        switch (charts.size()) {
            case 1:
                stackedBarChart.setMaxSize(900, 800);
                return;
            case 2:
                stackedBarChart.setMaxSize(450, 800);
                return;
            case 3:
                stackedBarChart.setMaxSize(400, 400);
                return;
            case 4:
                stackedBarChart.setMaxSize(400, 400);
                return;
            case 5:
                stackedBarChart.setMaxSize(300, 400);
                return;
            case 6:
                stackedBarChart.setMaxSize(300, 400);
                return;
            default:
        }
    }

    @FXML
    public void onProvinceClick(MouseEvent event) {
        for (StackedBarChart chart : charts.values()) {
            refreshChart(chart);
        }
    }

    @FXML
    public void onAreaClick(MouseEvent event) {
        String areaName = areasListView.getSelectionModel().getSelectedItem();
        boolean contains = areasListView.getSelectionModel().getSelectedItems().contains(areaName);
        int size = areasListView.getSelectionModel().getSelectedItems().size();

        if (stacked.contains(areaName)) {
            areaName = STACKED;
        }

        if (size == 1) {
            for (Map.Entry<String, StackedBarChart> entry : charts.entrySet()) {
                if (!areaName.equals(entry.getKey())) {
                    gridPane.getChildren().remove(entry.getValue());
                }
            }
            StackedBarChart stackedBarChart = charts.get(areaName);
            charts.clear();
            if (stackedBarChart != null) {
                charts.put(areaName, stackedBarChart);
            }
        }

        if (contains) {
            checkedArea(areaName);
        } else {
            uncheckedArea(areaName);
        }

        for (StackedBarChart stackedBarChart : charts.values()) {
            setSize(stackedBarChart);
        }
    }

    private void uncheckedArea(String areaName) {
        StackedBarChart stackedBarChart = charts.get(areaName);
        gridPane.getChildren().remove(stackedBarChart);
        charts.remove(areaName);
    }

    private void checkedArea(String areaName) {
        if (charts.containsKey(areaName)) {
            StackedBarChart stackedBarChart = charts.get(areaName);
            refreshChart(stackedBarChart);
        } else {
            StackedBarChart<String, Number> chart = createChart(areaName);
            setChart(chart);
            charts.put(areaName, chart);
            insertToGridPane(chart);
        }
    }

    private void insertToGridPane(StackedBarChart<String, Number> chart) {
        switch (charts.size()) {
            case 1:
                gridPane.add(chart, 0, 0);
                return;
            case 2:
                gridPane.add(chart, 1, 0);
                return;
            case 3:
                gridPane.add(chart, 0, 1);
                return;
            case 4:
                gridPane.add(chart, 1, 1);
                return;
            case 5:
                gridPane.add(chart, 2, 0);
                return;
            case 6:
                gridPane.add(chart, 2, 1);
                return;
            default:
        }
    }

    private void setChart(StackedBarChart stackedBarChart) {
        ObservableList<String> selectedAreas = areasListView.getSelectionModel().getSelectedItems();
        ObservableList<String> selectedProvinces = provincesListView.getSelectionModel().getSelectedItems();

        List<XYChart.Series> seriesList = new ArrayList<>();
        if (STACKED.equals(stackedBarChart.getId())) {
            for (String areaName : selectedAreas) {
                if (stacked.contains(areaName)) {
                    XYChart.Series dataSeries = new XYChart.Series();
                    dataSeries.setName(areaName);
                    seriesList.add(dataSeries);
                }
            }
        } else {
            String id = stackedBarChart.getId();
            XYChart.Series dataSeries = new XYChart.Series();
            dataSeries.setName(id);
            seriesList.add(dataSeries);
        }

        for (XYChart.Series series : seriesList) {
            String areaName = series.getName();
            for (Area area : areas) {
                if (areaName.equals(area.getName())) {
                    for (Province province : area.getProvince()) {
                        if (selectedProvinces.contains(province.getProvince())) {
                            series.getData().add(new XYChart.Data(province.getProvince(), province.getValue2014()));
                        }
                    }
                }
            }
        }

        stackedBarChart.getData().addAll(seriesList);
    }

    private void refreshChart(StackedBarChart stackedBarChart) {
        stackedBarChart.getData().clear();
        setChart(stackedBarChart);
    }

    private void setProvinceListView() {
        List<Province> provinceList = areas.get(0).getProvince();
        for (int i = 1; i < provinceList.size(); i++) {
            provincesListView.getItems().add(provinceList.get(i).getProvince());
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
