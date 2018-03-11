package ut.com.evercode.servlet;

import com.atlassian.templaterenderer.TemplateRenderer;
import com.evercode.servlet.AssigneeServlet;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static org.mockito.Mockito.*;

public class AssigneeServletTest {

    private AssigneeServlet subject;
    private HttpServletRequest mockRequest;
    private HttpServletResponse mockResponse;
    private TemplateRenderer templateRenderer;

    @Before
    public void setup() {

        templateRenderer = mock(TemplateRenderer.class);
        mockRequest = mock(HttpServletRequest.class);
        mockResponse = mock(HttpServletResponse.class);
        subject = new AssigneeServlet(templateRenderer);
    }
    @Test
    public void shouldRenderAssigneeAfterPost() {
        //given
        String assigneeInput = "";
        when(mockRequest.getParameter("assignee-input")).thenReturn(assigneeInput);

        //when
        try {
            subject.doPost(mockRequest, mockResponse);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //then
        try {
            Mockito.verify(templateRenderer, Mockito.times(1)).render(Mockito.eq("view/assignee.vm"), Mockito.any(PrintWriter.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldRenderAssigneeOutputAfterPost() {
        //given
        String assigneeInput = "1,2";
        when(mockRequest.getParameter("assignee-input")).thenReturn(assigneeInput);

        //when
        try {
            subject.doPost(mockRequest, mockResponse);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //then
        try {
            Mockito.verify(templateRenderer, Mockito.times(1)).render(Mockito.eq("view/assignee-output.vm"), Mockito.any(Map.class) , Mockito.any(PrintWriter.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

