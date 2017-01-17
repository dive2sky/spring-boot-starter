package net.madroach.spring.exception;

/**
 * Created by sampark on 2016. 12. 17..
 */
public enum ErrorCd {
    INTERNAL_ERROR("INTERNAL_ERROR"),
    INVALID_REQUEST("INVALID_REQUEST"),
    NOT_FOUND(404, "NOT_FOUND");

    private String message;
    private int httpStatus;

    ErrorCd() {
        this.httpStatus = 500;
    }

    ErrorCd(String message) {
        this.message = message;
        this.httpStatus = 500;
    }

    ErrorCd(int httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
    public String getMessage() {
        return this.message;
    }
}
