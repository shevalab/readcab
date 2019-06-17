package com.example.readcab;

public class PayloadFile {
    private String digest;
    private String location;

    public String getDigest() {
        return digest;
    }

    public PayloadFile setDigest(String digest) {
        this.digest = digest;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public PayloadFile setLocation(String location) {
        this.location = location;
        return this;
    }
}
