package ua.nure.tmo_lab_1_2_fx.controls;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class FlowChart {

    public FlowChart(float[] sequence, String name) {
        final NumberAxis xAxis = new NumberAxis(0, 24, 1);
        final NumberAxis yAxis = new NumberAxis(0, 5,1);
        xAxis.setLabel("N");
        yAxis.setLabel("x(tau)");

        final LineChart<Number, Number> lineChart =
                new LineChart<>(xAxis,yAxis);

        lineChart.setTitle("Chart");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(name);

        for (int i = 0; i < sequence.length; ++i) {
            series1.getData().add(new XYChart.Data(i, sequence[i]));
        }


        lineChart.getData().add(series1);

        Scene scene  = new Scene(lineChart,800,600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
