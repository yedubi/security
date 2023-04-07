package com.epam.security.secret;

import com.epam.security.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretRepository extends JpaRepository<Secret, Long> {

    Secret findByLinkAndViewedIsFalse(String link);

}
