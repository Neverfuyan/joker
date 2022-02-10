package com.wj.springcloud.config;

import com.wj.springcloud.constant.AuthConstant;
import com.wj.springcloud.entities.OAuthUserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：actor
 * @date ：Created in 2021/7/26 18:17
 * @description：
 * @modified By：
 * @version: $
 */
public class JWTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        if (oAuth2Authentication.getPrincipal() instanceof  OAuthUserDetails){
            OAuthUserDetails OAuthUserDetails = (OAuthUserDetails) oAuth2Authentication.getUserAuthentication().getPrincipal();
            Map<String, Object> info = new HashMap<>();
            //把用户ID设置到JWT中
            info.put(AuthConstant.USER_ID_KEY, OAuthUserDetails.getId());
            info.put(AuthConstant.CLIENT_ID_KEY,OAuthUserDetails.getClientId());
            ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        }
        return oAuth2AccessToken;
    }
}
