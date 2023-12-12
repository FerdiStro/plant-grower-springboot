package com.plantgrowerspringboot.main.stats;

import com.plant.plantgrow.model.Plant;
import com.plantgrowerspringboot.main.repository.Repository;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Base64;

public class CreateStats {

    private final Repository repository;


    //Pic
    private final static Integer height = 600;
    private final static Integer width = 300;
    private final static Integer fontSize  =  20;
    private final static Integer margin = 20;
    private final BufferedImage image;
    private final Graphics2D graphics;
    private final JPanel jPanel   = new JPanel( );

    public CreateStats(Repository repository){
        this.repository = repository;
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.graphics = image.createGraphics();
    }
    private BufferedImage getPlantPb(Plant plant){
        byte[] imageBytes = Base64.getDecoder().decode(plant.getPb());
        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
        try {
            return   ImageIO.read(bis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void paintPicture(Plant plant){
        //Setup
        BufferedImage image =  getPlantPb(plant);
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        graphics.setColor(Color.RED);
        graphics.fillRect(0, 0, width , height);
        graphics.setColor(Color.white);

        int x  = margin ;
        int y  = margin;
        int marginWidth  = (int)(width  * 0.65 - margin);
        int marginHeight = (int)(height * 0.85 - margin);

        // 280 / 2  = 140
        // 140 + 280 = 420;
        // 300 * 0,65  - 20 =  174
        // 600 * x  - 20 = 480
        DrawPresets.halfRoundBoxFrame(graphics, x, y, marginWidth, 480, 1, Color.WHITE);



        jPanel.setBackground(Color.white);
        this.graphics.setBackground(Color.white);

        //Text
//        DrawPresets.textBox(graphics, x, 30, 200, 100, plant.getName());
//        DrawPresets.textBox(graphics,x, 30, 200, 100, plant.getLast());
//        DrawPresets.textBox(graphics, width/2, 30, 200, 100, plant.getMos());
//        DrawPresets.textBox(graphics, 10 , 200, 200, 100, "AVG: "+ plant.getAvg());
        //pic
//        graphics.drawImage(image, width  / 2, height / 2, 200, 200 , null);


    }


    public String getStatsAsBase64String(String name){


        Plant plant = repository.get(name);
        //todo plant handling if null;
        paintPicture(plant);


        jPanel.paintComponents(graphics);
        graphics.dispose();
        try {


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageBytes = baos.toByteArray();


            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
         return null;
        }
    }



}


