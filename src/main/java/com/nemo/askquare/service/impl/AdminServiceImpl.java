package com.nemo.askquare.service.impl;

import com.nemo.askquare.annotation.Transactional;
import com.nemo.askquare.model.Account;
import com.nemo.askquare.repository.AccountRepository;
import com.nemo.askquare.repository.RoleRepository;
import com.nemo.askquare.service.AdminService;

import java.util.List;

class AdminServiceImpl implements AdminService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    AdminServiceImpl(AccountRepository accountRepository, RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional
    public List<Account> findAllAccountsOffset(int limit, int offset) {
        return accountRepository.findOffset(limit, offset);
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteRole(long id) {
        return roleRepository.deleteRole(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteAccount(long id) {
        return accountRepository.deleteAccount(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateAccount(String login, String password, String firstName, String secondName, String lastName, long id) {
        accountRepository.updateAccount(login, password, firstName, secondName, lastName, id);
    }

}
