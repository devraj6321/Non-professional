package bouncyCastle.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;

@Service
public class KeyManager {
    private static final KeyManager keyManagerInstance = new KeyManager();
    public static HashMap<String, Key> keyList ;
    @Autowired
    private BouncyCastleKeyProvider keyProvider;

    private KeyManager(){
        keyList = new LinkedHashMap<>();
    }

    private KeyManager getInstance(){
       return keyManagerInstance;
    }

    public String createKey(String keyName){
        Key key = keyProvider.getKey();
        keyList.put(keyName,key);
        return key.toString();
    }
    public Key getKey(String keyName){
        for(Map.Entry<String, Key> keySet : keyList.entrySet()){
            if(keySet.getKey().equalsIgnoreCase(keyName)){
                //can add a validation check before returning or validation can be done at the receiving end
                return keySet.getValue();
            }
        }
        throw new RuntimeException("Key not found");
    }

    public List<ResponseEntity<Key>> showAllKeys(){
        System.out.println("Fetching Keys");
        List<ResponseEntity<Key>> listOfKeys = new ArrayList<>();
        for(String key:keyList.keySet()){
            listOfKeys.add(new ResponseEntity<>(keyList.get(key),HttpStatus.OK));
        }
        return listOfKeys;
    }

}
