package org.dev.gui.widget;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Window;
import org.dev.datafetch.DataProviderService;
import org.dev.datafetch.model.CountryData;
import org.dev.datafetch.model.CovidDataModel;
import org.dev.datafetch.model.GlobalData;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WidgetController implements Initializable {

    // Planifacateur ( sert a refaire des action à des temps prévu
    private ScheduledExecutorService executorService;
    @FXML
    public AnchorPane rootPane;
    @FXML
    public Text textGlobalReport, textCountryCode, textCountryReport;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialisation
        initializeSchedulerService();


    }


    // Méthode de planification du loadData avec un taux de 20s
    private void initializeSchedulerService() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::loadData, 0,20, TimeUnit.SECONDS);
    }

    private void loadData() {
        DataProviderService dataProviderService = new DataProviderService();
        CovidDataModel covidDataModel = dataProviderService.getData("France");
        // attention au processus de javaFX
        Platform.runLater(()->{
            // déploiement des données dans l'affichage
            inflateData(covidDataModel);
        });

    }


    private void inflateData(CovidDataModel covidDataModel) {
        GlobalData globalData = covidDataModel.getGlobalData();
        CountryData countryData = covidDataModel.getCountryData();
        // Intégration dans les fields
        textGlobalReport.setText(getFormatedData(globalData.getCases(), globalData.getRecovered(), globalData.getDeaths()));
        textCountryReport.setText(getFormatedData(countryData.getCases(), countryData.getRecovered(), countryData.getDeaths()));
        // redimentionnement du stage
        readjustStage(textCountryCode.getScene().getWindow());
    }



    // Méthode de redimentionnement du stage
    private void readjustStage(Window stage) {
        stage.sizeToScene();
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(visualBounds.getMaxX() - 25 - textCountryCode.getScene().getWidth());
        stage.setY(visualBounds.getMinY() + 25);
    }


    // Méthode de formatage du texte

    private String getFormatedData(long totalCases, long recoveredCases, long totalDeaths) {
        return String.format("Cas : %-8d | Guéris : %-7d | Morts : %-6d", totalCases, recoveredCases, totalDeaths);

    }


}
