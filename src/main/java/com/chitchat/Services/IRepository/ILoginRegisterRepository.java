package com.chitchat.Services.IRepository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.chitchat.Models.User;
import java.util.List;




public interface ILoginRegisterRepository<T> extends MongoRepository<User,String>  {  
    @Query("{ 'userName' : ?0 }")
    User findByUserName(String userName);
    User findByEmail(String Email);
    @Query("{ '_id' : ?0 }")
    User findByuserId(String userId);
}
