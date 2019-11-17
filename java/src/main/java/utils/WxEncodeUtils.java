package main.java.utils;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.Security;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class WxEncodeUtils {
	/*
	 * java 1.8+
	 */
	public static String encode1_8(String originalDataStr , String ivStr , String sessionKeyStr) {
		byte[] ivs = Base64.getDecoder().decode(ivStr);
        byte[] sks = Base64.getDecoder().decode(sessionKeyStr);
		
        //否则：java.security.NoSuchProviderException: No such provider: BC
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(sks , "AES") , new IvParameterSpec(ivs));
			byte [] encryptedStr = cipher.doFinal(originalDataStr.getBytes()) ;
//			return new String(cipher.doFinal(originalDataStr.getBytes()) , "UTF-8") ;
			//此处模拟小程序必须加一层Base64加密，否则无法还原。
			return Base64.getEncoder().encodeToString(encryptedStr) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "" ;
	}
	/**
     * 小程序AES-128-CBC 7位填充解密算法
     * java 1.8+
     * @param encryptedDataStr 已加密数据，内含群ID：openGId
     * @param ivStr 参数iv的加密字符串
     * @param sessionKeyStr 通过code获取的session_key的字符串
     * @return 解密信息
     */
    public static String decode1_8(String encryptedDataStr, String ivStr, String sessionKeyStr) {
		
        byte[] ivs = Base64.getDecoder().decode(ivStr);
        byte[] eds = Base64.getDecoder().decode(encryptedDataStr);
        byte[] sks = Base64.getDecoder().decode(sessionKeyStr);
        
        Security.addProvider(new BouncyCastleProvider());
        Key aeskey = new SecretKeySpec(sks, "AES");
        byte[] decodedDataStr = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, aeskey, new IvParameterSpec(ivs));
            decodedDataStr = cipher.doFinal(eds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(decodedDataStr != null){
            try {
                return new String(decodedDataStr , "UTF-8") ;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
    /*
     * java 1.7
     */
    public static String encode1_7(String originalDataStr , String ivStr , String sessionKeyStr) {
    	byte[] ivs = org.apache.commons.codec.binary.Base64.decodeBase64(ivStr);
    	byte[] sks = org.apache.commons.codec.binary.Base64.decodeBase64(sessionKeyStr);
    	
    	//否则：java.security.NoSuchProviderException: No such provider: BC
    	Security.addProvider(new BouncyCastleProvider());
    	Cipher cipher = null;
    	try {
    		cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
    		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(sks , "AES") , new IvParameterSpec(ivs));
    		byte [] encryptedStr = cipher.doFinal(originalDataStr.getBytes()) ;
    		return org.apache.commons.codec.binary.Base64.encodeBase64String(encryptedStr) ;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return "" ;
    }
    
    /**
     * java 1.7
     */
    public static String decode1_7(String encryptedDataStr, String ivStr, String sessionKeyStr) {
    	
    	byte[] ivs = org.apache.commons.codec.binary.Base64.decodeBase64(ivStr);
    	byte[] eds = org.apache.commons.codec.binary.Base64.decodeBase64(encryptedDataStr);
    	byte[] sks = org.apache.commons.codec.binary.Base64.decodeBase64(sessionKeyStr);
    	
    	Security.addProvider(new BouncyCastleProvider());
    	Key aeskey = new SecretKeySpec(sks, "AES");
    	byte[] decodedDataStr = null;
    	try {
    		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
    		cipher.init(Cipher.DECRYPT_MODE, aeskey, new IvParameterSpec(ivs));
    		decodedDataStr = cipher.doFinal(eds);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	if(decodedDataStr != null){
    		try {
    			return new String(decodedDataStr , "UTF-8") ;
    		} catch (UnsupportedEncodingException e) {
    			e.printStackTrace();
    		}
    	}
    	return "";
    }
    
    
    
	public static void main(String[] args) {
		//原始数据
		String originStr = "{\"openGId\":\"tGVI4n48N9tYF_ocDm3VqAgc7eqCE\",\"watermark\":{\"timestamp\":1522402070,\"appid\":\"wxbec3971d6f93b036\"}}";
		//iv加密串
		String iv = "Bm9LHp6MvvQ9oSpT2ehlmA==" ;
		//待解密数据
		String encryptedData = "E2/GNCaOFtwUavrrNq6nIXisknIfvlw63010u3D65NN6w65EyLQL+ti5V0bGQGj/rIDU6h5R3aRHnADOVpxSaArRsQ5ElNP0bDUT2hmAwUOw1KSJ9Xn48qThCRxEl0dhx4uEUsY9YCcR/6EKJxoKsA==" ;
		//session_key加密串
		String sessionKey = "+Ig9r8zD9UpudXQaJYzO5w==" ;
		/**************************java1.8 Test**********************************/
		//part 1 :
		//加密
		String encodeStr = encode1_8(originStr, iv, sessionKey) ;
		System.out.println(encodeStr);
//		//用加密结果进行解密测试
		String decodeTestStr = decode1_8(encodeStr , iv , sessionKey) ;
		System.out.println(decodeTestStr);
		// part 2 :
		//解密
		String decodeStr = decode1_8(encryptedData , iv , sessionKey) ;
		System.out.println(decodeStr);
		
		/**************************java1.7 Test**********************************/
		//part 1 :
		//加密
		String encodeStr1_7 = encode1_7(originStr, iv, sessionKey) ;
		System.out.println(encodeStr1_7);
//		//用加密结果进行解密测试
		String decodeTestStr1_7 = decode1_7(encodeStr , iv , sessionKey) ;
		System.out.println(decodeTestStr1_7);
		// part 2 :
		//解密
		String decodeStr1_7 = decode1_7(encryptedData , iv , sessionKey) ;
		System.out.println(decodeStr1_7);
	}

}
