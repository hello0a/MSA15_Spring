package com.aloha.security.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserAuth {
  private Long no;
  @Builder.Default
  private String id = UUID.randomUUID().toString();
  private String username;
  private String auth;

  public UserAuth() {
    this.id = UUID.randomUUID().toString();
  }
}

