package com.epam.security.secret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SecretServices {

    @Autowired
    private SecretRepository secretRepository;

    public void save(Secret secret) {
        secretRepository.save(secret);
    }

    public Secret getSecret(String link) {
        return secretRepository.findByLinkAndViewedIsFalse(link);
    }

    public void delete(Secret secret) {
        secretRepository.delete(secret);

    }
}
