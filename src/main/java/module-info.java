module fx.studentmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens fx.studentmanagementsystem to javafx.fxml;
    exports fx.studentmanagementsystem;
    exports fx.studentmanagementsystem.controller;
    opens fx.studentmanagementsystem.controller to javafx.fxml;
}