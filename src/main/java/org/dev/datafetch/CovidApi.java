package org.dev.datafetch;

import com.google.gson.JsonObject;
import org.dev.datafetch.model.CountryData;
import org.dev.datafetch.model.GlobalData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CovidApi {

    // la requête get pour avoir toutes les infos
    @GET("https://coronavirus-19-api.herokuapp.com/all")
    // Récupération des données
    Call<GlobalData> getGlobalData();


    //La requête pour avoir uniquement le pays qui nous intéresse
    @GET("https://coronavirus-19-api.herokuapp.com/countries/{countryName}")
    // On revoie un paramètre
    Call<CountryData> getCountryData(@Path(value = "countryName") String countryName);

}
