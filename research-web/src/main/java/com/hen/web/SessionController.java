package com.hen.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liaohenry
 * @version 2016年10月26日 下午3:09:25
 */
@Controller
public class SessionController {
	private static final String SESSION_KEY = "test";

	@ResponseBody
	@RequestMapping(value = "/sid", method = RequestMethod.GET)
	public String getSessionId(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		StringBuilder builder = new StringBuilder();
		if (session.isNew()) {
			session.setAttribute(SESSION_KEY, "test");
			builder.append("new session: ").append(session.getId()).append("; put cache content --").append("test");
		} else {
			builder.append("old session: ").append(session.getId()).append("; get cache content --")
					.append(session.getAttribute(SESSION_KEY));
		}
//		String url = response.encodeURL(request.getRequestURL().toString());
//		try {
//			response.getWriter().write(url);
//		} catch (IOException e) {
//			return "fail to rewrit url";
//		}
		return builder.toString();
	}
}
