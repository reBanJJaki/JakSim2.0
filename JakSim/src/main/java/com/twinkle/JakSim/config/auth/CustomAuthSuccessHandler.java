package com.twinkle.JakSim.config.auth;

import com.twinkle.JakSim.model.service.account.LoginLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final LoginLogService loginLogService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        setDefaultTargetUrl("/");
        loginLogService.create(authentication.getName(), request.getRemoteAddr());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
