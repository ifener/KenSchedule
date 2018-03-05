package org.flowable;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyKeyGenerator {
	
	public static void main(String[] args) {
		try {
			String word = "要加密的";
			String ALGORITHM="AES";
			System.out.println(byteToHexString(word.getBytes()));
			KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
			keyGenerator.init(128,new SecureRandom());  //默认是128  AES要求密钥长度为128,192,256位   
			SecretKey secretKey = keyGenerator.generateKey(); //生成密钥
			byte[] bytes = secretKey.getEncoded();

			String key = byteToHexString(bytes);
			System.out.println("16进制的密钥："+key);
			//String key2 = toHexString(bytes);
			//System.out.println(key2);
			//AES加密
			SecretKey secretKey2 = new SecretKeySpec(hexStringToBytes(key), ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey2);
			byte[] cipherByte = cipher.doFinal(word.getBytes()); //加密
			
			String result = byteToHexString(cipherByte);
			System.out.println("AES加密结果："+result);
			
			//AES解密
			SecretKey secretKey3 = new SecretKeySpec(hexStringToBytes(key), ALGORITHM);//恢复密钥
			Cipher cipher2 = Cipher.getInstance(ALGORITHM);//Cipher完成加密或解密工作类
			cipher2.init(Cipher.DECRYPT_MODE, secretKey3);//对Cipher初始化，解密模式
			byte[] cipherByte2 = cipher2.doFinal(hexStringToBytes(result));//解密data
			
			System.out.println("AES解密结果："+byteToHexString(cipherByte2));
			
			System.out.println(new String(cipherByte2));
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

	}
	
	 /**
	  * byte数组转化为16进制字符串
	  * @param bytes
	  * @return
	  */
	  public static String byteToHexString(byte[] bytes) {
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < bytes.length; i++) {
	      String strHex=Integer.toHexString(bytes[i]);
	      if(strHex.length() > 3) {
	        sb.append(strHex.substring(6));
	      } else {
	        if(strHex.length() < 2) {
	          sb.append("0" + strHex);
	        } else {
	          sb.append(strHex);
	        }
	      }
	    }
	    return sb.toString();
	  }
	  
	  /**
	   * 16进制字符串转为byte数组
	   * @param hexString
	   * @return
	   */
	  public static byte[] hexStringToBytes(String hexString) {   
		    if (hexString == null || hexString.equals("")) {   
		        return null;   
		    }   
		    hexString = hexString.toUpperCase();   
		    int length = hexString.length() / 2;   
		    char[] hexChars = hexString.toCharArray();   
		    byte[] d = new byte[length];   
		    for (int i = 0; i < length; i++) {   
		        int pos = i * 2;   
		        d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));   
		    }   
		    return d;   
		}
	  
	  private static byte charToByte(char c) {   
		    return (byte) "0123456789ABCDEF".indexOf(c);   
	  } 

}