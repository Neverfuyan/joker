package com.wj.springcloud.constant;

/**
 * 权限相关常量定义
 */
public interface AuthConstant {
    /**
     * JWT令牌前缀
     */
    String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * JWT载体key
     */
    String JWT_PAYLOAD_KEY = "payload";

    String USER_ID_KEY = "userId";

    String USER_NAME_KEY = "user_name";

    String CLIENT_ID_KEY = "client_id";

    /**
     * 认证请求头key
     */
    String AUTHORIZATION_KEY = "Authorization";

    /**
     * JWT令牌前缀
     */
    String AUTHORIZATION_PREFIX = "Bearer ";


    /**
     * JWT存储权限属性
     */
    String JWT_AUTHORITIES_KEY = "authorities";


    Integer STATUS_YES = 1;

    /**
     * JWT ID 唯一标识
     */
    String JWT_JTI = "jti";

    /**
     * JWT ID 唯一标识 ,JWT过期时间戳(单位：秒)
     */
    String JWT_EXP = "exp";

    /**
     * 白名单token前缀
     */
    String TOKEN_WHITELIST_PREFIX = "auth:token:whitelist:";

    String URL_PERM_ROLES_KEY = "system:perm_roles_rule:url:";
    String BTN_PERM_ROLES_KEY = "system:perm_roles_rule:btn:";

    String SUPER_ADMIN_ID = "1";
    String SUPER_ADMIN = "jysp";
    String SUPER_ADMIN_PASSWORD = "Nari@2021#";

    String ALL = "all";
    String AUTHORIZED_GRANT_TYPES = "password,client_credentials,refresh_token,authorization_code";
    Integer ACCESS_TOKEN_VALIDITY = 3600 * 24;
    Integer REFRESH_TOKEN_VALIDITY = 3600 * 24 * 7;

}
