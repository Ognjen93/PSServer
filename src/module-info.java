module PSServer {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;

    opens controller;
    opens main;
    opens mysql;
    opens model.dto;
    opens resursi;
    opens view;
}