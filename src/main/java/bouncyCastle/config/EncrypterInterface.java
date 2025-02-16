package bouncyCastle.config;

import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public interface EncrypterInterface {
    String encrypt(Key key, String plainData) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException;
    String decrypt(Key key, String encryptedData);
}
