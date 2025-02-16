package bouncyCastle.service;

import bouncyCastle.config.BouncyCastleKeyProvider;
import bouncyCastle.config.EncrypterInterface;
import bouncyCastle.config.KeyManager;
import bouncyCastle.config.KeyProviderFactory;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.security.Key;

@Component
public class CryptographyService implements EncrypterInterface {
    private final BouncyCastleKeyProvider keyProvider = KeyProviderFactory.getInstance();
    @Autowired
    private KeyManager keyManager;
    @Override
    public String encrypt(Key key, String plainData) {
        try {
           // SecretKey key = keyProvider.getKey();

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(plainData.getBytes());
            return new String(Base64.encode(encryptedBytes));
        }catch (Exception e){
            throw new RuntimeException("Error encrypting: "+ e.getMessage());
        }
    }

    @Override
    public String decrypt(Key key,String encryptedData) {
        try {
            //SecretKey key = keyProvider.getKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] decryptedBytes = cipher.doFinal(Base64.decode(encryptedData));
            return new String(decryptedBytes);
        }catch (Exception e){
            throw new RuntimeException("Error decrypting: "+e.getMessage());
        }
    }
}
