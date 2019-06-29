module PSServer {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;

    opens authserver;
    opens util;
    opens model.dto;
    opens model.dao;
    opens server;
}