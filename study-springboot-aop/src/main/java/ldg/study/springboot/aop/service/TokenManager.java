package ldg.study.springboot.aop.service;

/**
 * author：      ldg
 * create date： 2018/4/17
 */
public interface TokenManager {

    String createToken(String username);

    boolean checkToken(String token);
}
