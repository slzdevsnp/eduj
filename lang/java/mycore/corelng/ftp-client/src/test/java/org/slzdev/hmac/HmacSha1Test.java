package org.slzdev.hmac;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
public class HmacSha1Test {

 public static class HmacSha1Signature{
     private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

     private static String toHexString(byte[] bytes) {
         Formatter formatter = new Formatter();

         for (byte b : bytes) {
             formatter.format("%02x", b);
         }

         return formatter.toString();
     }

     public static String calculateRFC2104HMAC(String data, String key)
             throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
     {
         SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
         Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
         mac.init(signingKey);
         return toHexString(mac.doFinal(data.getBytes()));
     }
 }

}
