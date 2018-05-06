package com.nemo.askquare.repository;

import com.nemo.askquare.annotation.Insert;
import com.nemo.askquare.annotation.Select;
import com.nemo.askquare.model.AccountRegistration;

public interface AccountRegistrationRepository {

    @Insert(sql = "insert into account_registration values(?, ?)")
    AccountRegistration saveConfirmation(AccountRegistration accountRegistration);

    @Select(sql = "select * from account_registration where id=?")
    AccountRegistration findById(long id);

    @Select(sql = "select * from account_registration where code=?")
    AccountRegistration findByCode(String code);

}
