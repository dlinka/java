package com.cr.crypto;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.cr.common.Facility;

import java.security.KeyPair;

import static com.cr.common.Facility.print;


public class HutoolRSA {

    public static void main(String[] args) {
        RSA rsa = new RSA();
        print(rsa.getPrivateKey());
        print(rsa.getPublicKey());
        print(rsa.getPrivateKeyBase64());
        print(rsa.getPublicKeyBase64());

        //公钥加密，私钥解密
        byte[] encrypt1 = rsa.encrypt(StrUtil.bytes("公钥加密，私钥解密"), KeyType.PublicKey);
        byte[] decrypt1 = rsa.decrypt(encrypt1, KeyType.PrivateKey);
        print(new String(decrypt1));

        //私钥加密，公钥解密
        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("私钥加密，公钥解密"), KeyType.PrivateKey);
        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);
        print(new String(decrypt2));
    }
}
