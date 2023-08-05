package com.example.SpringBootKeyCloakIntegration.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

public class JwtClaimsValidationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest req, @NonNull HttpServletResponse res, @NonNull FilterChain filterChain) throws ServletException, IOException {

        if (!req.getHeader("Authorization").isEmpty() && req.getHeader("Authorization")!=null){
            String token = req.getHeader("Authorization");
            String accessToken = token.replaceAll("Bearer","");

            if (!accessToken.isEmpty() && !accessToken.isBlank()){
                String[] splitToken= accessToken.split("\\.");
                String tokenHeader = splitToken[0];
                String tokenBody = splitToken[1];
                String tokenSignature = splitToken[2];

                if (tokenHeader.isBlank() || tokenBody.isBlank() || tokenSignature.isBlank()){
                    throw new ServletException("Tampered Token");
                }

                Base64 base64url = new Base64(true);
                JSONObject jsonHeader = new JSONObject(new String(base64url.decode(tokenHeader)));
                if (jsonHeader.get("alg").toString().equals("none")){
                    throw new ServletException("Tampered Token");
                }



                JSONObject jsonBody = new JSONObject(new String(base64url.decode(tokenBody)));
                Date tokenExpAt = new Date(Long.parseLong(String.valueOf(jsonBody.get("exp"))) * 1000);

                if (tokenExpAt.getTime()<= System.currentTimeMillis()){
                    throw new ServletException("Token Expired");
                }

            }else throw new ServletException("Empty Token.!");
        }else throw new ServletException("UnAuthorized.!");
     filterChain.doFilter(req,res);
    }
}
