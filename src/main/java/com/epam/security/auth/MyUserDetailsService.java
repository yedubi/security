package com.epam.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AuthGroupRepository authGroupRepository;
    private final LoginAttemptService loginAttemptService;
//    @Autowired
//    private HttpServletRequest request;

    public MyUserDetailsService(UserRepository userRepository, AuthGroupRepository authGroupRepository, LoginAttemptService loginAttemptService) {
        super();
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
        this.loginAttemptService = loginAttemptService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        if (loginAttemptService.isBlocked(username)) {
//            throw new UsernameNotFoundException("blocked");
//        }

        User user = this.userRepository.findByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("cannot find username: " + username);
        }
        List<AuthGroup> authGroups = this.authGroupRepository.findByUsername(username);
        return new UserPrincipal(user, authGroups);
    }

}
