package dev.gva.ui;

import dev.gva.core.RRBufferReader;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.springframework.stereotype.Component;




@Component
public class RadioBox extends HBox {

    private String station;

    public RadioBox(){

        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(2,8,2,2));
        setMaxWidth(210);
        setMinWidth(210);
        getStyleClass().add("radioBox");
    }

    void setup(Image image, String station, String name){
        ImageView iv = new ImageView(image);
        Label l = new Label(name);
        l.getStyleClass().add("label");
        getChildren().addAll(iv, l  );
        setStation(station);

        setOnMouseClicked(event ->{
            //change style
            if( getStyleClass().contains("radioBox") ){
                setUserData("http://air2.radiorecord.ru:805/" + station + "_" + MainController.bitRate);

                getStyleClass().remove("radioBox");
                getStyleClass().add("selectedRadioBox");

                RadioBox ud = (RadioBox) getParent().getUserData();
                if(ud != null){
                    ud.getStyleClass().remove("selectedRadioBox");
                    ud.getStyleClass().add("radioBox");
                    Thread.getAllStackTraces().forEach((t,s)->{
                        if(t.getName().equals(ud.getUserData())){
                            t.stop();
                        }
                    });
                }
                getParent().setUserData(this);


                Task task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        new RRBufferReader((String) getUserData()).play();
                        return null;
                    }
                };

                Thread th = new Thread(task);
                th.setName((String)getUserData());
                th.start();
            }else {
                getStyleClass().remove("selectedRadioBox");
                getStyleClass().add("radioBox");
                Thread.getAllStackTraces().forEach((t,s)->{
                    if(t.getName().equals(getUserData())){
                        t.stop();
                    }
                });

                getParent().setUserData(null);
            }
        });
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
