package dev.sheet_co.request_races.domain.user;

import lombok.Data;

import java.util.Set;

@Data
public class User {
  private Long id;
  private String name;
  private String email;
  private String password;
  private String passwordConfirmation;
  private Set<Role> roles;
}
