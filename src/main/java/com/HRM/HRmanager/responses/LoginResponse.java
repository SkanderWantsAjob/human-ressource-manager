package com.HRM.HRmanager.responses;

public class LoginResponse {
    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token  = token;
    
    }

    public long getExpiresIn() {
        return expiresIn; 
    }

    public void setExpiresIn(long e){
        this.expiresIn= e;
    }

 // Getters and setters...
}
