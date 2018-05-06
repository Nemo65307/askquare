package com.nemo.askquare.repository;

import com.nemo.askquare.annotation.Delete;

public interface RoleRepository {

    @Delete(sql = "delete from role where id=?")
    int deleteRole(long id); // TODO is a test method. to remove later.

}
