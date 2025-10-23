package dev.ivoencarnacao.book_tracker.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.ivoencarnacao.book_tracker.repository.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public JpaUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;

  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .map(user -> new User(
            user.getUsername(),
            user.getPasswordHash(),
            Collections.emptyList()))
        .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
  }

}
