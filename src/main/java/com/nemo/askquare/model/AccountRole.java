package com.nemo.askquare.model;

import com.nemo.askquare.annotation.Column;
import com.nemo.askquare.annotation.InsertIgnore;

public class AccountRole {

    @InsertIgnore
    private Long id;

    @Column("id_account")
    private Long accountId;

    @Column("id_role")
    private int roleId;

    public AccountRole() {
    }

    public AccountRole(Long accountId, int roleId) {
        this.accountId = accountId;
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
