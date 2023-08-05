package com.example.SpringBootKeyCloakIntegration.filter;

import com.example.SpringBootKeyCloakIntegration.exception.AuthException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtClaimsValidationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest req, @NonNull HttpServletResponse res, @NonNull FilterChain filterChain) throws ServletException, IOException {

        try{

        if (!req.getHeader("Authorization").isEmpty() && req.getHeader("Authorization") != null) {
            String token = req.getHeader("Authorization");
            String accessToken = token.replaceAll("Bearer", "");

            if (!accessToken.isEmpty() && !accessToken.isBlank()) {
                String[] splitToken = accessToken.split("\\.");
                String tokenHeader = splitToken[0];
                String tokenBody = splitToken[1];
                String tokenSignature = splitToken[2];

                if (tokenHeader.isBlank() || tokenBody.isBlank() || tokenSignature.isBlank()) {
                    throw new AuthException(401,"Tampered Token");
                }

                Base64 base64url = new Base64(true);
                JSONObject jsonHeader = new JSONObject(new String(base64url.decode(tokenHeader)));
                if (jsonHeader.get("alg").toString().equals("none")) {
                    throw new AuthException(401,"Tampered Token");
                }


                JSONObject jsonBody = new JSONObject(new String(base64url.decode(tokenBody)));
                Date tokenExpAt = new Date(Long.parseLong(String.valueOf(jsonBody.get("exp"))) * 1000);

                if (tokenExpAt.getTime() <= System.currentTimeMillis()) {
                    throw new AuthException(401,"Token Expired");
                }

            } else throw new AuthException(401,"Empty Token.!");

        } else throw new AuthException(401,"UnAuthorized.!");

        filterChain.doFilter(req, res);
    }catch (AuthException e){
            res.setStatus(e.getStatusCode());
            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            res.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            res.getWriter().write(String.format("{\"errorCode\": %d, \"errorMessage\": \"%s\"}",e.getStatusCode(),e.getStatusMsg()));
            res.getWriter().flush();
            res.getWriter().close();
        }
    }
}
