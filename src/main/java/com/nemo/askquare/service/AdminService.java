package com.nemo.askquare.service;

import com.nemo.askquare.model.Account;

import java.util.List;

public interface AdminService {

    List<Account> findAllAccounts();

    List<Account> findAllAccountsOffset(int limit, int offset);

    int deleteRole(long id);

    int deleteAccount(long id);

    Account save(Account account);

    void updateAccount(String login, String password, String firstName, String secondName, String lastName, long id);

}
