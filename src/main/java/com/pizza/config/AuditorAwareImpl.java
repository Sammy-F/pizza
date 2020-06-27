// package com.pizza.config;

// import java.util.Optional;

// import org.springframework.data.domain.AuditorAware;

// /**
//  * From: https://github.com/njnareshjoshi/articles/blob/master/spring-data-jpa-auditing/src/main/java/org/programming/mitra/AuditorAwareImpl.java
//  */

// class AuditorAwareImpl implements AuditorAware<String> {

//     @Override
//     public Optional<String> getCurrentAuditor() {
//         return Optional.of("Sammy");
//         // Can use Spring Security to return currently logged in user
//         // return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
//     }
// }