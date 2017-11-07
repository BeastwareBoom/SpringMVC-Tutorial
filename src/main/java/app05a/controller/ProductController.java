package app05a.controller;

import app05a.domain.Product;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 2017/11/6.
 */
@Controller
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
        logger.info("images:"+(images==null));
        if (null != images && images.size() > 0) {
            List<String> fileNames = new ArrayList<String>();
            for (MultipartFile image : images) {
                //获取源文件名称
                String filename = image.getOriginalFilename();
                fileNames.add(filename);
                //获取部署应用在磁盘中的真实路径
                String realPath = request.getServletContext().getRealPath("/WEB-INF/images");
                File dir = new File(realPath);
                //若不存在，创建目录
                if (!dir.exists()) {
                    dir.mkdir();
                }
                logger.info("realPath = " + realPath);
                File file = new File(realPath, filename);
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

    //测试Flash属性，需更改ProduceForm.jsp中表单的action为product_save_and_view
    @RequestMapping("/product_save_and_view")
    public String saveProductAndView(@ModelAttribute Product product, RedirectAttributes redirectAttributes, Model model) {
        //ProductView.jsp中已取不到，同样，@ModelAttribute Product product也取不到
        model.addAttribute("message_model", "产品添加成功。");
        //重定向传参，Flash属性
        redirectAttributes.addFlashAttribute("message_flash", "The product was successfully added.");
        return "redirect:/product_view/" + product.getName();
    }

    @RequestMapping(value = "/product_view/{name}")
    public String viewProduct(@PathVariable String name, Model model) {
        //这里可以通过传入的Model，通过Service进行查询，进而显示信息
//        Product product = productService.get(id);
        return "ProductView";
    }

}
