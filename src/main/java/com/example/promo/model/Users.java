package com.example.promo.model;

import java.util.List;

public class Users {
    private List<String> userTypes;
    private List<String> sm;
    private List<String> dsm;
    private List<String> clinic;
    private List<String> me;
    private List<String> cbm;
    private List<String> patient; // ✅ Added this field

    // No-arg constructor
    public Users() {
    }

    public List<String> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(List<String> userTypes) {
        this.userTypes = userTypes;
    }

    public List<String> getSm() {
        return sm;
    }

    public void setSm(List<String> sm) {
        this.sm = sm;
    }

    public List<String> getDsm() {
        return dsm;
    }

    public void setDsm(List<String> dsm) {
        this.dsm = dsm;
    }

    public List<String> getClinic() {
        return clinic;
    }

    public void setClinic(List<String> clinic) {
        this.clinic = clinic;
    }

    public List<String> getMe() {
        return me;
    }

    public void setMe(List<String> me) {
        this.me = me;
    }

    public List<String> getCbm() {
        return cbm;
    }

    public void setCbm(List<String> cbm) {
        this.cbm = cbm;
    }

    public List<String> getPatient() {
        return patient;
    }

    public void setPatient(List<String> patient) {
        this.patient = patient;
    }
}
