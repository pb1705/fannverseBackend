package com.fannverse.MatchFetcher.Models;

public class MatchPollerException extends RuntimeException {
    public MatchPollerException(String message) {
        super(message);
    }

    public MatchPollerException(String message, Throwable cause) {
        super(message, cause);
    }
}