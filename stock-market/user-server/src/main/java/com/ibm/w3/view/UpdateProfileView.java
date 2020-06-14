package com.ibm.w3.view;

import lombok.Data;

@Data
public class UpdateProfileView {
    private String old_username;
    private String name;
    private String type;
    private String email;
    private String mobileNumber;
}
