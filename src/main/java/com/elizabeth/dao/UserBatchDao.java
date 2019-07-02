package com.elizabeth.dao;

import com.elizabeth.entity.User;

import java.util.List;

public interface UserBatchDao {

    void createUser(List<User> users);

    void deleteUser(Long id);

    User getUser(Long id);

    List<User> listUsers();

}
