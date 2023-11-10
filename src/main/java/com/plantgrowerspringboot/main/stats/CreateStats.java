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
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Base64;

public class CreateStats {

    private final Repository repository;


    //Pic
    private final static Integer height = 600;
    private final static Integer width = 500;
    private final static Integer fontSize  =  20;
    private final BufferedImage image;
    private final Graphics2D graphics;
    private final JPanel jPanel   = new JPanel( );

    public CreateStats(Repository repository){
        this.repository = repository;
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.graphics = image.createGraphics();
//        try {
//            //write file
//            ImageIO.write(image, "png", new File("test.png"));
//
//            //get base64 string
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(image, "png", baos);
//            byte[] imageBytes = baos.toByteArray();
//            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//
//
//
//        } catch (Exception e) {
//           e.printStackTrace();
//        }
    }

    public void paintPicture(Plant plant){

        graphics.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        graphics.setColor(new Color(255, 255, 255, 0));
        graphics.fillRect(0, 0, width , height);
        graphics.setColor(Color.white);


//        int x = 50;
//        int y = 60;
//        int w = 200;
//        int h = 400;
//        int d = w;
//        int r = d/2;

//        graphics.fillRect( x            ,    y             , w, h);
//        graphics.fillRect(x +r       , y + r         , w, h);
//        graphics.fillOval(x + w - r  , y -1          , d, d);
//        graphics.fillOval(x - 1      , y + h  - r    , d, d);
        DrawPresets.halfRoundBoxFrame(graphics, 50, 60, 200, 400, Color.WHITE);



        jPanel.setBackground(Color.white);
        this.graphics.setBackground(Color.white);

        //name
        DrawPresets.textBox(graphics, width/2, 30, 200, 100, plant.getName());



        //avg
        DrawPresets.textBox(graphics, 10 , 200, 200, 100, "AVG: "+ plant.getAvg());







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


