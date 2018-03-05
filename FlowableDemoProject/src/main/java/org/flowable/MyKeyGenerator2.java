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
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class MyKeyGenerator2 {
	
	public static void main(String[] args) {
		try {
			String word = "要加密的";
			String ALGORITHM="AES";
			System.out.println(HexBin.encode(word.getBytes()));
			KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
			keyGenerator.init(128,new SecureRandom());  //默认是128  AES要求密钥长度为128,192,256位   
			SecretKey secretKey = keyGenerator.generateKey(); //生成密钥
			byte[] bytes = secretKey.getEncoded();

			String key = HexBin.encode(bytes);
			System.out.println("16进制的密钥："+key);
			//String key2 = toHexString(bytes);
			//System.out.println(key2);
			//AES加密
			SecretKey secretKey2 = new SecretKeySpec(HexBin.decode(key), ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey2);
			byte[] cipherByte = cipher.doFinal(word.getBytes()); //加密
			
			String result = HexBin.encode(cipherByte);
			System.out.println("AES加密结果："+result);
			
			//AES解密
			SecretKey secretKey3 = new SecretKeySpec(HexBin.decode(key), ALGORITHM);//恢复密钥
			Cipher cipher2 = Cipher.getInstance(ALGORITHM);//Cipher完成加密或解密工作类
			cipher2.init(Cipher.DECRYPT_MODE, secretKey3);//对Cipher初始化，解密模式
			byte[] cipherByte2 = cipher2.doFinal(HexBin.decode(result));//解密data
			
			System.out.println("AES解密结果："+HexBin.encode(cipherByte2));
			
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
}