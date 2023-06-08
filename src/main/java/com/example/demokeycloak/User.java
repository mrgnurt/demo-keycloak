package com.example.demokeycloak;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "bb_user_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long userId;


    @Column(name = "user_name")
    private String userName;

    @Column(name = "login_pwd")
    private String loginPwd;
}
