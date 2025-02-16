package bouncyCastle.controller;

import bouncyCastle.config.BouncyCastleKeyProvider;
import bouncyCastle.config.KeyManager;
import bouncyCastle.service.CryptographyApi;
import bouncyCastle.service.CryptographyService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.security.Key;
import java.util.List;

@RestController
@RequestMapping("/KeyProvider")
public class Controller {

    @Autowired
    private KeyManager keyManager;
    @Autowired
    private CryptographyApi cryptographyApi;

    @GetMapping("/saveKey")
    public ResponseEntity<String> saveKey(@RequestParam String keyName) {

        keyManager.createKey(keyName);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/createKey")
    public String createKey(@RequestParam String keyName) throws InterruptedException {
        String key = keyManager.createKey(keyName);
        Thread.sleep(1000);
        return key;
    }
    @GetMapping("/loadKey")
    public ResponseEntity<String> loadKey(@RequestParam String keyName) {
        String key = null;
        if (keyName != null) {
            try {
                key = keyManager.getKey(keyName).toString();
            } catch (Exception e) {
                System.out.println("Key not found" + e.getMessage());
            }
            return new ResponseEntity<>(key, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Enter key name", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/encrypt")
    public String encrypt(@RequestParam String plainText, @RequestParam String keyName){
        Key key = keyManager.getKey(keyName);
        cryptographyApi.encrypt(key,plainText);
        return cryptographyApi.encrypt(key, plainText);
    }

    @GetMapping("/decrypt")
    public String decrypt(String encryptedText, String keyName){
        Key key = keyManager.getKey(keyName);
    return cryptographyApi.decrypt(key, encryptedText);
    }

    @GetMapping("/showAllKeys")
    public List<ResponseEntity<Key>> showAllKeys(){
        return keyManager.showAllKeys();
    }
}
