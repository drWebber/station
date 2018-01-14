package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.ServiceFactory;
import service.ServiceFactoryImpl;

public class ServletDispatcher extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = 
            LogManager.getLogger(ServletDispatcher.class.getName());
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    public ServiceFactory getServiceFactory() {
        return new ServiceFactoryImpl();
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String url = req.getRequestURI();
        String context = req.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");
        if(postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length()) + "index";
        }
        Action action = ActionFactory.getAction(url);
        Forwarder forwarder = null;
        if (action != null) {
            try(ServiceFactory factory = getServiceFactory()) {
                action.setServiceFactory(factory);
                forwarder = action.execute(req, resp);
                if (forwarder != null && forwarder.isRedirect()) {
                    resp.sendRedirect(context + forwarder.getUrlWithAttributes());
                } else {
                    if(forwarder != null && forwarder.getUrl() != null) {
                        url = forwarder.getUrl();
                    }
                    req.getRequestDispatcher("/WEB-INF/jsp" + url + ".jsp")
                    .forward(req, resp);
                }
            } catch(Exception e) {
                logger.error(e);
                getServletContext()
                    .getRequestDispatcher("/WEB-INF/jsp/error.jsp")
                    .forward(req, resp);
            }
        } else {
            logger.warn("Action for requestURI '" + req.getRequestURI() + 
                    "' not found.");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/404.jsp")
                .forward(req, resp);
            
        }
    }
}
