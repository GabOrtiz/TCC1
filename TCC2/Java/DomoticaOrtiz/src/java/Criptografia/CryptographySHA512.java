/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabrielof
 */


package Criptografia;

import java.security.NoSuchAlgorithmException;
import Criptografia.Cryptography;
import Criptografia.CryptographyGeneric;
 
public class CryptographySHA512 extends CryptographyGeneric implements Cryptography {
 
  public String encrypt(String value) throws NoSuchAlgorithmException {
    return encryptByAlgorithm("SHA-512", value);
  }
 }
