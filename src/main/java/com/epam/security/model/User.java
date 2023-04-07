package com.epam.security.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="TEST_USER")
@Data
public class User {
    @Id
    @Column(name="USER_ID")
    private long id;
    @Column(name="USERNAME", nullable = false, unique = true)
    private String username;
    @Column(name="PASSWORD")
    private String password;
    @Column(name = "ACCOUNT_NON_LOCKED")
    private boolean accountNonLocked;
    @Column(name = "FAILED_ATTEMPT")
    private int failedAttempt;
    @Column(name = "LOCK_TIME")
    private Date lockTime;
}
