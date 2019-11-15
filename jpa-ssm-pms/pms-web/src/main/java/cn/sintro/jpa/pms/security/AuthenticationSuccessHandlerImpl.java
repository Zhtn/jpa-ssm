package cn.sintro.jpa.pms.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler{

	// 身份认证方法
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		System.out.println(authentication);
		Object details = authentication.getDetails();
		String username=(String) JSONObject.fromObject(details).get("username");


		request.setAttribute("username",username);
		request.getRequestDispatcher("/system/loginSuccess.do").forward(request,response);
	}
}
