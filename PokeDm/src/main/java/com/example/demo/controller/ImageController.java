package com.example.demo.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/image")
public class ImageController {

    @GetMapping("/generate")
    public String generateAndDisplayImage(Model model) {
        // テキストから画像を生成
        BufferedImage image = generateImage("apple");

        // 画像データをモデルに追加
        model.addAttribute("imageData", convertToByteArray(image));

        // 画像表示のためのビュー名を返す
        return "imageDisplay";
    }

    private BufferedImage generateImage(String text) {
        int width = 400;
        int height = 200;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.BLACK);
        g2d.drawString(text, 10, 30);
        g2d.dispose();

        return image;
    }

    private byte[] convertToByteArray(BufferedImage image) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}