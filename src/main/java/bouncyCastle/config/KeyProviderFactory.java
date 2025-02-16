package bouncyCastle.config;


public class KeyProviderFactory {
    private static BouncyCastleKeyProvider keyProvider;
    private KeyProviderFactory(){};

    public static BouncyCastleKeyProvider getInstance(){
        if(keyProvider==null){
            keyProvider = new BouncyCastleKeyProvider();
        }
        return keyProvider;
    }

}
