package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/memes")
@ServletAnnotation(
        url = "/memes",
        title = "Memes",
        description = "Some memes :) Just have fun!",
        buttonText = "Go for memes!",
        imagePath = "resources/pepe.png"
)
public class ContentServlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/content1.jsp").forward(req, resp);
    }
}