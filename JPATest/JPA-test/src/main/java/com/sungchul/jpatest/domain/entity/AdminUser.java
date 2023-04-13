package com.sungchul.jpatest.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id",length = 20)
    private String userId;

    @Column(name ="password",length = 200)
    private String password;

    @Column(name ="name",length = 20)
    private String name;

    @Column(name ="regDate",length = 8)
    private String regDate;

    @Column(name ="regTime",length = 6)
    private String regTime;

    @Column(name ="roleId",length = 15)
    private String roleId;

    @Column(name ="status",length = 1)
    private int status;

    @Column(name ="otp",length = 1)
    private int otp;

    @Column(name ="otpKey",length = 8)
    private String otpKey;
}
