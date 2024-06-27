package com.example.demo.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDetailsFormDTO {
    private String name;

    private String idNumber;

    private LocalDate idValidityBegin;

    private LocalDate idValidityEnd;

    private String occupation;

    private Long annualSalary;

    private String province;

    private String city;

    private String district;

    private String addressDetail;
}
