package com.example.codechallengeevl.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.codechallengeevl.model.BeersModel;
import java.util.List;

@Dao
public interface DaoBeer {
    @Insert
    void insertarBeer(Beer...beers);

    @Query("SELECT COUNT(id) FROM beerTD WHERE id= :idbeer")
    int getCount(int idbeer);

    @Query("SELECT id, name, tagline, first_brewed, description, image_url, abv, ibu, target_fg, target_og, ebc, srm, ph, attenuation_level FROM beerTD")
    List<BeersModel> obtenerData();
}
