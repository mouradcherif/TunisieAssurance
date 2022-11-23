package com.cherif.tunisieassurance;


public class ModelContract {

    private String id, ref, datedebut, datefin, redevence;

    public ModelContract(String id, String ref, String datedebut, String datefin, String redevence) {
        this.id = id;
        this.ref = ref;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.redevence = redevence;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public String getRedevence() {
        return redevence;
    }

    public void setRedevence(String redevence) {
        this.redevence = redevence;
    }
}