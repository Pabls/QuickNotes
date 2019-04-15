package com.ar4i.quicknotes.data.models;

public class UserVm {

    // region========================================FIELDS=========================================

    private String email;
    private String uid;

    // endregion-------------------------------------FIELDS-----------------------------------------

    public UserVm(){}

    public UserVm(String email, String uid) {
        this.email = email;
        this.uid = uid;
    }

    // region========================================SET/GET========================================

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }

    // endregion-------------------------------------SET/GET----------------------------------------

}
