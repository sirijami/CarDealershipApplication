package com.neu.jan17.data;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.net.URL;

public class Util {

    /**
     *
     * @param filePath takes path to the file as a string
     * @return String of the whole content in the file
     * @throws Exception if no file is found at the path
     */
    public static String readFromFile(String filePath) throws Exception{
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        Integer available = fileInputStream.available();
        byte b[] = new byte[available];
        fileInputStream.read(b);
        fileInputStream.close();
        return new String(b);
    }

    /**
     *
     * @param filepath takes path to the file as a string
     * @param contentToWrite takes String content to be written
     * @param append is a boolean to append the current doc or to overwrite
     * @throws Exception if no file is found at the path
     */
    public static void writeToFile(String filepath,String contentToWrite, boolean append) throws Exception{

        File file = new File(filepath);
        FileWriter fileWriter = new FileWriter(file, append);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(contentToWrite);
        bufferedWriter.close();
    }


    /**
     *
     * @param urlPath takes the url link as a string
     * @return returns imageIcon which can be used in a label to show the image
     * @throws Exception if no image is found in the url
     */
    public static ImageIcon getImageFromUrl(String urlPath) throws Exception{

        URL url = new URL(urlPath);
        return new ImageIcon(ImageIO.read(url));

    }

    public static void setUIFont(FontUIResource f)
    {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while(keys.hasMoreElements())
        {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if(value instanceof FontUIResource)
                UIManager.put(key, f);

        }
    }
}
