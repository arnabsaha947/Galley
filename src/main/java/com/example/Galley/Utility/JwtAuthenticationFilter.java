package com.example.Galley.Utility;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    //Everytime an endpoint is hit, each of those requested hits pass through series of steps.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = null;
        String usernameFromToken = null;

        //STEP 1 : To Authenticate the user we get the Authorization value from request header
        String requestHeader = request.getHeader("Authorization");

        if(requestHeader!=null && requestHeader.startsWith("Bearer")){

            // STEP 2 : We get the token value which is preceeded by "Bearer" in request header.
            token = requestHeader.substring(7);
            try {
                /*
                STEP 3 : We get the username from the token value as, if the token value is tampered then we receive
                        incorrect username from it or an exception
                        OR-ELSE we receive correct username
                 */
                usernameFromToken = this.jwtHelper.getUsernameFromToken(token);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }

        }
        else {
            System.out.println("Invalid Authorization-Header value !");
        }
        /*
         STEP 4 : For every request hits we check if the user is already authenticated or not.
                  If user is not authenticated then it enters into if block
         */
        if(usernameFromToken!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(usernameFromToken);
            Boolean validateToken = this.jwtHelper.validateToken(token,userDetails);
            if(validateToken){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                /*
                 In order to authenticate the current user we need an object of UsernamePasswordAuthenticationToken
                 It is done by setting the request details of the current user in UsernamePasswordAuthenticationToken
                 */
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else {
                System.out.println("Token Validation Failed !");
            }
        }
       filterChain.doFilter(request,response);

    }
}
