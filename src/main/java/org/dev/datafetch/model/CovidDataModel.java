package org.dev.datafetch.model;

public class CovidDataModel {

    // Déclaration des objets
    private GlobalData globalData;
    private CountryData countryData;


    @Override
    // Transforme les Objets globalData et countryData
    public String toString() {
        return "CovidDataModel{\n" +
                "globaldata=" + globalData +
                "\n countryData=" + countryData +
                '}';
    }

    // Contructeur
    public CovidDataModel(GlobalData globalData, CountryData countryData) {
        this.globalData = globalData;
        this.countryData = countryData;
    }




    public GlobalData getGlobalData()
    {
        return globalData;
    }




    public CountryData getCountryData()
    {
        return countryData;
    }
}
