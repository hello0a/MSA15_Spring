package com.aloha.security.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Users {
  private Long no;
  @Builder.Default
  private String id = UUID.randomUUID().toString();
  private String username;
  private String password;
  private String name;
  private String email;
  private Date createdAt;
  private Date updatedAt;
  private int enabled;
  
  // userauth.auth : role_user -> gen~~~ ....
  private List<UserAuth> authList;

  public Users() {
    this.id = UUID.randomUUID().toString();
  }
}
