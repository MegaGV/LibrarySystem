package xzz.library.util;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.UUID;

public class MD5Utils {
    public static String md5Code(String username, String password) {
        return new Md5Hash(password, username, 100).toString();
    }

    public static void main(String[] args) {
        String id = UUID.randomUUID().toString();
        System.out.println(id);
        System.out.println(md5Code("admin","123456"));
    }
}
