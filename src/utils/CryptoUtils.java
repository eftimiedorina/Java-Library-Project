package utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtils {

	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES";

	public static void encrypt(String key, File inputFile, File outputFile) throws CryptoException {
		doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
	}

	public static void decrypt(String key, File inputFile, File outputFile) throws CryptoException {
		doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
	}

	private static void doCrypto(int cipherMode, String key, File inputFile, File outputFile) throws CryptoException {
		try {

			Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(cipherMode, secretKey);

			// create stream
			FileInputStream inputStream = new FileInputStream(inputFile);

			byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);

			byte[] outputBytes = cipher.doFinal(inputBytes);

			FileOutputStream outputStream = new FileOutputStream(outputFile);
			outputStream.write(outputBytes);

			inputStream.close();
			outputStream.close();

		} catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException
				| IllegalBlockSizeException | IOException ex) {
			throw new CryptoException("Error encrypting/decrypting file", ex);
		}
	}

}
