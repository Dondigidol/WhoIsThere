package ru.whoisthere.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Person {
    private String name;
    private String surname;
    private String department;
    private byte[] photo;

    public Person(String name, String surname, String department, byte[] photo) {
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.photo = photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }


    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getDepartment() {
        return this.department;
    }

    public BufferedImage getPhoto() {
        return biToImage();
    }

    public BufferedImage biToImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new ByteArrayInputStream(this.photo));
            double imgWidth = img.getWidth();
            double imgHeight = img.getHeight();
            double imgRatio = imgHeight / imgWidth;
            img = resize(img, (int)(100 * imgRatio), 100);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }




}