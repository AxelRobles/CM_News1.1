package com.example.axel_.cm_news11;

import android.graphics.Bitmap;

import java.io.Serializable;

public class ModelNew implements Serializable {

    String id=null;
    String headline=null;
    String urlthumb=null;
    String urlimag=null;
    Bitmap image=null;
    String texto;

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setUrlimag(String urlimag) {
        this.urlimag = urlimag;
    }

    public void setUrlthumb(String urlthumb) {
        this.urlthumb = urlthumb;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getHeadline() {
        return headline;
    }

    public String getId() {
        return id;
    }

    public String getUrlimag() {
        return urlimag;
    }

    public String getUrlthumb() {
        return urlthumb;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getTexto() {
        return texto;
    }
}


