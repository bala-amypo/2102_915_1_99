// package com.example.demo.config;

// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import java.util.Collection;
// import java.util.stream.Collectors;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {
    
//     private final UserRepository userRepository;
    
//     public CustomUserDetailsService(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }
    
//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         // In your case, username is the email
//         User user = userRepository.findByEmail(username)
//             .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        
//         Collection<GrantedAuthority> authorities = user.getRoles().stream()
//             .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
//             .collect(Collectors.toList());
        
//         return new org.springframework.security.core.userdetails.User(
//             user.getEmail(),
//             user.getPassword(),
//             authorities
//         );
//     }
// }