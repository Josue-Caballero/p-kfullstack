
package com.kruger.kdevfull.exception;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorMessage {

    private Integer code;

    private Date timestamp;

    private String message;

    private List<String> details;

    @Override
    public String toString() {
        return String.format("{\"code\": %d, \"timestamp\": \"%s\", \"message\": \"%s\"}",
            this.code,
            this.timestamp,
            this.message);
    }

}
