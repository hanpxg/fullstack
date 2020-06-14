package com.ibm.w3.service.impl;

import com.ibm.w3.dao.UserDao;
import com.ibm.w3.entity.UserInfo;
import com.ibm.w3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	public UserInfo getUserByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	public UserInfo saveUserInfo(UserInfo userInfo) {
		return userDao.save(userInfo);
	};
	
	public int setConfirmedByUsername(String username, String confirmed) {
		return userDao.saveUsersByUsernameAndConfirmed(username, "1");
	};
	
	public int setUpdatetsByUsername(String username, Date updatets) {
		return userDao.saveUsersByUsernameAndUpdatets(username, updatets);
	};

	public UserInfo getUserByUsernameAndPassword(String username, String password) {
		return userDao.findByUsername(username);
	}
    
}