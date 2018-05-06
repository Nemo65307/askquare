package com.nemo.askquare.model;

import com.nemo.askquare.annotation.Column;

public class AccountRegistration {

    @Column("id_account")
    private Long accountId;
    private String code;


    public AccountRegistration() {
    }

    public AccountRegistration(Long accountId, String code) {
        this.accountId = accountId;
        this.code = code;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
