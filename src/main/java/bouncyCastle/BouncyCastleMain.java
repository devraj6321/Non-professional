package bouncyCastle;

import bouncyCastle.service.CryptographyApi;
import bouncyCastle.service.CryptographyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BouncyCastleMain {
    public static void main(String[] args) {
        SpringApplication.run(BouncyCastleMain.class,args);
        /*CryptographyApi cryptographyApi = new CryptographyApi();
        String secret = cryptographyApi.encrypt("Secret Message");
        System.out.println("Encrypted Text: " + secret);
        System.out.println("Decrypted Text: " + cryptographyApi.decrypt(secret));*/
    }


}
