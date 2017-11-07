package app05a.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 防止交叉引用
 */
@Controller
public class ImageController {

    private static final Log logger = LogFactory.getLog(ImageController.class);

    @RequestMapping(value = "/image_get/{id}", method = RequestMethod.GET)
    public void getImage(@PathVariable String id,
                         HttpServletRequest request,
                         HttpServletResponse response,
                         @RequestHeader String referer) {
        /**
         * 仅当refer标题不为null时，才将图片资源发送给浏览器，这样可以防止在浏览器中仅输入网址就能够下载图片
         * refer:http://localhost:8080/images.html
         * 标识引用的来源。
         * 1，防止盗连，比如我是个下载软件的网站，在下载页面我先用referer来判断上一页面是不是自己网站，如果不是，说明有人盗连了你的下载地址。
         * 2，电子商务网站的安全，我在提交信用卡等重要信息的页面用referer来判断上一页是不是自己的网站，如果不是，可能是黑客用自己写的一个表单，
         * 来提交，为了能跳过你上一页里的javascript的验证等目的。
         *
         */

        logger.info("refer:" + referer);

        if (referer != null) {
            String imageDirectory = request.getServletContext().getRealPath("/WEB-INF/images");
            File file = new File(imageDirectory,
                    id + ".jpg");
            if (file.exists()) {
                response.setContentType("image/jpg");
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                // if you're using Java 7, use try-with-resources
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
                    // do something here
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
        }
    }
}
