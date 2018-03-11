package com.evercode.servlet;

import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.templaterenderer.TemplateRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Scanned
public class AssigneeServlet extends HttpServlet{
    private static final Logger log = LoggerFactory.getLogger(AssigneeServlet.class);

    @ComponentImport
    private final TemplateRenderer templateRenderer;

    public AssigneeServlet(TemplateRenderer templateRenderer) {
        this.templateRenderer = templateRenderer;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        templateRenderer.render("view/assignee.vm", resp.getWriter());
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String assigneeInput = (req.getParameter("assignee-input"));
        Map<String, Object> context = new HashMap<>();
        String[] assigneeInputItems = assigneeInput.split(",");
        if (assigneeInputItems.length == 1 && assigneeInputItems[0].equals("")){
            templateRenderer.render("view/assignee.vm", resp.getWriter());
        }else{
            context.put("assigneeOutput", assigneeInputItems);
            templateRenderer.render("view/assignee-output.vm", context, resp.getWriter());
        }
    }
}