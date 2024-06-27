package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class Users implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String phone;

    private String password;

    private String avatar;

    private String username;

    private LocalDateTime lastLoginTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer isDeleted;
}
