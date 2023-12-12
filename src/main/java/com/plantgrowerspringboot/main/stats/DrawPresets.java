package com.plantgrowerspringboot.main.stats;

import java.awt.*;

public class DrawPresets {


    public static void textBox(Graphics2D graphics, int x, int y , int width, int height, String text, Color color ){
        graphics.setColor(color);
        graphics.draw3DRect(x, y , width, height, false );
        graphics.drawString(text, x + width/2 - (text.length() * 5), y  + height/2);


    }
    public static void textBox(Graphics2D graphics, int x, int y , int width, int height, String text){
        graphics.setColor(Color.BLACK);
        graphics.draw3DRect(x, y , width, height, false );
        graphics.drawString(text, x + width/2 - (text.length() * 5), y  + height/2);


    }

    public static void halfRoundBox(Graphics2D graphics, int x, int y , int width, int height, Color color){
        int r = width /2;
        graphics.setColor(color);
        graphics.fillRect(x          ,    y             , width, height);
        graphics.fillRect(x +r       , y + r         , width, height);
        graphics.fillOval(x + width - r  , y -1          , width, width);
        graphics.fillOval(x - 1      , y + height  - r    , width, width);

    }
    public static void halfRoundBoxFrame(Graphics2D graphics, int x, int y , int width, int height, int borderSize,  Color color){
        halfRoundBox(graphics, x, y, width, height, Color.BLACK);
        width = width - borderSize;
        height =  height-borderSize;
        x =  x + borderSize;
        y =  y + borderSize;
        halfRoundBox(graphics, x, y, width, height, color);
//        x = x + 1;
//        y = y - 1;
//        width = width - 1;
//        height =  height-1;
//        halfRoundBox(graphics, x, y, width, height, color);
    }


}
