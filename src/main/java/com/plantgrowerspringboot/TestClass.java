package com.plantgrowerspringboot;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Base64;

public class TestClass {


    public static String getBase64(String url) {
        BufferedImage img = null;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            img = ImageIO.read(new File(url));
            ImageIO.write( img , "jpg", output );
        } catch (Exception ignored){}
        byte [] data = output.toByteArray();

        return Base64.getEncoder().encodeToString(data);
    }

}
