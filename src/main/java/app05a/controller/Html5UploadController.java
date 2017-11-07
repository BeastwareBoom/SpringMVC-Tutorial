package app05a.controller;

import app05a.domain.Html5UploadFile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by Asus on 2017/11/7.
 * html5的api客户端上传，多文件带进度条
 */

@Controller
public class Html5UploadController {

    private static final Log logger = LogFactory.getLog(Html5UploadController.class);

    @RequestMapping("/html5")
    public String html() {
        return "Html5";
    }

    @RequestMapping("/file_upload")
    public void upload(Html5UploadFile html5UploadFile, HttpServletRequest httpServletRequest) {
        MultipartFile multipartFile = html5UploadFile.getMultipartFile();
        String originalFilename = multipartFile.getOriginalFilename();
//获取部署应用在磁盘中的真实路径
        String realPath = httpServletRequest.getServletContext().getRealPath("/WEB-INF/images");
        File dir = new File(realPath);
        //若不存在，创建目录
        if (!dir.exists()) {
            dir.mkdir();
        }
        logger.info("realPath = " + realPath);
        File file = new File(realPath, originalFilename);
        try {
            //将上传的文件保存到目标路径下
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
