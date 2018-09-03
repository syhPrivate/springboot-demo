package com.test.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.entity.User;

@Controller
@RequestMapping("login")
public class LoginController {
	Logger logger = Logger.getLogger(this.getClass().getName());

	@RequestMapping("toLogin")
	public String toLogin() {
		System.out.println("-----toLogin-----");
		return "login";
	}

	@RequestMapping("test")
	@RequiresPermissions("update")
	public void test() {
		System.out.println("-----test update-----");
	}

	@RequestMapping("logOut")
	public String logOut(HttpSession session) {
		System.out.println("-----logout-----");
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login";
	}

	@RequestMapping("doLogin")
	public String doLogin(User user, boolean remeberme, RedirectAttributes redirectAttributes,
			BindingResult bindingResult,HttpSession httpSession) {
		System.out.println("-----dologin----");
		if (bindingResult.hasErrors()) {
			return "redirect:login/toLogin";
		}
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword(),
				remeberme);
		String username = user.getUsername();

		Subject subject = SecurityUtils.getSubject();
		try {
			try {
				// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
				// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
				// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
				logger.info("对用户[" + username + "]进行登录验证..验证开始");
				subject.login(usernamePasswordToken);
				logger.info("对用户[" + username + "]进行登录验证..验证通过");
			} catch (UnknownAccountException uae) {
				logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
				redirectAttributes.addFlashAttribute("message", "未知账户");
			} catch (IncorrectCredentialsException ice) {
				logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
				redirectAttributes.addFlashAttribute("message", "密码不正确");
			} catch (LockedAccountException lae) {
				logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
				redirectAttributes.addFlashAttribute("message", "账户已锁定");
			} catch (ExcessiveAttemptsException eae) {
				logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数大于5次,账户已锁定");
				redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数大于5次,账户已锁定");
			} catch (DisabledAccountException sae) {
				logger.info("对用户[" + username + "]进行登录验证..验证未通过,帐号已经禁止登录");
				redirectAttributes.addFlashAttribute("message", "帐号已经禁止登录");
			} catch (AuthenticationException ae) {
				// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
				logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
				ae.printStackTrace();
				redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
			}
			if (subject.isAuthenticated()) {
				User currentUser = (User) subject.getPrincipal();
				subject.getSession().setAttribute("user", currentUser);
			}
			httpSession.setAttribute("username", user.getUsername());
			return "index";
		} catch (Exception e) {
			return "login";// 返回登录页面
		}
	}

}
