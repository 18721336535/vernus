package code.commons;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class CommonTest {
    public static void main(String []args){
        try {
            String data = "hi,I am fine";
            Cipher cipher = Cipher.getInstance("RSA");
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            Base64.Decoder dc= Base64.getDecoder();
            String pkey = "AED12345BC...";
            byte[] keybyte = dc.decode(pkey);
            PKCS8EncodedKeySpec keyspec = new PKCS8EncodedKeySpec(keybyte);

            PrivateKey privatekey = keyFactory.generatePrivate(keyspec);

            Signature sgn = Signature.getInstance("MD5withRSA");
            sgn.initSign(privatekey);
            sgn.update(data.getBytes());
            String decodesign = new String(dc.decode(sgn.sign()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        }

}
