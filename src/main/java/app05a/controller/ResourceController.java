package app05a.controller;

import app05a.domain.Login;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * 下载案例一：普通下载
 */
@Controller
public class ResourceController {

    private static final Log logger = LogFactory.getLog(ResourceController.class);

    @RequestMapping(value = "/login")
    public String login(@ModelAttribute Login login, HttpSession session, Model model) {
        model.addAttribute("login", new Login());
        if ("paul".equals(login.getUserName()) &&
                "secret".equals(login.getPassword())) {
            session.setAttribute("loggedIn", Boolean.TRUE);
            return "Main";
        } else {
            return "LoginForm";
        }
    }

    @RequestMapping(value = "/resource_download")
    public String downloadResource(HttpSession session, HttpServletRequest request,
                                   HttpServletResponse response) {
        if (session == null ||
                session.getAttribute("loggedIn") == null) {
            return "LoginForm";
        }
        String dataDirectory = request.
                getServletContext().getRealPath("/WEB-INF/images");
        File file = new File(dataDirectory, "10.jpg");
        if (file.exists()) {
            //关键代码
            // 【1】设置响应的内容类型为文件类型，当不清楚文件类型时，使用：application/octet-stream
            response.setContentType("image/jpeg");
            //【2】添加一个http响应标题，并赋值attachment;filename=文件名
            response.addHeader("Content-Disposition", "attachment; filename=10.jpg");
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            // if using Java 7, use try-with-resources
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (IOException ex) {
                // do something,
                // probably forward to an Error page
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        return null;
    }

}
