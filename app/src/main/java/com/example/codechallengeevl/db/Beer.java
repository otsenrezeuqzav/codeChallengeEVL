package com.example.codechallengeevl.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "beerTD")
public class Beer {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "tagline")
    public String tagline;
    @ColumnInfo(name = "first_brewed")
    public String first_brewed;
    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "image_url")
    public String image_url;
    @ColumnInfo(name = "abv")
    public String abv;
    @ColumnInfo(name = "ibu")
    public String ibu;
    @ColumnInfo(name = "target_fg")
    public String target_fg;
    @ColumnInfo(name = "target_og")
    public String target_og;
    @ColumnInfo(name = "ebc")
    public String ebc;
    @ColumnInfo(name = "srm")
    public String srm;
    @ColumnInfo(name = "ph")
    public String ph;
    @ColumnInfo(name = "attenuation_level")
    public String attenuation_level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getFirst_brewed() {
        return first_brewed;
    }

    public void setFirst_brewed(String first_brewed) {
        this.first_brewed = first_brewed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public String getTarget_fg() {
        return target_fg;
    }

    public void setTarget_fg(String target_fg) {
        this.target_fg = target_fg;
    }

    public String getTarget_og() {
        return target_og;
    }

    public void setTarget_og(String target_og) {
        this.target_og = target_og;
    }

    public String getEbc() {
        return ebc;
    }

    public void setEbc(String ebc) {
        this.ebc = ebc;
    }

    public String getSrm() {
        return srm;
    }

    public void setSrm(String srm) {
        this.srm = srm;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getAttenuation_level() {
        return attenuation_level;
    }

    public void setAttenuation_level(String attenuation_level) {
        this.attenuation_level = attenuation_level;
    }
}
