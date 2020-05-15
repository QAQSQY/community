package top.yuyayao.community.community.exception;

public class CustomizeException extends RuntimeException{

    private String message;
    private Integer code;

    public CustomizeException(ICustomzedErrorCode iCustomzedErrorCode){
        this.message = iCustomzedErrorCode.getMessage();
        this.code = iCustomzedErrorCode.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
