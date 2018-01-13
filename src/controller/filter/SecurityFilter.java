package controller.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.user.Role;
import domain.user.User;

public class SecurityFilter implements Filter {
    private static final Map<String, Set<Role>> permissions = new HashMap<>();

    static {
        Set<Role> all = new HashSet<>();
        all.addAll(Arrays.asList(Role.values()));
        Set<Role> users = new HashSet<>();
        users.add(Role.ADMINISTRATOR);
        users.add(Role.SUBSCRIBER);
        Set<Role> administrator = new HashSet<>();
        administrator.add(Role.ADMINISTRATOR);
        Set<Role> subscirber = new HashSet<>();
        subscirber.add(Role.SUBSCRIBER);
        
        permissions.put("/", all);
        permissions.put("/index", all);
        permissions.put("/login", all);
        permissions.put("/logout", users);
        permissions.put("/administrator/delete", administrator);
        permissions.put("/administrator/edit", administrator);
        permissions.put("/administrator/list", administrator);
        permissions.put("/administrator/save", administrator);
        permissions.put("/administrator/view", administrator);
        permissions.put("/subscriber/list", administrator);
        permissions.put("/subscriber/edit", administrator);
        permissions.put("/subscriber/save", administrator);
        permissions.put("/subscriber/delete", administrator);
        permissions.put("/subscriber/ban", administrator);
        permissions.put("/offer/list", all);
        permissions.put("/offer/edit", administrator);
        permissions.put("/offer/save", administrator);
        permissions.put("/offer/delete", administrator);
        permissions.put("/subscription/list", subscirber);
        permissions.put("/subscription/accept", subscirber);
        permissions.put("/subscription/reject", subscirber);
        permissions.put("/rate/edit", administrator);
        permissions.put("/rate/list", all);
        permissions.put("/rate/save", administrator);
        permissions.put("/call/dial", subscirber);
        permissions.put("/call/make-call", subscirber);
        permissions.put("/invoice/control", administrator);
        permissions.put("/invoice/create", administrator);
        permissions.put("/invoice/delete", administrator);
        permissions.put("/invoice/list", subscirber);
        permissions.put("/invoice/view", subscirber);
        permissions.put("/payment/pay", subscirber);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest)request;
        HttpServletResponse httpResp = (HttpServletResponse)response;
        String url = httpReq.getRequestURI();
        String context = httpReq.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");
        if(postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length()) + "index";
        }
        Set<Role> roles = permissions.get(url);
        if(roles != null) {
            HttpSession session = httpReq.getSession(false);
            if(session != null || roles.contains(Role.GUEST)) {
                User user = null;
                if (session != null) {
                    user = (User)session.getAttribute("currentUser");
                }
                if((user != null && roles.contains(user.getRole()))
                        || roles.contains(Role.GUEST)) {
                    chain.doFilter(request, response);
                    return;
                }
            }
        } else {
            chain.doFilter(request, response);
            return;
        }
        httpResp.sendRedirect(context +
                "/login.html?message=login.message.access.denied");
    }

    @Override
    public void destroy() { }
}
