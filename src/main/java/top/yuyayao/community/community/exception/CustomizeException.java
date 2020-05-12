package top.yuyayao.community.community.exception;

public class CustomizeException extends RuntimeException{

    private String message;

    public CustomizeException(ICustomzedErrorCode iCustomzedErrorCode){
        this.message = iCustomzedErrorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
