package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/recipes")
@ServletAnnotation(
        url = "/recipes",
        title = "Student recipes",
        description = "Simple recipes for students!",
        buttonText = "Start cooking!",
        imagePath = "resources/recipes.jpg"
)
public class ContentServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/content2.jsp").forward(req, resp);
    }
}