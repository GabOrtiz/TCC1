/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Criptografia;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

/**
 *
 * @author gabrielof
 */

 
 public abstract class CryptographyGeneric{
 
   private MessageDigest messageDigest;
   private BASE64Encoder encoder;
 
   protected void useAlgorithm(String algorithm) throws NoSuchAlgorithmException {
     if (messageDigest == null || messageDigest.getAlgorithm() != algorithm) {
       messageDigest = MessageDigest.getInstance(algorithm);
     }
 
     if (encoder == null) {
       encoder = new BASE64Encoder();
     }
   }
 
   protected String encryptByAlgorithm(String algorithm, String value) throws NoSuchAlgorithmException {
     if (value == null) {
       throw new IllegalArgumentException("The value is null.");
     }
 
     useAlgorithm(algorithm);
     byte[] hash = messageDigest.digest(value.getBytes());
     return encoder.encode(hash);
   }
 }
