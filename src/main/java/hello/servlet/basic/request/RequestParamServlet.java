package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("전체 파라미터 조회 - start");
        request.getParameterNames().asIterator().
                forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));
        System.out.println("전체 파라미터 조회 - end");

        System.out.println("개별 파라미터 조회 - start");
        String username = request.getParameter("username");
        System.out.println("username =" + username);
        String age = request.getParameter("age");
        System.out.println("age =" + age);
        System.out.println("개별 파라미터 조회 - end");

        System.out.println("이름이 같은 복수 파라미터 조회 - start");
        String [] names = request.getParameterValues("username");
        for (String name : names) {
            System.out.println("name = " + name);
        }
        System.out.println("이름이 같은 복수 파라미터 조회 - end");

        response.getWriter().write("ok");
    }
}
