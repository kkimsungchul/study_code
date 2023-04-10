package com.securus;
import com.securus.Base64Encoder;
import com.securus.StringEncrypter;


public class RunTest {
    public static void main(String[]args) throws Exception {

//        String key = "afasdfasdfadf";
//        String iv = "vvvaaaaa";
//
//        // 인스턴스 만들기.
//        StringEncrypter encrypter = new StringEncrypter(key, iv);
//
//
//        // 문자열 암호화.
//        String encrypted = encrypter.encrypt("securusTestString");
//        System.out.println("encrypted : " + encrypted);
//        // 문자열 복호화.
//        String decrypted = encrypter.decrypt(encrypted);
//        System.out.println("decrypted : " + decrypted);

        System.out.println(AES.encrypt("1"));

        String str=null;
        if(str.equals("aa")){
            System.out.println(true);
        }else{
            System.out.println(false);
        }

    }
}
