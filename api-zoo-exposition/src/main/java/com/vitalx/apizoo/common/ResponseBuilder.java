package com.vitalx.apizoo.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.util.Assert.notNull;

public class ResponseBuilder<GenericBody> {
    private static final String HEADER_INFORMATION_KEY = "information";
    private HttpStatus httpStatus;
    private String information;
    private GenericBody body;

    public ResponseBuilder<GenericBody> status(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public ResponseBuilder<GenericBody> information(String information) {
        this.information = information;
        return this;
    }

    public ResponseBuilder<GenericBody> body(GenericBody body) {
        this.body = body;
        return this;
    }

    public ResponseEntity<GenericBody> build() {
        notNull(
                httpStatus,
                "The HTTP status of the response is required"
        );
        notNull(
                information,
                "The information of the response is required"
        );

        HttpHeaders headers = new HttpHeaders();
        headers.add(
                HEADER_INFORMATION_KEY,
                information
        );

        return new ResponseEntity<>(
                body,
                headers,
                httpStatus
        );
    }
}
