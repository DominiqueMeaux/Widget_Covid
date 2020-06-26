package org.dev;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.dev.datafetch.DataProviderService;
import org.dev.datafetch.model.CovidDataModel;

public class Launch extends Application {
    // Coordonnées poiur le déplacement du widget
    private double xOffset;
    private double yOffset;

    @Override
    public void start(Stage primaryStage) throws Exception {


        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setOpacity(0);
        primaryStage.show();
        Stage secondaryStage = new Stage();
        secondaryStage.initStyle(StageStyle.UNDECORATED);
        // On intègre le secondaryStage au premier
        secondaryStage.initOwner(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/org/dev/gui/widget/widget.fxml"));
        Scene scene = new Scene(root);
        secondaryStage.setScene(scene);
        secondaryStage.show();

        // On définie l'écran pour le déplacement de la fenêtre

        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        secondaryStage.setX(visualBounds.getMaxX() - 25 - scene.getWidth());
        secondaryStage.setY(visualBounds.getMinY() + 25);

        // Drag and drop
        // Méthode Lambda
        scene.setOnMousePressed(event -> {
            xOffset = secondaryStage.getX() - event.getScreenX();
            yOffset = secondaryStage.getY() - event.getScreenY();
        });

        scene.setOnMouseDragged(event -> {
            secondaryStage.setX(event.getScreenX() + xOffset) ;
            secondaryStage.setY(event.getScreenY() + yOffset) ;
        });

    }
}
