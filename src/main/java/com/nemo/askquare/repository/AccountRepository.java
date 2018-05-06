package com.nemo.askquare.repository;

import com.nemo.askquare.annotation.*;
import com.nemo.askquare.model.Account;

import java.util.List;

public interface AccountRepository {

    @Select(sql = "select * from account where login=?")
    Account findByLogin(String login);

    @Select(sql = "select * from account where email=?")
    Account findByEmail(String email);

    @Select(sql = "select * from account where id=?")
    Account findById(long id);

    @Select(sql = "select * from account order by id")
    @ReturnType(entityClass = Account.class)
    List<Account> findAll();

    @Select(sql = "select * from account order by id limit ? offset ?")
    @ReturnType(entityClass = Account.class)
    List<Account> findOffset(int limit, int offset);

    @Delete(sql = "delete from account where id=?")
    int deleteAccount(long id);

    @Insert(sql = "insert into account values(nextval('account_id_seq'), ?, ?, ?, ?, ?, ?)")
    Account save(Account account);

    @Select(sql = "select count(*) from account")
    @ReturnType(entityClass = Long.class)
    Long count();

    @Update(sql = "update account set login=?, password=?, first_name=?, second_name=?, last_name=? where id=?")
    void updateAccount(String login, String password, String firstName, String secondName, String lastName, long id);

    @Update(sql = "update account set active=? where id=?")
    void updateActive(Boolean active, long id);

}
