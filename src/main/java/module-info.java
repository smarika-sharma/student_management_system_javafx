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
    requires java.desktop;
    requires annotations;
    requires opencsv;

    opens fx.studentmanagementsystem to javafx.fxml;
    exports fx.studentmanagementsystem;
    exports fx.studentmanagementsystem.controller;
    opens fx.studentmanagementsystem.controller to javafx.fxml;
    exports fx.studentmanagementsystem.controller.Student;
    opens fx.studentmanagementsystem.controller.Student to javafx.fxml;
    exports fx.studentmanagementsystem.controller.Teacher to javafx.fxml;
    opens fx.studentmanagementsystem.controller.Teacher to javafx.fxml;
    opens fx.studentmanagementsystem.model to javafx.base;
    opens fx.studentmanagementsystem.service to javafx.base;
    exports fx.studentmanagementsystem.controller.Staff to javafx.fxml;
    opens fx.studentmanagementsystem.controller.Staff to javafx.fxml;
    exports fx.studentmanagementsystem.controller.Admin;
    opens fx.studentmanagementsystem.controller.Admin to javafx.fxml;
    exports fx.studentmanagementsystem.model;
    exports fx.studentmanagementsystem.service;


}
