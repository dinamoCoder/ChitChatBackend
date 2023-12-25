package com.chitchat.Services.IRepository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.chitchat.Models.User;




public interface ILoginRegisterRepository<T> extends MongoRepository<User,ObjectId>  {  
    @Query("{ 'userName' : ?0 }")
    User findByUserName(String userName);
   // User findByEmail(String Email);
    @Query("{ '_id' : ?0 }")
    User findByuserId(String userId);
}
