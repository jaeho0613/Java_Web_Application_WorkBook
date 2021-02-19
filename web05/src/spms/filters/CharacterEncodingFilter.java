package spms.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
		urlPatterns = "/*",
		initParams = {@WebInitParam(name="encoding", value ="utf-8")}
		)
public class CharacterEncodingFilter implements Filter {
	FilterConfig config;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		request.setCharacterEncoding(config.getInitParameter("encoding"));
		
		// chain.doFilter를 기준으로 위에 적으면 서블릿을 호출하기 전에 사용할 코드
		chain.doFilter(request, response);
		// chain.doFilter를 기준으로 아래에 적으면 서블릿을 호출후 사용할 코드
	}

	@Override
	public void destroy() {
		
	}
}
