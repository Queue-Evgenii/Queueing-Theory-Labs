module ua.nure.tmo_lab_1_2_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;


    opens ua.nure.tmo_lab_1_2_fx to javafx.fxml;
    exports ua.nure.tmo_lab_1_2_fx;
}