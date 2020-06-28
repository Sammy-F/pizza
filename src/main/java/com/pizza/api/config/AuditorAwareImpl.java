package com.pizza.api.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

/**
 * From: https://github.com/njnareshjoshi/articles/blob/master/spring-data-jpa-auditing/src/main/java/org/programming/mitra/AuditorAwareImpl.java
 */

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Sammy");
        // Can use Spring Security to return currently logged in user
        // return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
    }
}

// ------------------ Use below code for spring security --------------------------
// from https://www.javaguides.net/2018/09/spring-data-jpa-auditing-with-spring-boot2-and-mysql-example.html

/*class SpringSecurityAuditorAware implements AuditorAware<User> {

 public User getCurrentAuditor() {

  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

  if (authentication == null || !authentication.isAuthenticated()) {
   return null;
  }

  return ((MyUserDetails) authentication.getPrincipal()).getUser();
 }
}*/