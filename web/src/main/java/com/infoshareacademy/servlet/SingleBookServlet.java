package com.infoshareacademy.servlet;

import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.BookService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/single")
public class SingleBookServlet extends HttpServlet {

    @Inject
    private BookService bookService;

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String param = req.getParameter("id");

        if (param == null || param.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        Long id = Long.valueOf(param);



        PrintWriter writer = resp.getWriter();

        Template template = templateProvider
                .getTemplate(getServletContext(),
                        "singlePage.ftlh");
        Map<String, Object> model = new HashMap<>();

        if (bookService.getView(id) != null) {
            model.put("book", bookService.getView(id));
            try {
                template.process(model, writer);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        } else {
            model.put("errorMessage", "Book not found");
            try {
                template.process(model, writer);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
    }
}