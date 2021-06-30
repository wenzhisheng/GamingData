package com.quanmin.djdata.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

/**
 * @Author: ate
 * @Description: TODO
 * @CreateDate: 2019-11-21 12:10
 * @ClassName: com.quanmin.djdata.util.RSAEncrypt
 */
public class RSAEncrypt {

    private static final Logger logger = LoggerFactory.getLogger(RSAEncrypt.class);

    /** 编码 */
    public static final String CHARSET = "UTF-8";
    // 算法：MD5withRSA  SHA1WithRSA SHA256WithRSA
    public static final String RSA_ALGORITHM = "SHA256WithRSA";

    /** 浮东请求私钥 */
    public static final String privateKey ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDop2sdYJQ4yCEgQu5Lp32Ou82zBTs6ty62qgMy4oF21hyZQq7iKmuZZY4gBsz5GShgncTqpLD0TxiZqOYpARyLGibsQPZ3wIJnPPlbtCuFEDZdEhfLo08aGv01TgnhIcoE7OwxR3dV9jeJN77qw8ZJzSR2UlAZ0y3QvNxPWqJBoWap5d8UAU2jQrx2d+uj7EVUTVtRsCE4QPeUz385e0c1VOu8qDYz/j/rDrS2BlsmeDXq9ZFobmvoO+pZ2lHjFTRCeaLCVKnpX8d4+mZK3bTpXm9Fdu8Nx9jZrC3vykirqUPnnZ7TB+pBvRt3Vz9SEpk1yXD/8lNyBN624IJLSXdDAgMBAAECggEAfJJgLUuwMbMe4ZpU49dbyFhQrMFpVGgPMClaKx3S+mFs0Lc+0sSp9mnFLurVR6+rygfQD199jGLppiUkj+ITeXvYSXoDPl2qtUKVtf+DqezjXvQ4H4Zi7XR0Dd2qNoyUEg0V7tD4WePLGsLpi+SlwJCCLISodRt5FaJ6SFccOA0BZKz9JX4hlEOVkjKgFIoovM9G9F+rvGheoDdEpyRRXBVI9G5HhRW5cqB0sNK6A9gXi/Uv3jeno4W4QO5FboR+S0snyoPOMmQwkhYpJAWblIldEUfmuagnmp0a6sXQd2t5Wfr7A4P8JtS+u507r6Biquikob+XJApidIPKniUiKQKBgQD7xc05fWcbXv5z77aM4Te+ZOviQhNGuwz0ngArSBcOVUiA4CgfBQrle6KnZyomUmhGAq6TPjU2iQTtDlZxUY8dUvZ2ENpuRhNmS8i7Rh+hSrNUVCSK1v1n4+asiSfYYyJws6bCgw7pa0YcszWuGNvef1gctchLxDCs+rHne5stdwKBgQDsj3BJam1Z/J5D1G1a8aQ6MiPdm4wD3DlSg+jN2hzCyG+nxaKXKZnx/FE3rV4z62nt6HYuItCd1u+GsLRlTuzlhqVuyo/MzlSjX0r9Cdf/81h1oEVmAS2puCSNY1TqtE7g/r/0wWDegAI+fPVPyvWJRwa9n7q2KBKcAbge2YRHlQKBgQD1DLnJqdewGU5aM0efaRmjc4DvMFaosihS8nHBrqHaLoGqBgKm5naLk0Fl5BBvSif5dGTMJXEPil9EB391Peeop/YARjkDuarqFvrh48enahiPDHKgu83az0PWTIx+nUaJISI/EeZypBmSl464y7M8pP9yui+gJu0lf7+mSXVo0wKBgF+kitCUAAxO76oa+++2HSEOXqPdnNl+s4piHMEFu3UhVsttQ5R8VGqbCjdJl/nD53sx7n4uw0vdt9AsJ3OCWpNeQgquST+T+HJpN8dgsH0iZRSBrS1VsqGY+uZTT+To669aMEAD42dyN/YNzZzqQSW0mswWBYZaY1PB+jA2352VAoGARurIZOma2sXn1bJHmELHEbTUJFDPpcR+ZHPwgi+dfzJrGssLSN9fJP3h1AZcKtSS6R9NqkMzYSZBRpnoaJHM1Sl++/mYa+w+4PsULz7c5RRb4KF3bqouwRAlkcnuAwyt4MaFI+SAZNH8rF2EFxtoZxKO0EJ8PhrqcHHYjUgHaqw=";

    /** 浮东请求公钥 */
    public static final String publicKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6KdrHWCUOMghIELuS6d9jrvNswU7OrcutqoDMuKBdtYcmUKu4iprmWWOIAbM+RkoYJ3E6qSw9E8YmajmKQEcixom7ED2d8CCZzz5W7QrhRA2XRIXy6NPGhr9NU4J4SHKBOzsMUd3VfY3iTe+6sPGSc0kdlJQGdMt0LzcT1qiQaFmqeXfFAFNo0K8dnfro+xFVE1bUbAhOED3lM9/OXtHNVTrvKg2M/4/6w60tgZbJng16vWRaG5r6DvqWdpR4xU0QnmiwlSp6V/HePpmSt206V5vRXbvDcfY2awt78pIq6lD552e0wfqQb0bd1c/UhKZNclw//JTcgTetuCCS0l3QwIDAQAB";

    public static String rsaSign(String content, String privateKey, String charset, String algorithm) throws SignatureException {
        try {
            //PrivateKey priKey = getPrivateKeyFromPKCS8("RSA", new ByteArrayInputStream(privateKey.getBytes()));
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            //MD5withRSA  SHA1WithRSA  SHA256WithRSA
            Signature signature = Signature.getInstance(algorithm);
            signature.initSign(priKey);
            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }
            byte[] signed = signature.sign();
            return new String(Base64.encodeBase64(signed));
        } catch (Exception e) {
            throw new SignatureException("RSAcontent = " + content + "; charset = " + charset, e);
        }
    }

    public static String signParam(TreeMap<String, String> paramsMap) {
        // 请求参数封装到Treemap
//        paramsMap.put("tenant_id","2");
//        paramsMap.put("game_id","1");
//        paramsMap.put("series_id","74995654371587840");
//        paramsMap.put("request_time","1568955917");
        paramsMap.put("tenant_id", "2");
        Set<Map.Entry<String, String>> entrySet = paramsMap.entrySet();
        //logger.info("sign list entrySet={}", entrySet.toString());
        // 把参数排序后装进linkedlist
        List<String> list = new LinkedList<>();
        for (Map.Entry<String, String> entry: entrySet){
            list.add(org.apache.commons.lang.StringUtils.join(Arrays.asList("&", entry.getKey(),"=",entry.getValue()),""));
        }
        // 拼接list
        String params = org.apache.commons.lang.StringUtils.join(list,"");
        // 去除最前面符号
        params = params.substring(1);
        //logger.info("sign params={}", params);
        String sign = DigestUtils.md5Hex(params);
        //logger.info("sign={}", sign);
        String decrypt = "";
        try {
            decrypt = RSAEncrypt.rsaSign(sign, RSAEncrypt.privateKey, RSAEncrypt.CHARSET, RSAEncrypt.RSA_ALGORITHM);
            logger.info("sign rsa={}", decrypt);
        } catch (SignatureException ex) {
            logger.error("sign rsa error={}", ex);
        }
        return decrypt;
    }

}
