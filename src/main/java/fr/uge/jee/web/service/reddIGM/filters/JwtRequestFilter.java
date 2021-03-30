package fr.uge.jee.web.service.reddIGM.filters;

import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.services.UserService;
import fr.uge.jee.web.service.reddIGM.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Pour la connexion une fois qu'un token est fixé
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest httpServletRequest, @NonNull HttpServletResponse httpServletResponse, @NonNull FilterChain filterChain) throws ServletException, IOException {
        System.out.println("JWT REQUEST ACtiVATED");
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String username = null;
        String jwt;

        // Extraction du token depuis le header
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUserName(jwt);
            if (StringUtils.hasText(jwt) && this.jwtUtil.validateToken(jwt)) {
                System.out.println("okkkkkkk validééééééé");
                Authentication authentication = this.jwtUtil.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        // Si pas déjà authentifié
        System.out.println("authentication : " + SecurityContextHolder.getContext().getAuthentication());
        /*if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            //User user = this.userService.loadUserByUsername(username);
            User user = new User(username, )
            System.out.println("PAS DEJA AUTHENTIFIAK");

            // vérification du token
            if (jwtUtil.validateToken(jwt, user)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                // Ajout de la connexion au SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }*/

        // Continue the chain
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
