module org.sysinfo.morpion {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires com.almasb.fxgl.all;

  opens org.sysinfo.morpion to javafx.fxml;
  exports org.sysinfo.morpion;
    exports org.sysinfo.morpion.controller;
    opens org.sysinfo.morpion.controller to javafx.fxml;
}