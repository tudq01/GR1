package com.gr1.spring.security.service;

import com.gr1.spring.dto.UserDTO;
import com.gr1.spring.entity.RefreshToken;
import com.gr1.spring.exception.CustomValidationException;
import com.gr1.spring.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

@Service
public class UserService {
   @Autowired
   private AuthenticationManager authenticationManager;
   @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    RefreshTokenService refreshTokenService;

    public UserDTO login(String username, String password){
       Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(username, password));
       SecurityContextHolder.getContext().setAuthentication(authentication);
       String jwt = jwtUtils.generateJwtToken(authentication);

       UserDetailsImpl userDetails  = (UserDetailsImpl) authentication.getPrincipal();
       RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
       return new UserDTO(jwt,refreshToken.getToken(),userDetails.getId(),userDetails.getUsername());
   }
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public UserDTO getUserDetail() {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetails;

                // convert userDetails to userDto
                return new UserDTO(jwt,
                        userDetailsImpl.getId(),
                        userDetailsImpl.getUsername()
                       );
            }
            throw new CustomValidationException("No token provided");
        } catch (Exception e) {
            throw e;
        }
    }
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }

}
