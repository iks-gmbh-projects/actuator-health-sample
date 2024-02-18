package com.iksgmbh.actuator.health.sample.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    private final static String systemUser = System.getProperty("user.name");  // Windows: System.getenv("USERNAME"); Linux: System.getenv("USER"); OS/400: System.getenv("LOGNAME");

    /**
     * Returns current Spring security authentication.
     *
     * @return Authentication principal
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * Get current authenticated user name.
     * If user is not authenticated, then current system user running this application is returned.
     *
     * @return Current user name
     */
    public String getCurrentUserName() {
        Authentication authentication = getAuthentication();
        if (authentication != null &&
            authentication.isAuthenticated()) {
            return authentication.getName();
        } else {
            return systemUser;
        }
    }
}
