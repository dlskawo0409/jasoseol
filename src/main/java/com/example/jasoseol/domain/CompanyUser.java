package com.example.jasoseol.domain;

import com.example.jasoseol.Enum.ComePath;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
@DiscriminatorValue("COMPANY_USER")
public class CompanyUser extends User {

    @Column(name = "COMPANY_NUM")
    private String companyNum;

    @Column(name = "COMPANY_USER_NAME", nullable = false)
    private String companyUserName;

    @Column(name = "COMPANY_USER_PHONENUM", nullable = false)
    private String companyUserPhonenum;

    @Column(name = "COMEPATH", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ComePath comePath;

    @OneToOne(mappedBy = "companyUser")
    private Company company;


    @Builder(builderMethodName = "companyUserBuilder")
    public CompanyUser(String email, String password, String nickname, int marketing, int career, String role, String companyNum, String companyUserName, String companyUserPhonenum, ComePath comePath) {
        super(email, password, nickname, marketing, career, role);
        this.companyNum = companyNum;
        this.companyUserName = companyUserName;
        this.companyUserPhonenum = companyUserPhonenum;
        this.company = null;
        this.comePath = comePath;
    }
}
