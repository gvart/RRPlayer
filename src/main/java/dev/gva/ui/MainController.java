package dev.gva.ui;

import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public  static Integer   bitRate     = 128;
    private        Image     settingsDef = new Image("images/settings_icon.png");
    private        Image     settingsIn  = new Image("images/settings_icon_hover.png");

    public         GridPane  gridPane;
    public         ImageView label;
    public         ImageView setting;
    public         RadioBox  rr,sd,tm,rus,mdl,chil,club,gop,vip,ps,brks,deep,dc,dub,trap,teo,rock,pump;
    public         HBox      topBox;



    public void initialize(URL location, ResourceBundle resources) {
        label.setImage(new Image("images/title_label.png"));

        setting.setImage(settingsDef);
        setting.getStyleClass().add("settings");
        setting.setOnMouseEntered(event -> setting.setImage(settingsIn));
        setting.setOnMouseExited(event -> setting.setImage(settingsDef));
        setting.setOnMouseClicked(event -> {
            ChoiceDialog<Integer> dialog = new ChoiceDialog<>(bitRate, new ArrayList<Integer>(){{add(64); add(128); add(320);}});

            dialog.setTitle("Bitrate Settings");
            dialog.setHeaderText("Choose bitrate");
            dialog.setContentText("Bitrate:");

            Optional<Integer> result = dialog.showAndWait();

            result.ifPresent(br -> {
                Thread.getAllStackTraces().forEach((t,s)->{
                    if(t.getName().contains(bitRate.toString())){
                        t.stop();
                    }

                    gridPane.getChildren().forEach(c ->{
                        if(c.getStyleClass().get(0).equals("selectedRadioBox")) {
                            c.getStyleClass().add("radioBox");
                        }
                    });
                });

                bitRate = br;
            });
        });

        Tooltip.install(setting, new Tooltip("Configure bitrate"));

        rr.setup(new Image("images/rr.png"), "rr", "Radio Record");
        sd.setup(new Image("images/sd.png"), "sd90", "Супердискотека 90-х");
        tm.setup(new Image("images/tm.png"), "tm", "Trancemission");
        rus.setup(new Image("images/rus.png"),"rus", "Russian Mix");
        mdl.setup(new Image("images/mdl.png"), "mdl", "Медляк FM");
        chil.setup(new Image("images/chill.png"), "chil", "Record Chill-Out");
        club.setup(new Image("images/club.png"), "club", "Record Club");
        gop.setup(new Image("images/gop.png"), "gop", "Гоп FM");
        vip.setup(new Image("images/vip.png"), "vip", "Vip Mix");
        ps.setup(new Image("images/ps.png"), "ps", "Pirate Station");
        deep.setup(new Image("images/deep.png"), "deep", "Record Deep");
        brks.setup(new Image("images/brks.png"), "brks", "Record Breaks");
        dc.setup(new Image("images/dc.png"), "dc", "Record Dancecore");
        dub.setup(new Image("images/dub.png"), "dub", "Record Dubstep");
        trap.setup(new Image("images/trap.png"), "trap", "Record Trap");
        teo.setup(new Image("images/teo.png"), "teo", "Record Hardstyle");
        rock.setup(new Image("images/rock.png"), "rock", "Record Hardstyle");
        pump.setup(new Image("images/pump.png"), "pump", "Pump'n'Klubb");

        gridPane.setUserData(null);
    }

    @PostConstruct
    public void init(){

    }


}
