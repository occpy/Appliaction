package com.example.demo12;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.SM2;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo12ApplicationTests {

    @Test
    void contextLoads() {
        String content = "admin";
//        final SM2 sm2 = SmUtil.sm2();
//        String sign = sm2.signHex(HexUtil.encodeHexStr(content));
//        String sign = "304402205440b7cab449537d82f355b66d3121b2a4e89f647c34a42b5d2d0d2d62f4d70202205a9942de90be476269b963e86a5f8a40921055b436170aedea6009aebca674b5";
//        System.out.println(sign);
// true
//        boolean verify = sm2.verifyHex(HexUtil.encodeHexStr(content), sign);
        Digester digester = DigestUtil.digester("sm3");
        String digestHex = digester.digestHex(content);
        System.out.println(digestHex);
    }

}
