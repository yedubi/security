package com.epam.security.auth;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="AUTH_USER_GROUP")
@Data
public class AuthGroup {
    @Id
    @Column(name="AUTH_USER_GROUP_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="USERNAME")
    private String username;
    @Column(name="AUTH_GROUP")
    private String authGroup;

}
