package com.project.SpringCafeUI.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class FileSaver {
    private static final String PATH = "invoice\\bill.txt";

    public static void saveToFile(JTextArea textArea) {
        try {
            String content = textArea.getText();
            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
