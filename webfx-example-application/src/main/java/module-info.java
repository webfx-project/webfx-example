// File managed by WebFX (DO NOT EDIT MANUALLY)

module webfx.example.application {

    // Direct dependencies modules
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;

    // Exported packages
    exports org.example.webfxexample;

    // Provided services
    provides javafx.application.Application with org.example.webfxexample.WebFxExampleApplication;

}