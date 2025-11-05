package filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class CharsetFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("UTF-8");
        }

        res.setCharacterEncoding("UTF-8");
        if (res.getContentType() == null || res.getContentType().startsWith("text/")) {
            res.setContentType("application/html;charset=UTF-8");
        }
        chain.doFilter(req, res);

    }
}
