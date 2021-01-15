package com.tpspringcloud.securityservice.utilities;

public class JWTUtil {
    public static final String SECRET = "myHMACPrivateKey";
    public static final String AUTH_HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";
    public static final long EXPIRE_ACCESS_TOKEN = 2*60*1000;
    public static final long EXPIRE_REFRESH_TOKEN = 10*24*3600*1000;
}
