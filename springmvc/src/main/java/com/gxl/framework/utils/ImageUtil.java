package com.gxl.framework.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图形工具类
 */
public class ImageUtil implements Serializable {

	/**
	 * 图片编码
	 * 
	 * @param imagePath
	 *            d:/111.png
	 * @param imageFormat
	 *            jpg,png,jpeg
	 * @return
	 */
	public static String encodeImage(String imagePath, String imageFormat) {
		File f = new File(imagePath);
		BufferedImage bi;
		try {
			bi = ImageIO.read(f);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, imageFormat, baos);
			byte[] bytes = baos.toByteArray();

			return new sun.misc.BASE64Encoder().encodeBuffer(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 图片解码
	 * 
	 * @param binary
	 *            BASE64加密图片的二进制字符串
	 * @param savePath
	 *            图片的保存位置， d:/ss.png
	 */
	public static void decodeImage(String binary, String savePath) {
		try {

			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();

			//Base64解码  
            byte[] b = decoder.decodeBuffer(binary);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  

			File file = new File(savePath);

			FileOutputStream fos = new FileOutputStream(file);

			fos.write(b);

			fos.flush();

			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	//base64字符串转化成图片  
    public static boolean saveBinaryImage(String photoContent,File file)  
    {   //对字节数组字符串进行Base64解码并生成图片  
        if (photoContent == null) //图像数据为空  
            return false;  
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
        try   
        {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(photoContent);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            OutputStream out = new FileOutputStream(file);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        }   
        catch (Exception e)   
        {  
            return false;  
        }  
    }  
    //将图片压缩
    public static boolean compressImage(File file,String realpath,String file_name,int width,int height) {
    	 FileOutputStream out = null;
		try {
		
		Image src = ImageIO.read(file);
        File saveCompressFile = new File(new File(realpath), file_name);
      
		if (!saveCompressFile.getParentFile().exists()) {
			saveCompressFile.getParentFile().mkdirs();
		}
		BufferedImage tag = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);  

	    tag.getGraphics().drawImage(src.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null); 
        out= new FileOutputStream(saveCompressFile);
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag); 
        jep.setQuality(1, true);  
        encoder.encode(tag, jep);  
     
        out.close();  
        src.flush(); 
        return true;  
		} 
		 catch (Exception e) {
			 return false;  
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
       
	}

}
