package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/music")
@ServletAnnotation(
        url = "/music",
        title = "My favourite song",
        description = "Lil Skies Nowadays Lyrics",
        buttonText = "Check this out!",
        imagePath = "resources/lil skies.jpg"
)
public class ContentServlet3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/content3.jsp").forward(req, resp);
    }
}