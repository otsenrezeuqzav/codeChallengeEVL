package com.example.codechallengeevl.model;

import java.io.Serializable;

public class BeersModel implements Serializable {
    private int id;
    private String name;
    private String tagline;
    private String first_brewed;
    private String description;
    private String image_url;
    private String abv;
    private String ibu;
    private String target_fg;
    private String target_og;
    private String ebc;
    private String srm;
    private String ph;
    private String attenuation_level;

    public BeersModel(int id, String name, String tagline, String first_brewed, String description, String image_url,
                      String abv, String ibu, String target_fg, String target_og, String ebc, String srm, String ph,
                      String attenuation_level) {
        this.id = id;
        this.name = name;
        this.tagline = tagline;
        this.first_brewed = first_brewed;
        this.description = description;
        this.image_url = image_url;
        this.abv = abv;
        this.ibu = ibu;
        this.target_fg = target_fg;
        this.target_og = target_og;
        this.ebc = ebc;
        this.srm = srm;
        this.ph = ph;
        this.attenuation_level = attenuation_level;

    }

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

    @Override
    public String toString() {
        return "BeersModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tagline='" + tagline + '\'' +
                ", first_brewed='" + first_brewed + '\'' +
                ", description='" + description + '\'' +
                ", image_url='" + image_url + '\'' +
                ", abv='" + abv + '\'' +
                ", ibu='" + ibu + '\'' +
                ", target_fg='" + target_fg + '\'' +
                ", target_og='" + target_og + '\'' +
                ", ebc='" + ebc + '\'' +
                ", srm='" + srm + '\'' +
                ", ph='" + ph + '\'' +
                ", attenuation_level='" + attenuation_level + '\'' +
                '}';
    }
}
