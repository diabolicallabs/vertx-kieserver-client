package com.diabolicallabs.kie.server;

import java.util.Base64;

public class Credential {

  public static String encode(String username, String password) {

    String credential = username + ":" + password;
    return new String(Base64.getEncoder().encode(credential.getBytes()));
  }
}
