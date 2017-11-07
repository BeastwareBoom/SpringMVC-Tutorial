package app05a.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * Created by Asus on 2017/11/7.
 */
public class Html5UploadFile implements Serializable {
    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
