package com.mtoolkit.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

/**
 * A utility class that provides image common operations. 
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 3/8/2011
 * @since 	JDK1.5
 */
public class ImageUtil {
	
	/**
	 * Private constructor, not permit to construct the instance.
	 */
	private ImageUtil() {
	}
	
	/**
	 * Converts image byte array to BufferedImage.
	 * 
	 * @param  bytes image byte array.
	 * 
	 * @return BufferedImage instance.
	 * 
	 * @throws IOException if an error occurs during reading.
	 * @throws NullPointerException if {@code bytes} is null.
	 */
	public static BufferedImage bytes2Image(byte[] bytes) throws IOException {
		InputStream input = new ByteArrayInputStream(bytes);
		
		return ImageIO.read(input);
	}
	
	/**
	 * Converts the BufferedImage to byte array with given format name.
	 *  
	 * @param  image  buffered image instance.
	 * @param  format image format name.
	 * 
	 * @return image byte array.
	 * 
	 * @throws IOException if an error occurs during writing.
	 * @throws IllegalArgumentException if {@code image} or {@code format} is null.
	 */
	public static byte[] image2Bytes(BufferedImage image, String format) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(image, format, output);
		
		return output.toByteArray();
	}
	
	/**
	 * Gets image format type from specified image byte array.
	 * 
	 * @param  bytes image byte array.
	 * 
	 * @return image format type.
	 * 
	 * @throws IOException if a cache file is needed but cannot be created.
	 * @throws NullPointerException if {@code bytes} is null.
	 */
	public static String getType(byte[] bytes) throws IOException {
		return getType(new ByteArrayInputStream(bytes));
	}
	
	/**
	 * Gets image format type from specified image file name.
	 * 
	 * @param  file image file name.
	 * 
	 * @return image format type.
	 * 
	 * @throws IOException if a cache file is needed but cannot be created.
	 * @throws NullPointerException if {@code file} is null.
	 */
	public static String getType(String file) throws IOException {
		return getType(new File(file));
	}
	
	/**
	 * Gets image format type from specified image file.
	 * 
	 * @param  file image file.
	 * 
	 * @return image format type.
	 * 
	 * @throws IOException if a cache file is needed but cannot be created.
	 * @throws NullPointerException if {@code file} is null.
	 */
	public static String getType(File file) throws IOException {
		return getType(new FileInputStream(file));
	}
	
	/**
	 * Gets image format type from specified image input stream.
	 * 
	 * @param  input image input stream.
	 * 
	 * @return image format type.
	 * 
	 * @throws IOException if a cache file is needed but cannot be created.
	 * @throws IllegalArgumentException if {@code input} is null.
	 */
	public static String getType(InputStream input) throws IOException {
		ImageInputStream imageInput = ImageIO.createImageInputStream(input);
        Iterator<ImageReader> iterator = ImageIO.getImageReaders(imageInput);
        
        String type = null;
        ImageReader reader = null;
        if (iterator.hasNext()) {
            reader = iterator.next();
            type = reader.getFormatName().toUpperCase();
            reader.dispose();
        }

        try {
            return type;
        } finally {
            if (imageInput != null) {
                imageInput.close();
            }
        }
	}
	
	/**
	 * Checks the specified image is more than the specified size.
	 * 
	 * @param  image  image instance.
	 * @param  width  image max width.
	 * @param  height image max height.
	 * 
	 * @return {@code true} if and only if the width and height of image 
	 * 		   is less than the specified size; {@code false} otherwise.
	 * 
	 * @throws NullPointerException if {@code image} is null.
	 */
	public static boolean checkSize(BufferedImage image, int width, int height) {
		return image.getWidth() < width && image.getHeight() < height;
	}
	
	/**
	 * Flips the specified image.
	 * 
	 * @param  image image instance.
	 * 
	 * @return flipped image.
	 * 
	 * @throws NullPointerException if {@code image} is null.
	 */
	public static BufferedImage flip(BufferedImage image) {
        int width  = image.getWidth();
        int height = image.getHeight();
        int type   = image.getColorModel().getTransparency();
        
        BufferedImage flippedImage = new BufferedImage(width, height, type);
        
        Graphics2D graphics2d = flippedImage.createGraphics();
        graphics2d.setRenderingHint(
                RenderingHints.KEY_RENDERING, 
                RenderingHints.VALUE_RENDER_QUALITY);

        graphics2d.drawImage(image, 0, 0, width, height, width, 0, 0, height, null);
        
        try {
            return flippedImage;
        } finally {
            if (graphics2d != null) {
                graphics2d.dispose();
            }
        }
	}
	
	/**
	 * Rotates the image with specified degree. 
	 * 
	 * @param  image  image instance.
	 * @param  degree rotate degree.
	 * 
	 * @return rotated image.
	 * 
	 * @throws NullPointerException if {@code image} is null.
	 */
	public static BufferedImage rotate(BufferedImage image, int degree) {
        int width  = image.getWidth();
        int height = image.getHeight();
        int type   = image.getColorModel().getTransparency();

        BufferedImage rotatedImage = new BufferedImage(width, height, type);
        
        Graphics2D graphics2d = rotatedImage.createGraphics();
        graphics2d.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        graphics2d.rotate(Math.toRadians(degree), (float) width / 2, (float) height / 2);
        graphics2d.drawImage(image, 0, 0, null);
        
        try {
            return rotatedImage;
        } finally {
            if (graphics2d != null) {
                graphics2d.dispose();
            }
        }
	}
	
	/**
	 * Compresses the specified image.
	 * 
	 * @param  image image instance.
	 * 
	 * @return compressed image instance.
	 * 
	 * @throws NullPointerException if {@code image} is null.
	 */
	public static BufferedImage compress(BufferedImage image) {
        int width  = image.getWidth();
        int height = image.getHeight();
        
        BufferedImage compressedImage = new BufferedImage(
        		width, height, BufferedImage.TYPE_INT_RGB);
        
        Graphics2D graphice2d = compressedImage.createGraphics();
        graphice2d.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        
        Image compImage =  image.getScaledInstance(
        		width, height, Image.SCALE_AREA_AVERAGING);
        graphice2d.drawImage(compImage, 0, 0, width, height, null);

        try {
            return compressedImage;
        } finally {
            if (graphice2d != null) {
                graphice2d.dispose();
            }
        }
	}
	
	/**
	 * Resizes the specified image to specified percent size.
	 * 
	 * @param  image   image instance.
	 * @param  percent resize percent.
	 * 
	 * @return resized image instance.
	 * 
	 * @throws NullPointerException if {@code image} is null.
	 */
	public static BufferedImage resize(BufferedImage image, int percent) {
		if (percent == 0) {
            return image;
        }
		
        int width  = image.getWidth();
        int height = image.getHeight();
        double newpercent = (double) Math.abs(percent) / 100;
        
        int newWidth  = 0;
        int newHeight = 0;
        if (percent > 0) {
            newWidth = (int) (width + (width * newpercent));
            newHeight = (int) (height + (height * newpercent));
        } else if (percent < 0) {
            newWidth = (int) (width - (width * newpercent));
            newHeight = (int) (height - (height * newpercent));
        }
        
        return resize(image, newWidth, newHeight);
	}
	
	/**
	 * Resizes the specified image to specified width and height size.
	 * 
	 * @param  image  image instance for resize.
	 * @param  width  resized image width.
	 * @param  height resized image height.
	 * 
	 * @return resized image instance.
	 * 
	 * @throws NullPointerException if {@code image} is null.
	 */
	public static BufferedImage resize(BufferedImage image, int width, int height) {
        int type = image.getColorModel().getTransparency();
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        
        Graphics2D graphics2d = resizedImage.createGraphics();
        graphics2d.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        
        graphics2d.drawImage(
        		image, 0, 0, width, height, 0, 0, image.getWidth(), image.getHeight(), null);
        
        try {
            return resizedImage;
        } finally {
            if (graphics2d != null) {
                graphics2d.dispose();
            }
        }
	}
	
	/**
	 * Parsed the watermark image to the specified image from the specified point.
	 * 
	 * @param  image 	 image witch for parsed watermark. 
	 * @param  watermark watermark image.
	 * @param  point 	 start point.
	 * 
	 * @return image instance after parsed watermark.
	 * 
	 * @throws NullPointerException if {@code image} or {@code watermark} or {@code point} is null.
	 */
	public static BufferedImage watermark(BufferedImage image, BufferedImage watermark, Point point) {
        int iWidth  = image.getWidth();
        int iHeight = image.getHeight();
        
        int wWidth  = watermark.getWidth();
        int wHeight = watermark.getHeight();
        
        int perWidth  = iWidth - point.x;
        int perHeight = iHeight - point.y;
        
        if (perWidth <= 0 || perHeight <= 0) {
            return image;
        }
        
        int type = image.getColorModel().getTransparency();
        BufferedImage markedImage = new BufferedImage(iWidth, iHeight, type);

        Graphics2D graphics2d = markedImage.createGraphics();
        graphics2d.setBackground(Color.BLACK);
        graphics2d.drawImage(image, 0, 0, iWidth, iHeight, null);
        
        int markWidth  = wWidth  < perWidth  ? wWidth  : perWidth;
        int markHeight = wHeight < perHeight ? wHeight : perHeight;

        graphics2d.drawImage(watermark, point.x, point.y, markWidth, markHeight, null);

        try {
            return markedImage;
        } finally {
            if (graphics2d != null) {
                graphics2d.dispose();
            }
        }
	}
	
	/**
	 * Prints the given image to specified output with type and quality.
	 *  
	 * @param  image   image instance witch to print.
	 * @param  output  output stream.
	 * @param  format  image format name.
	 * @param  quality a float between ${code 0} and ${code 1} indicating the desired quality level.
	 * 
	 * @throws IOException if a cache file is needed but cannot be created.
	 * @throws IllegalArgumentException if {@code image} or {@code output} or {@code format} is null
	 * 		   or quality is not between 0F and 1F.
	 */
	public static void printTo(BufferedImage image, OutputStream output, String format, int quality) throws IOException {
		ImageTypeSpecifier type = ImageTypeSpecifier.createFromRenderedImage(image);
        Iterator<ImageWriter> iterator = ImageIO.getImageWriters(type, format);
        
        ImageWriter writer = null;
        if (iterator.hasNext()) {
            writer = (ImageWriter) iterator.next();
        } else {
        	return;
        }
        
        IIOImage iioImage = new IIOImage(image, null, null);
        ImageWriteParam param = writer.getDefaultWriteParam();
        if (param.canWriteCompressed()) {
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(quality);
        }
        
        ImageOutputStream outputStream = ImageIO.createImageOutputStream(output);
        writer.setOutput(outputStream);
        writer.write(null, iioImage, param);
	}
	
}
