package bouncyCastle.config;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

@Service
public class BouncyCastleKeyProvider {
    private SecretKey secretKey;
    public BouncyCastleKeyProvider(){
        Security.addProvider(new BouncyCastleProvider());
        generateKey(); //Generate key method is envoked to get a key when provider is created.
    }
    private void generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            secretKey = keyGenerator.generateKey();
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Key Generation error: " + e.getMessage());
        }
    }
    public SecretKey getKey(){
        return secretKey;
    }

}
