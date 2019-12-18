package com.gxl.framework.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件工具类
 */
public class FileUtil {

    public static String getFileExtension(String file_name) {
        if (file_name.lastIndexOf(".") != -1 && file_name.lastIndexOf(".") != 0) {
            return file_name.substring(file_name.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public static String readFileByBytes(String fileName) {
        InputStream in = null;

        String result = "";

        try {

            // check file exist
            File file = new File(fileName);

            if (file.isFile() && file.exists()) {

                // 一次读多个字节
                byte[] tempbytes = new byte[100];
                @SuppressWarnings("unused")
                int byteread = 0;
                in = new FileInputStream(file);

                // 读入多个字节到字节数组中，byteread为一次读入的字节数
                while ((byteread = in.read(tempbytes)) != -1) {
                    // System.out.write(tempbytes, 0, byteread);
                    result += new String(tempbytes);
                }
//                System.out.println(result);
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
        return result;
    }

    /***
     * 错误日志写入
     *
     * @param tag     段标志符
     * @param content 错误内容信息
     * @param logPath 备份文件路径
     * @return
     */
    public static void writeLog(String tag, String content, String logPath) {
        String str = "[" + DateUtils.getCurrentDateYYYYMMDDHHMMSS() + "]" + " [" + tag + "] " + content + System.getProperty("line.separator");

        // 创建日志文件
        String logFile = logPath + "/log.txt";
        File log = new File(logFile);
        try {
            if (!log.exists()) {
                log.getParentFile().mkdirs();
                log.createNewFile();
            }

            // 构造一个FileWriter
            FileWriter writer = new FileWriter(logFile, true);
            writer.write(str);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过Servlet 方式下载文件
     *
     * @param response
     * @param filePath，文件夹地址
     * @param fileName 文件名，带后缀
     * @param aliasName 别名，下载显示、保存用，带文件后缀
     * @throws Exception
     */
    public static void downloadFileForServlet(HttpServletResponse response, String filePath, String fileName,String aliasName) throws Exception {
        // 1、设置response 响应头
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
       
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(StringUtil.isEmpty(aliasName)?fileName:aliasName, "UTF-8"));

        File file = new File(filePath, fileName);
        
        response.setContentLengthLong(file.length());
        // 2、 读取文件--输入流
        InputStream input = new FileInputStream(file);
        // 3、 写出文件--输出流
        OutputStream out = response.getOutputStream();
        byte[] buff = new byte[2048];
        int index = 0;
        // 4、执行 写出操作
        while ((index = input.read(buff)) != -1) {
            out.write(buff, 0, index);
            out.flush();
        }
        out.close();
        input.close();

    }

    /**
     * 通过SPRING API 方式下载文件
     *
     * @param filePath，文件夹地址
     * @param fileName 文件名，带后缀
     * @param aliasName 别名，下载显示、保存用，带文件后缀
     * @return
     * @throws Exception
     */
    public static ResponseEntity<byte[]> downloadFileForSpringAPI(String filePath, String fileName,String aliasName) throws Exception {
        try {
            File file = new File(filePath, fileName);
            HttpHeaders headers = new HttpHeaders();
            String fileName_utf = new String((StringUtil.isEmpty(aliasName) ? fileName : aliasName).getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
            headers.setContentDispositionFormData("attachment", fileName_utf);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.set("Content-Range", "bytes 0-" + Long.toString(file.length() - 1) + "/" + Long.toString(file.length()));
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);

        } catch (Exception e) {
            HttpHeaders headers = new HttpHeaders();
            String fileName_utf = new String((StringUtil.isEmpty(aliasName) ? fileName : aliasName).getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
            headers.setContentDispositionFormData("attachment", fileName_utf);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.set("Content-Range", "bytes 0-0/0");
            return new ResponseEntity<byte[]>(headers, HttpStatus.NOT_FOUND);
        }
    }
        
//        /**
//         * 通过SPRING API 方式下载文件
//         *
//         * @param filePath，文件夹地址
//         * @param fileName 文件名，带后缀
//         * @param aliasName 别名，下载显示、保存用，带文件后缀
//         * @return
//         * @throws Exception
//         */
//        public static ResponseEntity<byte[]> downloadFileForSpringAPIQR(String filePath, String fileName,String aliasName) throws Exception {
//            try {
//                File file = new File(filePath, fileName);
//                HttpHeaders headers = new HttpHeaders();
//                String fileName_utf = new String((StringUtil.isEmpty(aliasName) ? fileName : aliasName).getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
//                headers.setContentDispositionFormData("attachment", fileName_utf);
//                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
////                headers.set("Content-Range", "bytes 0-" + Long.toString(file.length() - 1) + "/" + Long.toString(file.length()));
//                return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
//
//            } catch (Exception e) {
//                HttpHeaders headers = new HttpHeaders();
//                String fileName_utf = new String((StringUtil.isEmpty(aliasName) ? fileName : aliasName).getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
//                headers.setContentDispositionFormData("attachment", fileName_utf);
//                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
////                headers.set("Content-Range", "bytes 0-0/0");
//                return new ResponseEntity<byte[]>(headers, HttpStatus.NOT_FOUND);
//            }
//
//        }



    /**
     * 压缩文件
     *
     * @param parms            List<Map<filePath, fileName>>
     * @param byteOutPutStream
     * @return
     * @throws Exception
     */
    public static void zipFiles(List<Map<String, String>> parms, ByteArrayOutputStream byteOutPutStream) throws Exception {

        ZipOutputStream zipOut = new ZipOutputStream(byteOutPutStream);

        FileInputStream inputStream = null;
        try {
            for (Map<String, String> value : parms) {
                // 文件路径
                inputStream = new FileInputStream(new File(value.get("filePath")));
                // 使用指定名称创建新的 ZIP 条目 （通俗点就是文件名）
                ZipEntry zipEntry = new ZipEntry(value.get("fileName"));
                // 开始写入新的 ZIP 文件条目并将流定位到条目数据的开始处
                zipOut.putNextEntry(zipEntry);

                byte[] b = new byte[1024];

                int length = 0;

                while ((length = inputStream.read(b)) != -1) {

                    zipOut.write(b, 0, length);

                }
                inputStream.close();
                zipOut.closeEntry();
            }
            zipOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
