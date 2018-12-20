/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.Key;
import javax.crypto.BadPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author unkno
 */
public class user {

    private String username = constants.username;
    private String password = constants.password;
    private String email = constants.email;
    private String displayName = constants.displayName;
    private int userID = constants.userID;
    private boolean banned = constants.banned;
    private int active = constants.status;
    private int walletSize = 0;
    
    private static final String Skey = "pAdzWhcRuaKuzMc0";    //16 characters
    private static final String initVector = "pVNLoLIjaCANmZ5Z";    //16 characters
    
    
    public user(String username, String password, String email, String displayName, int userID, boolean banned, int active, int walletSize) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.displayName = displayName;
        this.userID = userID;
        this.banned = banned;
        this.active = active;
        
        if (walletSize < 0) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, "Wallet mis-match, balance below 0.");
        } else {
            this.walletSize = walletSize;
        }
    }

    /**
     * 
     * @return used for returning wallet size 
     */
    public int getWalletSize() {
        return walletSize;
    }
    
    
    /**
     * 
     * @param walletSize
     * @description used for setting wallet size
     */
    public void setWalletSize(int walletSize) {
        this.walletSize = walletSize;
    }
    
    /**
     * @return user active status
     */
    public int getActive() {
        return active;
    }

    /**
     * @param active 
     * @description used for setting activity status (disabled, enabled, etc)
     */
    public void setActive(int active) {
        this.active = active;
    }
    
    

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getUserID() {
        return userID;
    }

    /**
     * 
     * @param banned
     * @description used for setting user ability to interact with canvas
     */
    public void setBanned(boolean banned) {
        this.banned = banned;
    }
    
    /**
     * @description returns true if user is banned
     * @return boolean
     */
    public boolean isBanned() {
        return banned;
    }
    
    /**
     * @Author: Chris
     * @Date: Dec, 20, 2018
     * @Desc: Used for encrypting strings using standard key AES encryption
     * @param input
    */
    private static String encryptPword(String input) {
        try {            
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKey key = new SecretKeySpec(Skey.getBytes("UTF-8"), "AES");
            c.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] x = c.doFinal(input.getBytes());
            System.out.println(DatatypeConverter.printBase64Binary(x)); //used in dev
            return DatatypeConverter.printBase64Binary(x);
            
        } catch (NoSuchAlgorithmException | 
                NoSuchPaddingException | 
                InvalidKeyException | 
                UnsupportedEncodingException | 
                IllegalBlockSizeException | 
                BadPaddingException | 
                InvalidAlgorithmParameterException ex) { 
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return null;
    }
    
    /**
     * NOTE: WIP
     * @Author: Chris
     * @Date: Dec, 20, 2018
     * @Desc: Used for decrypting strings using standard key AES encryption
     * @param input
    */
    private String decryptPword(String input) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKey key = new SecretKeySpec(Skey.getBytes("UTF-8"), "AES");
            c.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] org = c.doFinal(DatatypeConverter.parseBase64Binary(input));
            System.out.println("Original: " + new String(org)); //for dev only
            return new String(org);

        } catch (NoSuchAlgorithmException | 
                NoSuchPaddingException | 
                InvalidKeyException | 
                UnsupportedEncodingException | 
                IllegalBlockSizeException | 
                BadPaddingException | 
                InvalidAlgorithmParameterException ex) { 
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
}
