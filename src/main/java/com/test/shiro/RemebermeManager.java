package com.test.shiro;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 记住账号管理中心
 * @author songyunhui
 *
 */
@Configuration
public class RemebermeManager {

	/**
	  * cookie对象;
	  * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
	  * @return
	 */
	@Bean
	public SimpleCookie rememberMeCookie(){
	      //System.out.println("ShiroConfiguration.rememberMeCookie()");
	      //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
	      SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
	      //<!-- 记住我cookie生效时间30天 ,单位秒;-->
	      simpleCookie.setMaxAge(259200);
	      return simpleCookie;
	}

	/**
	  * cookie管理对象;
	  * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
	  * @return
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager(){
	      //System.out.println("ShiroConfiguration.rememberMeManager()");
	      CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
	      cookieRememberMeManager.setCookie(rememberMeCookie());
	      //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
	      cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
	      return cookieRememberMeManager;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager defaultWebSecurityManager(AuthRealm realm){
	      DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
	      //设置realm
	      securityManager.setRealm(realm);
//	      用户授权/认证信息Cache
//	      securityManager.setCacheManager(getCacheManager());
	      //注入记住我管理器
	      securityManager.setRememberMeManager(rememberMeManager());
	      return securityManager;
	}
}
