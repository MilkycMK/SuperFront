module net.mlk.adolffront.adolffront {
    requires javafx.controls;
    requires javafx.fxml;


    opens net.mlk.adolffront to javafx.fxml;
    exports net.mlk.adolffront;
}