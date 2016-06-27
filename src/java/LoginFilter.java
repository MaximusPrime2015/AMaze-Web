import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserEx3;
import model.UserDatabaseEx3;

/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author Max Anisimov 322068487 anisimm
 */
@WebFilter(filterName = "LoginFilter",
                            urlPatterns = {"/secure/*"}, asyncSupported=true)
public class LoginFilter implements Filter {

    /**
     * Checks that a user can access certain pages.
     * @param request http request
     * @param response http response
     * @param chain filter chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // filter enters to secured pages by Session checking
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        if (session != null && session.getAttribute("name") != null) {
            UserEx3 user = UserDatabaseEx3.getInstance()
                        .getUser(session.getAttribute("username").toString());
            if (user == null) {
                // fake session
                session.invalidate();
                ((HttpServletResponse) response).sendRedirect("/LoginServlet");
            }
            // user is ok
            chain.doFilter(request, response);
        } else {
            // no session -> send to login page
            ((HttpServletResponse) response).sendRedirect("/LoginServlet");
        }
    }

    /**
     * Does nothing.
     * function from interface.
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    /**
     * Does nothing.
     * function from interface.
     */
    @Override
    public void destroy() {}
}
