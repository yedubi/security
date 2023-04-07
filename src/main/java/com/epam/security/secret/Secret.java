package com.epam.security.secret;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="SECRET")
public class Secret {

    @Id
    @Column(name="SECRET_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name="SECRET_LINK")
    private String link;

    @NonNull
    @Column(name="SECRET_DATA")
    private String text;

    @NonNull
    @Column(name="VIEWED")
    private boolean viewed;

    public Secret(String link, String text, boolean viewed) {
        this.link = link;
        this.text = text;
        this.viewed = viewed;

    }
}
