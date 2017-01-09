package dev.gva;


import dev.gva.config.AbstractJavaFxApplicationSupport;
import dev.gva.config.ConfigurationControllers;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

@Lazy
@SpringBootApplication
public class Main extends AbstractJavaFxApplicationSupport {

    @Value("${ui.title:RRPlayer}")
    private String windowTitle;

    @Qualifier("mainView")
    @Autowired
    private ConfigurationControllers.View view;

    public void start(Stage window) throws Exception {
        window.setTitle(windowTitle);
        window.setScene(new Scene(view.getView()));
        window.getScene().getStylesheets().add("css/main.css");
        window.setResizable(false);
        window.getIcons().add(new Image("images/icon.png"));
        window.centerOnScreen();
        window.setOnCloseRequest(event -> System.exit(0));
        window.show();
    }

    public static void main(String[] args) {
        launchApp(Main.class,   args);
    }
}
