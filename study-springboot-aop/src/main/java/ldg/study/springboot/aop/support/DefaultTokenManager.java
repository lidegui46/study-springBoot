package ldg.study.springboot.aop.support;

import ldg.study.springboot.aop.service.TokenManager;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认Token生成器
 * author：      ldg
 * create date： 2018/4/17
 */
@Service("defaultTokenManager")
public class DefaultTokenManager implements TokenManager {

    private static Map<String, String> tokenMap = new ConcurrentHashMap<>();

    @Override
    public String createToken(String username) {
        String token = UUID.randomUUID().toString().replace("-","");
        tokenMap.put(token, username);
        return token;
    }

    @Override
    public boolean checkToken(String token) {
        return !StringUtils.isEmpty(token) && tokenMap.containsKey(token);
    }
}