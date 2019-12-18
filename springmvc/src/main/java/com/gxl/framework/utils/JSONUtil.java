package com.gxl.framework.utils;

import java.io.IOException;
import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxl.framework.handler.MessagePair;
import com.gxl.framework.model.MessageResult;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON工具类
 */
public class JSONUtil implements Serializable {


    private static final Logger logger = LoggerFactory.getLogger(JSONUtil.class);

    private static final String PARAM_REQ = "req";
    
    private static final boolean _ENCRYPT_FLG = new Boolean(ResourceBundle.getBundle(WebConstants.PROPERTIES_FILE_NAME).getString(WebConstants.ENCRYPT_FLG));
    
 // 获取公钥
    private static final  PublicKey _RSA_PUBLIC_KEY = RSAUtil.keyStrToPublicKey(FileUtil.readFileByBytes(ResourceBundle.getBundle(WebConstants.PROPERTIES_FILE_NAME).getString(WebConstants.RSA_PUBLIC_KEY_PATH)));
    // 获取私钥
    private static final PrivateKey _RSA_PRIVATE_KEY = RSAUtil.keyStrToPrivateKey(FileUtil.readFileByBytes(ResourceBundle.getBundle(WebConstants.PROPERTIES_FILE_NAME).getString(WebConstants.RSA_PRIVATE_KEY_PATH)));

    protected HttpServletRequest request;

    protected HttpServletResponse response;
    private String _json_str;

    static {

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {

            @Override
            public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
                if (value instanceof Date) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return sdf.format((Date) value);
                }
                return value;
            }

            @Override
            public Object processArrayValue(Object value, JsonConfig jsonConfig) {

                return value;
            }
        });

    }

    private JSONUtil() {

    }

    public JSONUtil(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    
    /**
     * 数据
     * @return
     */
    public boolean preHandleRequestData() {
        //获取数据
        _json_str=getRequestData();
        // 数据解密
        if(_ENCRYPT_FLG) {
            try {
                _json_str=RSAUtil.decryptedToStrByPrivate(_json_str,_RSA_PRIVATE_KEY);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                responseClientEncryptError();
                return false;
            } 
        }
        return true;
    }

    /**
     * 获得请求的json消息内容
     * 
     * @return
     */
    public String getRequestData() {
        String data = request.getParameter(PARAM_REQ);
        if (logger.isInfoEnabled()) {
            logger.info(data);
        }
        return data;

//	    BufferedReader br =null;
//        StringBuffer requestData = new StringBuffer("");
//        
//        try{
//            
//            br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
//           
//            String temp;
//            while ((temp = br.readLine()) != null) {
//                requestData.append(temp);
//            }
//            br.close();
//        } catch (Exception e) {
//
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//
//                }
//
//            }
//
//        }
//        
//        if (logger.isInfoEnabled()) {
//          logger.info(requestData.toString());
//      }
//        return requestData.toString();

    }
    
    /**
     * 数据加密错误
     */
    public void responseClientEncryptError() {
        String responseJsonData = "";
        try {
            responseJsonData = generateErrorJSONData(MessagePair.STATUS_CODE_JSON_DECRYPT_ERROR);
            outJsonString(responseJsonData);

        } catch (IOException e) {
            logger.error("can not get the printwriter stream。 response json data:" + responseJsonData);
        }

    }

    /**
     * 数据格式错误
     */
    public void responseClientFormatError() {
        String responseJsonData = "";
        try {
            responseJsonData = generateErrorJSONData(MessagePair.STATUS_CODE_JSON_FORMAT_ERROR);
            outJsonString(responseJsonData);

        } catch (IOException e) {
            logger.error("can not get the printwriter stream。 response json data:" + responseJsonData);
        }

    }

    /**
     * 参数类型错误
     */
    public void responseClientParamError() {
        String responseJsonData = "";
        try {
            responseJsonData = generateErrorJSONData(MessagePair.STATUS_CODE_JSON_PARAM_ERROR);
            outJsonString(responseJsonData);

        } catch (IOException e) {
            logger.error("can not get the printwriter stream。 response json data:" + responseJsonData);
        }

    }
    
    public void responseClientCustomError(String messagePairCode) {
        String responseJsonData = "";
        try {

            responseJsonData = generateErrorJSONData(messagePairCode);
            outJsonString(responseJsonData);
        } catch (IOException e) {
            logger.error("can not get the printwriter stream。 response json data:" + responseJsonData);
        }

    }

    /**
     * 返回会话过期数据
     * 
     */
    public void responseClientSessionTimeout() {
        String responseJsonData = "";
        try {

            responseJsonData = generateErrorJSONData(MessagePair.STATUS_CODE_JSON_SESSION_TIMEOUT);
            outJsonString(responseJsonData);
        } catch (IOException e) {
            logger.error("can not get the printwriter stream。 response json data:" + responseJsonData);
        }

    }

    /**
     * 系统异常
     */
    public void responseClientSystemException() {
        String responseJsonData = "";
        try {
            responseJsonData = generateErrorJSONData(MessagePair.STATUS_CODE_JSON_SYSTEM_EXCEPTION);
            outJsonString(responseJsonData);

        } catch (IOException e) {
            logger.error("can not get the printwriter stream。 response json data:" + responseJsonData);
        }

    }

    /**
     * 返回手机客户端数据
     * 
     * @param responseJsonData
     */
    public void responseClientSuccess(String responseJsonData) {

        try {
            outJsonString(responseJsonData);
        } catch (IOException e) {
            logger.error("can not get the printwriter stream。 response json data:" + responseJsonData);
        }

    }

    private String generateErrorJSONData(String messagePairCode) {
        MessageResult messageResult = new MessageResult(messagePairCode, MessagePair.getMessage(messagePairCode));

        JSONObject jsonObject = JSONObject.fromObject(messageResult);
        return jsonObject.toString();
    }

    private String generateErrorJSONData(String code, String string) {
        MessageResult messageResult = new MessageResult(code, string);

        JSONObject jsonObject = JSONObject.fromObject(messageResult);
        return jsonObject.toString();
    }

    /***
     * 返回json串
     * 
     * @param json json参数
     * @throws IOException
     */
    private void outJsonString(String json) throws IOException {

        if(_ENCRYPT_FLG) {
            try {
                json = RSAUtil.encryptDataByPrivateKey(json.getBytes(),_RSA_PRIVATE_KEY);
            } catch (Exception e) {
                logger.error("返回结果数据加密异常:" + e);
                json=generateErrorJSONData(MessagePair.STATUS_CODE_JSON_SYSTEM_EXCEPTION);
            }
            
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("pragma", "no-cache");
        response.setDateHeader("expires", 0L);
        response.getWriter().write(json);
        response.getWriter().flush();
        response.getWriter().close();
    }

    public static void main(String[] args) throws Exception {
        JSONUtil util = new JSONUtil();
        System.out.println(util.generateErrorJSONData("1", "xxxxxxxxx"));

    }

    public String get_json_str() {
        return _json_str;
    }


    

}
