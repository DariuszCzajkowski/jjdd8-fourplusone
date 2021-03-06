package com.infoshareacademy.web.servlet;

import com.infoshareacademy.domain.view.BookView;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.BookService;
import com.infoshareacademy.service.PaginationService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/dramaCatalogue")
public class DramaCatalogueServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DramaCatalogueServlet.class.getName());

    @Inject
    private BookService bookService;

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private PaginationService paginationService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = (String) req.getSession().getAttribute("name");
        String email = (String) req.getSession().getAttribute("email");
        String role = (String) req.getSession().getAttribute("role");

        PrintWriter writer = resp.getWriter();

        String param = req.getParameter("bookNum");
        if (param == null || param.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Template wrongInput = templateProvider
                .getTemplate(getServletContext(),
                        "wrongInput.ftlh");
        Map<String, Object> wrongInputModel = new HashMap<>();


        if (param.matches("^[0-9]*$")) {
            int num = Integer.parseInt(param);

            int next = paginationService.add(num);

            int previous = paginationService.reduce(num);

            int lastPageView = paginationService.getLasPageDrama();

            List<BookView> bookViewList = bookService.getDramaBooksForPagination(num);

            Template template = templateProvider
                    .getTemplate(getServletContext(),
                            "drama-catalogue.ftlh");
            Map<String, Object> model = new HashMap<>();
            model.put("catalogue", bookViewList);
            model.put("next", next);
            model.put("previous", previous);
            model.put("lastPageView", lastPageView);
            if (email != null && !email.isEmpty()) {
                model.put("logged", "yes");
                model.put("email", email);
            } else {
                model.put("logged", "no");}
            if(role != null && role.equals("superadmin")) {
                model.put("superadmin", "yes");
            }
            else {model.put("superadmin", "no");}
            try {
                template.process(model, writer);
            } catch (TemplateException e) {
                logger.error("Template error");
            }
        } else {
            try {
                wrongInputModel.put("name", writer);
                wrongInput.process(wrongInputModel, writer);
            } catch (TemplateException e) {
                logger.error("Template error");
            }
        }
    }
}
