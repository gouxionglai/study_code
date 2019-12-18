package com.gxl.framework.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxl.framework.exception.BusinessException;
import com.gxl.framework.exception.CustomException;
import com.gxl.framework.model.MessageResult;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

/**
 * 全局异常处理器
 *
 */

@ControllerAdvice
public class GlobalExceptionResolver extends DefaultHandlerExceptionResolver {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);
    

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getServletPath();

        if (logger.isInfoEnabled()) {
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>exception message:" + ex.getMessage());
            ex.printStackTrace();
        }

        // CustomException
        if (ex instanceof CustomException) {
            return sendJSONResult(response, ((CustomException) ex).getCode(), ex.getMessage(), url,request.getParameterMap());

        }

        // BusinessException
        else if (ex instanceof BusinessException) {
            return sendJSONResult(response, ((BusinessException) ex).getCode(), ex.getMessage(), url,request.getParameterMap());

        }

//        if (url.startsWith("/webservice/api") || url.contains("json")) {// api返回异常拦截

        // 运行时异常
        else if (ex instanceof RuntimeException) {
            return sendJSONResult(response, MessagePair.STATUS_CODE_SERVER_RUNTIME, MessagePair.getMessage(MessagePair.STATUS_CODE_SERVER_RUNTIME), url,request.getParameterMap());

        }
        // 空指针异常
        else if (ex instanceof NullPointerException) {
            return sendJSONResult(response, MessagePair.STATUS_CODE_SERVER_NULL_POINT, MessagePair.getMessage(MessagePair.STATUS_CODE_SERVER_NULL_POINT), url,request.getParameterMap());

        }
        // 类型转换异常
        else if (ex instanceof ClassCastException) {
            return sendJSONResult(response, MessagePair.STATUS_CODE_SERVER_CLASS_CAST, MessagePair.getMessage(MessagePair.STATUS_CODE_SERVER_CLASS_CAST), url,request.getParameterMap());

        }
        // IO异常
        else if (ex instanceof IOException) {
            return sendJSONResult(response, MessagePair.STATUS_CODE_SERVER_IO, MessagePair.getMessage(MessagePair.STATUS_CODE_SERVER_IO), url,request.getParameterMap());

        }
        // 未知方法异常
        else if (ex instanceof NoSuchMethodException) {
            return sendJSONResult(response, MessagePair.STATUS_CODE_SERVER_NO_SUCH_METHOD, MessagePair.getMessage(MessagePair.STATUS_CODE_SERVER_NO_SUCH_METHOD), url,request.getParameterMap());

        }
        // 数组越界异常
        else if (ex instanceof IndexOutOfBoundsException) {
            return sendJSONResult(response, MessagePair.STATUS_CODE_SERVER_INDEX_OUT_OF_BOUND, MessagePair.getMessage(MessagePair.STATUS_CODE_SERVER_INDEX_OUT_OF_BOUND), url,request.getParameterMap());

        }

        // 400错误
        else if (ex instanceof HttpMessageNotReadableException) {
            return sendJSONResult(response, MessagePair.STATUS_CODE_HTTP_BAD_REQUEST, MessagePair.getMessage(MessagePair.STATUS_CODE_HTTP_BAD_REQUEST), url,request.getParameterMap());

        }
        // 400错误
        else if (ex instanceof TypeMismatchException) {
            return sendJSONResult(response, MessagePair.STATUS_CODE_HTTP_BAD_REQUEST, MessagePair.getMessage(MessagePair.STATUS_CODE_HTTP_BAD_REQUEST), url,request.getParameterMap());

        }
        // 400错误
        else if (ex instanceof MissingServletRequestParameterException) {
            return sendJSONResult(response, MessagePair.STATUS_CODE_HTTP_BAD_REQUEST, MessagePair.getMessage(MessagePair.STATUS_CODE_HTTP_BAD_REQUEST), url,request.getParameterMap());

        }

        // 404錯誤
        else if (ex instanceof MissingPathVariableException) {
            return sendJSONResult(response, MessagePair.STATUS_CODE_HTTP_NOT_FOUND, MessagePair.getMessage(MessagePair.STATUS_CODE_HTTP_NOT_FOUND), url,request.getParameterMap());

        }
        // 404錯誤
        else if (ex instanceof NoHandlerFoundException) {
            return sendJSONResult(response, MessagePair.STATUS_CODE_HTTP_NOT_FOUND, MessagePair.getMessage(MessagePair.STATUS_CODE_HTTP_NOT_FOUND), url,request.getParameterMap());

        }

        // 405错误
        else if (ex instanceof HttpRequestMethodNotSupportedException) {
            return sendJSONResult(response, MessagePair.STATUS_CODE_HTTP_METHOD_NOT_ALLOWED, MessagePair.getMessage(MessagePair.STATUS_CODE_HTTP_METHOD_NOT_ALLOWED), url,request.getParameterMap());

        }
        // 406错误
        else if (ex instanceof HttpMediaTypeNotAcceptableException) {
            return sendJSONResult(response, MessagePair.STATUS_CODE_HTTP_NOT_ACCEPT, MessagePair.getMessage(MessagePair.STATUS_CODE_HTTP_NOT_ACCEPT), url,request.getParameterMap());

        }
        // 500错误
        else if (ex instanceof ConversionNotSupportedException) {
            // 可以进行其他方法处理，LOG或者什么详细记录，我这里直接返回JSON
            return sendJSONResult(response, MessagePair.STATUS_CODE_HTTP_INTERNAL_SERVER_ERROR, MessagePair.getMessage(MessagePair.STATUS_CODE_HTTP_INTERNAL_SERVER_ERROR), url,request.getParameterMap());

        }
        // 500错误
        else if (ex instanceof HttpMessageNotWritableException) {
            // 可以进行其他方法处理，LOG或者什么详细记录，我这里直接返回JSON
            return sendJSONResult(response, MessagePair.STATUS_CODE_HTTP_INTERNAL_SERVER_ERROR, MessagePair.getMessage(MessagePair.STATUS_CODE_HTTP_INTERNAL_SERVER_ERROR), url,request.getParameterMap());

        } else {
//                super.doResolveException(request, response, handler, ex);
            return sendJSONResult(response, MessagePair.STATUS_CODE_HTTP_INTERNAL_SERVER_ERROR, MessagePair.getMessage(MessagePair.STATUS_CODE_HTTP_INTERNAL_SERVER_ERROR), url,request.getParameterMap());
        }

//        }

//        // 这里调用父类的异常处理方法，实现其他不需要的异常交给SpringMVC处理
//        return super.doResolveException(request, response, handler, ex);
    }

    /**
     * 将异常消息转换成json串
     * @param response
     * @param code
     * @param msg
     * @param url
     * @param data
     * @return
     * @throws IOException
     */
    public String sendJSONResult(HttpServletResponse response, String code, String msg, String url,Object data) throws IOException {

        MessageResult messageResult = new MessageResult(code, msg, url,data);
        JSONObject j = JSONObject.fromObject(messageResult);
        
        if (logger.isInfoEnabled()) {
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>JSON message:" + j);
        }
        return j.toString();
    }
}
