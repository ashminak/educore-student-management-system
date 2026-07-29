package com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.jwt;

import com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.user.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String HEADER = "Authorization";
    private static final String BEARER = "Bearer ";
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String Header = request.getHeader(HEADER);
        if(Header==null ||
            !Header.startsWith(BEARER)
        ){
            filterChain.doFilter(request,response);
            return;
        }
        System.out.println("Header = [" + Header + "]");
        String token = Header.substring(7).trim();
        System.out.println("Token = [" + token + "]");
        String username = jwtService.extractUsername(token);
        System.out.println("Username = " + username);
        if(username!=null &&
            SecurityContextHolder.getContext()
                    .getAuthentication()==null){
            UserDetails user =
                    userDetailsService
                            .loadUserByUsername(username);
            if(jwtService.isValidToken(token,user)){
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                user.getAuthorities());
                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);

    }

}
