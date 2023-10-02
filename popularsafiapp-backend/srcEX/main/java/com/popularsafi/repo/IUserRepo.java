package com.popularsafi.repo;

import com.popularsafi.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepo extends IGenericRepo<User, String> {

    //@Query("FROM User u WHERE u.username = ?")
    //Derived Query
   @Query(value = "select * from TAB_USUARIO where C_USUARIO_ID like '%'||:username||'%'",nativeQuery = true)
    User findByUsernameLike(@Param("username") String username);
}
