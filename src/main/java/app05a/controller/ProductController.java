package app05a.controller;

import app05a.domain.Product;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 2017/11/6.
 */
public class ProductController {

    private static final Log logger = LogFactory.getLog(BookController.class);

    @RequestMapping("/product_input")
    public String inputProduct(Model model) {
        model.addAttribute("product", new Product());
        return "ProductForm";
    }

    @RequestMapping("/product_save")
    public String saveProduct(HttpServletRequest request, @ModelAttribute Product product) {
        List<MultipartFile> images = product.getImages();
        if (null != images && images.size() > 0) {
            List<String> fileNames = new ArrayList<String>();
            for (MultipartFile image : images) {
                //获取源文件名称
                String filename = image.getOriginalFilename();
                fileNames.add(filename);
                File file = new File(request.getServletContext().getRealPath("/images"), filename);
                try {
                    //将上传的文件保存到目标路径下
                    image.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "ProductDetails";
    }

}
