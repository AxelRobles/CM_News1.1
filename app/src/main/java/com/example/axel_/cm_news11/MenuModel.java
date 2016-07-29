package com.example.axel_.cm_news11;

import android.graphics.Bitmap;

/**
 * Created by axel_ on 30/05/2016.
 */
public class MenuModel {
    Bitmap icono=null;
    String option;
    String id;


    public void setIcono(Bitmap icono) {
        this.icono = icono;
    }

    public void setOption(String option) {
        this.option = option;
        createIdSec(option);
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bitmap getIcono() {
        return icono;
    }

    public String getOption() {
        return option;
    }

    public String getId() {
        return id;
    }

    public void createIdSec(String a){

        switch (a){
            case "Portada":
                id="1";
                break;
            case "Nacional":
                id="2";
                break;
            case"Internacional":
                id="3";
                break;
            case "Deportes":
                    id="4";
                break;
            case "Espectaculos":
                id="5";
                break;
            case "Guardados":
                id="1";///****************************aqui cambio
                break;

        }

    }
}
