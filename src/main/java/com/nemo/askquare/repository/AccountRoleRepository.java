package com.nemo.askquare.repository;

import com.nemo.askquare.annotation.Insert;
import com.nemo.askquare.annotation.Select;
import com.nemo.askquare.model.AccountRole;

public interface AccountRoleRepository {

    @Select(sql = "select * from account_role where id_account=?")
    AccountRole findAccountRole(long accountId);

    @Insert(sql = "insert into account_role values(nextval('account_role_id_seq'), ?, ?)")
    AccountRole save(AccountRole accountRole);

}
