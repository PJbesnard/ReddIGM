package fr.uge.jee.web.service.reddIGM.filters;

import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.services.UserService;
import fr.uge.jee.web.service.reddIGM.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
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
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // Extraction du token depuis le header
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            try {
                jwt = authorizationHeader.substring(7);
                username = jwtUtil.extractUserName(jwt);
            } catch (Exception e) {
                System.err.println("Authentication error : " + e.getMessage());
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }

        }
        // Si pas déjà authentifié
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            User user = this.userService.loadUserByUsername(username);

            // vérification du token
            if (jwtUtil.validateToken(jwt, user)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                // Ajout de la connexion au SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        // Continue the chain
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
