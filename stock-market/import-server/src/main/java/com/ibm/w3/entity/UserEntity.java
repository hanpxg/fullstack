package com.ibm.w3.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="user")
@Entity
public class UserEntity {
    /*
    1.	Id
2.	Username
3.	Password
4.	UserType(if Admin or normal User)
5.	E-mail
6.	Mobile number
7.	Confirmed
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private Boolean type;
    @Column
    private String email;
    @Column
    private String mobileNumber;
    @Column
    private Boolean confirmed;
}
