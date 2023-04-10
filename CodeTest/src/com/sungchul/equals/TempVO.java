package com.sungchul.equals;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class TempVO {

    @JsonProperty("password")
    public String password;
    @JsonProperty("email")
    public String email;
    @JsonProperty("username")
    public String username;
}
