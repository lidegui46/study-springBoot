package ldg.study.springboot.tools.utils.encrypt;

/**
 * Base64编码 解码
 */
public class Base64Encoder {
    /**
     * @param s
     * @return
     */
    public static String getBase64(String s) {
        if (s == null) {
            return null;
        }
        return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
    }

    /**
     * 将 BASE64 编码的字符串 s 进行解码   解密
     *
     * @param s
     * @return
     */
    public static String getFromBASE64(String s) {
        if (s == null) {
            return null;
        }
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param ming
     * @return
     */
    public static String mTOa(Object ming) {
        return Base64Encoder.getBase64(Base64Encoder.getBase64(Base64Encoder.getBase64((String) ming)));
    }

    /**
     * @param an
     * @return
     */
    public static String aTOm(String an) {
        return Base64Encoder.getFromBASE64(Base64Encoder.getFromBASE64(Base64Encoder.getFromBASE64(an)));
    }

    public static void main(String[] args) {
        String a = mTOa("爪哇笔记".toString());
        System.out.println(a);//加密
        System.out.println(aTOm(a));//解密
    }

}
