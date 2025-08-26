import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class EncTest {

    public static void main(String[] args) throws Exception {
        test1();
    }

    public static void test1() throws Exception {
        String plainText = "Hello, MadPlay!";

        PBEKeySpec keySpec = new PBEKeySpec("Selani".toCharArray());
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey secretKey = new SecretKeySpec(factory.generateSecret(keySpec).getEncoded(), "AES");

        SecretKey key1 = getKey();
        SecretKey key2 = getKey();
        IvParameterSpec ivParameterSpec = getIv();
        String specName = "AES/CBC/PKCS5Padding";

        String encryptedText = encrypt(specName, secretKey, ivParameterSpec, plainText);
        String decryptedText = decrypt(specName, secretKey, ivParameterSpec, encryptedText);

        System.out.println("cipherText: " + encryptedText);
        System.out.println("plainText: " + decryptedText);
    }

    public static SecretKey getKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

    public static IvParameterSpec getIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static String encrypt(String algo, SecretKey key, IvParameterSpec iv, String text) throws Exception {
        Cipher cipher = Cipher.getInstance(algo);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.getEncoder().encode(encrypted));
    }

    public static String decrypt(String algo, SecretKey key, IvParameterSpec iv, String text) throws Exception {
        Cipher cipher = Cipher.getInstance(algo);
        cipher.init(Cipher.DECRYPT_MODE, key, iv); // 모드가 다르다.
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(text));
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    public static String encrypt(String txt, String sk, String iv) throws Exception {
        return crypt(txt, sk, iv, Cipher.ENCRYPT_MODE);
    }

    public static String decrypt(String txt, String sk, String iv) throws Exception {
        return crypt(txt, sk, iv, Cipher.DECRYPT_MODE);
    }

    public static String crypt(String txt, String sk, String iv, int mode) throws Exception {
        byte[] bt = txt.getBytes();
        byte[] bk = ByteBuffer.allocate(32).put(sk.getBytes()).array();
        byte[] bi = ByteBuffer.allocate(16).put(iv.getBytes()).array();
        SecretKeySpec secretKeySpec = new SecretKeySpec(bk, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bi);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(mode, secretKeySpec, ivParameterSpec);
        if (mode == Cipher.ENCRYPT_MODE) {
            byte[] br = cipher.doFinal(bt);
            return Base64.getEncoder().encodeToString(br);
        } else {
            byte[] bx = Base64.getDecoder().decode(bt);
            byte[] br = cipher.doFinal(bx);
            return new String(br);
        }
    }
}
