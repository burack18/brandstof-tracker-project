package com.example.brandstoftracker.config.filter;

import com.example.brandstoftracker.security.JWTTokenProvider;
import com.example.brandstoftracker.security.UserPrincipalManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private UserPrincipalManager userManager;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getMethod().equalsIgnoreCase("OPTIONS")){
            response.setStatus(HttpStatus.OK.value());
        }else {
            String authorizationHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
            try {
                if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer "))
                {
                    String jwtToken= authorizationHeader.substring("Bearer ".length());
                    String name=jwtTokenProvider.getSubjectFromToken(jwtToken);
                    List<GrantedAuthority> authorityList= jwtTokenProvider.getAuthorities(jwtToken);
                    UserDetails user = userManager.loadUserByUsername(name);
                    if(user!=null){
                        Authentication aut=jwtTokenProvider.getAuthentication(user,user.getUsername(),authorityList,request);
                        SecurityContextHolder.getContext().setAuthentication(aut);
                    }
                }
            }catch (Exception e){
                return;
            }
            filterChain.doFilter(request,response);
        }
    }
}