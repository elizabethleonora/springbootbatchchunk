package com.elizabeth.dao;

import com.elizabeth.entity.UserFile;

public interface UserFileDao {

    long create(String fileName);

    UserFile read(String fileName);

    int updateStatus(long fileId, String status);

}
