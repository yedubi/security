package com.epam.security.auth;

import com.epam.security.services.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final LoginAttemptService loginAttemptService;

    @Autowired
    private MessageSource messages;

    @Autowired
    private LocaleResolver localeResolver;


    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException, ServletException {

        super.setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);

//        final Locale locale = localeResolver.resolveLocale(request);
//        String errorMessage1 = messages.getMessage("message.badCredentials", null, locale);

        String userName = request.getParameter("username");

        String errorMessage = "Bad credentials";

        if (loginAttemptService.isBlocked(userName)) {
            errorMessage = "User blocked";
        }

        request.getSession()
                .setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);

    }
}