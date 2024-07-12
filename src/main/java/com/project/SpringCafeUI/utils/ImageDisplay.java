package com.project.SpringCafeUI.utils;

import java.awt.Component;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ImageDisplay {
	
	private static String getFullPath(Component frame) {
        JFileChooser fileChooser = new JFileChooser(new File("E:\\1_IUH-Documents\\4_HK2_year2\\Event_Driven_Programming_With_Java_Technology\\Excercise\\CafeUI\\images"));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.isFile() && isImageFile(selectedFile)) {
                return selectedFile.getPath();
            } else {
                JOptionPane.showMessageDialog(frame, "Vui lòng chọn file ảnh");
            }
        }
		return null;
	}
	
    private static boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png") || name.endsWith(".gif") || name.endsWith(".bmp");
    }
    
    public static String getPath(Component frame) {
    	String fullPath = getFullPath(frame);
    	if (fullPath.isBlank()) {
			JOptionPane.showMessageDialog(frame, "Đường dẫn rỗng", "Lỗi", JOptionPane.PLAIN_MESSAGE);
		} else {
			Path path = Paths.get(fullPath);
			String relativePath = "images\\" + path.getFileName();
			return relativePath;
		}
        return null;
    }
    
    public static String formatPath(String path) {
    	String[] strings = path.trim().split("-");
    	return strings[0] + "-%d" + strings[1].substring(3);
    }
}
