package Util;

public enum ErrorCode {
    FILE_ERROR(1,"create file error");

    private final int error_code;
    private final String error_message;

    ErrorCode(int error_code, String error_message) {
        this.error_code = error_code;
        this.error_message = error_message;
    }

    public String log(String content){
        return content + error_message + ",error code: " + error_code;
    }


}
