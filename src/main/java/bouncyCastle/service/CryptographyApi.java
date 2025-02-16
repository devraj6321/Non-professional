package bouncyCastle.service;

import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class CryptographyApi {
    private final CryptographyService cryptographyService;
    public CryptographyApi(){
        cryptographyService = new CryptographyService();
    }
    public String encrypt(Key key, String plainText){
        return cryptographyService.encrypt(key, plainText);
    }
    public String decrypt(Key key, String encryptedText){
        return cryptographyService.decrypt(key, encryptedText);
    }
}
