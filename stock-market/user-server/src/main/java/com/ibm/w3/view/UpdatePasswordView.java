package com.ibm.w3.view;

import lombok.Data;

@Data
public class UpdatePasswordView {
    private String username;
    private String oldPassword;
    private String password;
}
