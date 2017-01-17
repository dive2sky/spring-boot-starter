package net.madroach.spring.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by sampark on 2016. 12. 17..
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class DefaultException extends RuntimeException {

    private ErrorCd errorCode;
    private String errorMessage;

    public DefaultException(ErrorCd errorCode) {
        this.errorCode = errorCode;
    }

    public DefaultException(ErrorCd errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
