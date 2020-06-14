package com.ibm.w3.service;


import com.ibm.w3.entity.UserInfo;

import java.util.Date;


public interface UserService {
	UserInfo getUserByUsername(String username);
	UserInfo saveUserInfo(UserInfo userInfo);
    int setConfirmedByUsername(String username, String confirmed);
    int setUpdatetsByUsername(String username, Date updatets);
    UserInfo getUserByUsernameAndPassword(String username, String password);
    
	}