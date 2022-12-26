package servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@WebServlet("/holder")
public class HolderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.println("<html>");
        pw.println("<title>");
        pw.println("Holder");
        pw.println("</title>");
        pw.println("<body style=\"background-color: peachpuff\">");

        try (Stream<Path> paths = Files.walk(Path.of(req.getServletContext().getRealPath("")))) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> getFileExt(path).equals("class"))
                    .forEach(source -> {
                        try {
                            Class<?> classClass = Class.forName("servlets." + source.getFileName().toString().split("\\.")[0]);
                            Annotation[] annotations = classClass.getAnnotations();

                            for (Annotation annotation : annotations) {
                                if (annotation instanceof ServletAnnotation) {
                                    final ServletAnnotation servletData = (ServletAnnotation) annotation;
                                    pw.println("<div style=\"align-items: center; display: flex; justify-content: center\">\n" +
                                            "        <div>\n" +
                                            "            <p style=\"font-size: 24px; text-align: center\">" + servletData.title() + "</p>\n" +
                                            "            <p style=\"font-size: 20px; text-align: center\">" + servletData.description() + "</p>\n" +
                                            "            <img style=\"margin-left: 15px\" src=\"" + servletData.imagePath() + "\"width=\"300\" height=\"300\">\n" +
                                            "            <form style=\"text-align: center; margin-top: 15px\">\n" +
                                            "                <a href=\"" + servletData.url() + "\"><input type=\"button\" value=\"" + servletData.buttonText() + "\"></a>\n" +
                                            "            </form>\n" +
                                            "            <br>\n" +
                                            "        </div>\n" +
                                            "    </div>");
                                }
                            }
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        pw.println("</body>");
        pw.println("</html>");
    }

    public static String getFileExt(Path path) {
        String filename = path.getFileName().toString();
        String[] components = filename.split("\\.");
        if (components.length == 1) {
            return "";
        }
        return components[components.length-1];
    }
}