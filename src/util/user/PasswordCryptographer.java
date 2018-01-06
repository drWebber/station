package util.user;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordCryptographer {
    private String password;
    private String salt;
    private int length;

    public PasswordCryptographer(String salt, String password) {
        length = salt.length();
        this.salt = "weakSalt:)" + salt;
        this.password = password;
    }
    
    public String getCryptedPassword() throws NoSuchAlgorithmException {
        salt = salt.substring(salt.length() % 2, salt.length() - length % 2);
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(StandardCharsets.UTF_8.encode(password+salt));
        return String.format("%032x", new BigInteger(1, md5.digest()));
    }
}
