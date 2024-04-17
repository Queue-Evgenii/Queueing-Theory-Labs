package ua.nure.tmo_lab_1_2_fx.controls;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import ua.nure.tmo_lab_1_2_fx.service.ErlangSystem;

public class ErlangChart {

    public ErlangChart(ErlangSystem sys) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Кількість каналів, k");
        yAxis.setLabel("Ймовірність, Pk");

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Розподіл Pk");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Pk");

        for (int k = 0; k <= sys.getServicesNumber(); k++) {
            series.getData().add(new XYChart.Data<>(k, sys.getPk(k)));
        }

        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(series);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


}
