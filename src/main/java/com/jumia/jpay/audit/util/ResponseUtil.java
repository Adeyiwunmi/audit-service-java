package com.jumia.jpay.audit.util;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * @author adeyi.adebolu
 * created on 05/04/2019
 */
public class ResponseUtil {

    public static Mono<ResponseEntity> LogAndReturnError(Logger logger, String errorMsg, Throwable e) {
        logger.error(errorMsg, e);
        return Mono.just(new ResponseEntity<>(errorMsg + " " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    public static Mono<ResponseEntity> BadRequestResponse(String message) {
        return Mono.just(new ResponseEntity<>(message, HttpStatus.BAD_REQUEST));
    }

    public static Mono<ResponseEntity> OkResponse(Object object) {
        return Mono.just(new ResponseEntity<>(object, HttpStatus.OK));
    }

    public static Mono<ResponseEntity> NoRecordResponse() {
        return Mono.just(new ResponseEntity<>("No Record Found", HttpStatus.NOT_FOUND));
    }

    public static Mono<ResponseEntity> ErrorResponse(String errorMessage) {
        return Mono.just(new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
