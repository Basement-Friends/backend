package basement.friends.backend.security;

import basement.friends.backend.api.handlers.DTO.ErrorResponse;
import basement.friends.backend.model.User;
import basement.friends.backend.service.definition.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
//            addErrorMessageToResponse(response, HttpStatus.NOT_ACCEPTABLE.value(), "Authorization Header not provided!");
            return;
        }
        try {
            final String jwt = authHeader.substring(7);       //to exclude the "Bearer " keyword
            try {
                final String username = jwtService.extractUsername(jwt);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    User userDetails = userDetailsService.getByUsername(username);
                    if (jwtService.isTokenValid(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
                filterChain.doFilter(request, response);
            } catch (Exception exception) {
                addErrorMessageToResponse(response, HttpStatus.FORBIDDEN.value(), exception.getMessage());
            }
        } catch (Exception exception) {
            addErrorMessageToResponse(response, HttpStatus.NOT_ACCEPTABLE.value(), "Authorization Header not provided!");
        }
    }

    private void addErrorMessageToResponse(HttpServletResponse response, int errorCode, String errorMessage) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), new ErrorResponse(errorCode, errorMessage));
    }
}
