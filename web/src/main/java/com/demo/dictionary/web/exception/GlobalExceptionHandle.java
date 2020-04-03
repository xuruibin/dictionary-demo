package com.demo.dictionary.web.exception;

import com.demo.dictionary.common.enums.StatusCode;
import com.demo.dictionary.common.exception.BusinessException;
import com.demo.dictionary.common.exception.ValidException;
import com.demo.dictionary.common.response.ResponseData;
import com.demo.dictionary.common.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.Set;

/**
 * @author Rio
 * @date 2018/8/1
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> handleBadRequest(String errorMsg, HttpHeaders headers, HttpStatus status) {
        ResponseData<Void> resData = new ResponseData<>();
        resData.setStatusCode(HttpStatus.BAD_REQUEST.value());
        resData.setMessage(errorMsg);
        return new ResponseEntity<>(resData, headers, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMsg = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        log.error(errorMsg, ex);
        return handleBadRequest(errorMsg, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMsg = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        log.error(errorMsg, ex);
        return handleBadRequest(errorMsg, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMsg = ex.getMessage();
        log.error(errorMsg, ex);
        return handleBadRequest(errorMsg, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMsg = ex.getMessage();
        log.error(errorMsg, ex);
        return handleBadRequest(errorMsg, headers, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<Object> handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException e) {
        logUrlParams(request, e.getMessage());
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            strBuilder.append(violation.getMessage()).append("\n");
        }
        log.error(strBuilder.toString(), e);
        return handleBadRequest(strBuilder.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException ex) {
        logUrlParams(request, ex.getMessage());
        ex.printStackTrace();
        return handleBadRequest(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidException.class)
    @ResponseBody
    public ResponseEntity<Object> handleValidException(HttpServletRequest request, ValidException ex) {
        logUrlParams(request, ex.getMessage());
        return handleBadRequest(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseData<Void> handleBusinessException(HttpServletRequest request, BusinessException ex) {
        logUrlParams(request, ex.getMessage());
        return new ResponseData<>(ex.getStatusCode(), ex.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<Object> processException(Throwable ex, HttpServletRequest request) {
        logUrlParams(request, ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(new ResponseData<>(StatusCode.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logUrlParams(HttpServletRequest request, String errorMsg) {
        String url = "接口：" + request.getRequestURI();
        String params = "参数：" + JacksonUtil.toJsonString(request.getParameterMap());
        log.error(url + "，" + params + "，错误信息：" + errorMsg);
    }

}
