package station.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import station.service.ServiceFactory;
import station.service.ServiceFactoryImpl;

public class ServletDispatcher extends HttpServlet {

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
            throws ServletException {
        String url = req.getRequestURI();
        String context = req.getContextPath();
        url = url.substring(context.length(), url.lastIndexOf(".html"));
        Action action = ActionFactory.getAction(url);
        try (ServiceFactory factory = getServiceFactory()) {
            action.setServiceFactory(factory);
            Forwarder forwarder = action.execute(req, resp);
            if(forwarder != null && forwarder.isRedirect()) {
                resp.sendRedirect(context + forwarder.getUrl());
            } else {
                if(forwarder != null && forwarder.getUrl() != null) {
                    url = forwarder.getUrl();
                }
                req.getRequestDispatcher("/WEB-INF/jsp" + url + ".jsp")
                        .forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
