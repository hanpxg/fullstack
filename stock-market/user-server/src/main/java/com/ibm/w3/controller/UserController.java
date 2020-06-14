package com.ibm.w3.controller;

import com.ibm.w3.Constant.Const;
import com.ibm.w3.entity.UserInfo;
import com.ibm.w3.model.UserInfoList;
import com.ibm.w3.service.MailService;
import com.ibm.w3.service.UserService;
import com.ibm.w3.utils.BeanUtilsCopy;
import com.ibm.w3.utils.CommonResult;
import com.ibm.w3.utils.ResponseBean;
import com.ibm.w3.view.UpdatePasswordView;
import com.ibm.w3.view.UpdateProfileView;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.OK;

@RestController

public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Value("${spring.mail.loginlink}")
	private String loginlink;

	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;

	@PostMapping("/signup")
	public ResponseEntity<CommonResult> signup(@RequestBody UserInfoList userInfoList) throws Exception {
		UserInfo userInfo = new UserInfo();
		userInfoList.setConfirmed("0");
		//	userInfoList.setUsertype("1"); //user type =1, admin = 0
		BeanUtilsCopy.copyPropertiesNoNull(userInfoList, userInfo);

		try {
			userService.saveUserInfo(userInfo);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("db error");
			logger.error("db error >>> ", e.getMessage());
			return ResponseEntity.ok().body(CommonResult.build(Const.COMMONRESULT_ERROR_CODE, "User sign up failed, please check your enters, user name may have been registered, you can try another one!"));
		}

		try {
			// send email
			System.out.println(userInfoList.getEmail());
			System.out.println(userInfoList.getEmail());
			mailService.sendHTMLMail(userInfoList.getEmail(), userInfoList.getUsername());
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Send txt fail.");
			logger.error("html email send failed!", e.getMessage());
			return ResponseEntity.ok().body(CommonResult.build(Const.COMMONRESULT_ERROR_CODE, "Email sent failed due to net issue! Pleasae re-signup later, Thanks."));
		}

		return ResponseEntity.ok().body(CommonResult.build(Const.COMMONRESULT_OK_CODE, "A confirmation email have sent to you, please go to your mailbox to confirm first!"));

	}

	@GetMapping("/confirmed/{username}")
	public String activeUserByUsername(@PathVariable("username") String username) throws Exception {
		if(userService.setConfirmedByUsername(username, "1")>0) {
			System.out.println("Send html success.");
			logger.error("User have confirmed!");
			return "<a href='"+ loginlink +"'>please click here to login SMC system</a>";
		}
		return "User confirm action failed!";

	}

	@PostMapping("/updatepwd")
	public ResponseEntity<CommonResult> updatePassword(@RequestBody UpdatePasswordView view) {
		System.out.println("updatepwd");
		UserInfo user = userService.getUserByUsername(view.getUsername());
		String password = user.getPassword();
		System.out.println(password);
		System.out.println(view.getOldPassword());
		System.out.println(password.equals(view.getOldPassword()));
		if (password.equals(view.getOldPassword())) {
			user.setPassword(view.getPassword());
			userService.saveUserInfo(user);
			return ResponseEntity.ok().body(CommonResult.build(OK.value(), "Update password success."));
		}
		return ResponseEntity.ok().body(CommonResult.build(Const.COMMONRESULT_ERROR_CODE, "Update password Failed."));
	}

	@GetMapping("/profile/{name}")
	public ResponseEntity<CommonResult> profileById(@PathVariable("name") String name) {
		System.out.println("===================");
		UserInfo user = userService.getUserByUsername(name);
		UpdateProfileView view = new UpdateProfileView();
		view.setOld_username(user.getUsername());
		view.setName(user.getUsername());
		view.setType(user.getUsertype());
		view.setEmail(user.getEmail());
		view.setMobileNumber(user.getMobile());
		return ResponseEntity.ok().body(CommonResult.build(Const.COMMONRESULT_OK_CODE, "Get profile success", view));
//		return view;
	}


	@PostMapping("/updateprofile")
	public ResponseEntity<CommonResult> updateProfile(@RequestBody UpdateProfileView view) {
		UserInfo user = userService.getUserByUsername(view.getOld_username());
		user.setUsername(view.getName());
		user.setUsertype(view.getType());
		user.setEmail(view.getEmail());
		user.setMobile(view.getMobileNumber());
		userService.saveUserInfo(user);
		return ResponseEntity.ok().body(CommonResult.build(OK.value(), "Update profile success."));
	}

	@PostMapping("/settings")
	public ResponseEntity<CommonResult> updateUserInfoList(@RequestBody UserInfoList userInfoList) throws Exception {

		String username = userInfoList.getUsername();
		String oldpw = userInfoList.getPassword();
		String newpw = userInfoList.getNewpassword();
		if (StringUtils.isBlank(oldpw)) {
			return ResponseEntity.ok().body(CommonResult.build(Const.COMMONRESULT_ERROR_CODE, "Please enter old password!"));
		}
		if (StringUtils.isBlank(newpw)) {
			return ResponseEntity.ok().body(CommonResult.build(Const.COMMONRESULT_ERROR_CODE, "Please enter new password!"));
		}

		// validate old pw
		UserInfo oneuser = userService.getUserByUsernameAndPassword(username, oldpw);
		if (oneuser == null) {
			return ResponseEntity.ok().body(CommonResult.build(Const.COMMONRESULT_ERROR_CODE, "Your old password is not correct !"));
		}

		// update pw
		UserInfo userInfo = new UserInfo();
		BeanUtilsCopy.copyPropertiesNoNull(oneuser, userInfo);
		userInfo.setPassword(newpw);

		try {
			userService.saveUserInfo(userInfo);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Passwordw db error");
			logger.error("Passwordw db error >>> ", e.getMessage());
			return ResponseEntity.ok().body(CommonResult.build(Const.COMMONRESULT_ERROR_CODE, "Password change failed, make sure you following the password change rules!"));
		}

		// login, changepw, logout will update updatets column
		if(!(userService.setUpdatetsByUsername(username, new Date())>0)) {
			return ResponseEntity.ok().body(CommonResult.build(Const.COMMONRESULT_ERROR_CODE, "database error, please wait a moment and retry or contact with system admin!"));
		}

		try {
			// send email
			String email = oneuser.getEmail();
			mailService.sendNewPasswordEmail(email, newpw);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Send txt fail."+e.getMessage());
		}

		return ResponseEntity.ok().body(CommonResult.build(Const.COMMONRESULT_OK_CODE, "Password change successed, you can also find your new password in your mail box, please relogin with your new pasword!"));

	}

	@GetMapping("/logout/{username}")
	public ResponseEntity<CommonResult> logout(@PathVariable("username") String username) throws Exception {

		// login, changepw, logout will update Updatets column
		if(userService.setUpdatetsByUsername(username, new Date())>0)
			return ResponseEntity.ok().body(CommonResult.build(Const.COMMONRESULT_OK_CODE, "You have exited successfully!"));

		return ResponseEntity.ok().body(CommonResult.build(Const.COMMONRESULT_ERROR_CODE, "Logout failed!"));
	}

	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(UNAUTHORIZED)
	public ResponseEntity<ResponseBean> handleAuthentication401Exception(AuthenticationException exception) throws Exception {
		return ResponseEntity.status(UNAUTHORIZED)
				.body(new ResponseBean(UNAUTHORIZED.value(), UNAUTHORIZED.getReasonPhrase()).error(exception.getMessage()));
	}

	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(FORBIDDEN)
	public ResponseEntity<ResponseBean> handleAuthentication403Exception(AuthenticationException exception) throws Exception {
		return ResponseEntity.status(FORBIDDEN).body(new ResponseBean(FORBIDDEN.value(), FORBIDDEN.getReasonPhrase()).error(exception.getMessage()));
	}
}
