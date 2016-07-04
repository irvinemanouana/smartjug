package com.dev.christopher.smartjug.result;

/**
 * Created by Christopher on 04/07/16.
 */
public class NetworkResult {
    String libelle;
    String content;

    public NetworkResult(String libelle, String content) {
        this.libelle = libelle;
        this.content = content;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
