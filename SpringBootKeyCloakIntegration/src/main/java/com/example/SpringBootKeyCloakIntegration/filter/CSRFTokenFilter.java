package com.example.SpringBootKeyCloakIntegration.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CSRFTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest req,@NonNull HttpServletResponse res,@NonNull FilterChain filterChain)
                                                                                                                        throws ServletException, IOException {
        CsrfToken csrfToken = (CsrfToken) req.getAttribute(CsrfToken.class.getName());
        if (csrfToken.getHeaderName() !=null){
            res.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
        }
        filterChain.doFilter(req,res);
    }
}
