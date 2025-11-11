package com.coding.projects.airBnbApp.advice;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> subErrors;

    private ApiError(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.subErrors = builder.subErrors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getSubErrors() {
        return subErrors;
    }


    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    public static class Builder {
        private HttpStatus status;
        private String message;
        private List<String> subErrors;

        public Builder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder subErrors(List<String> subErrors) {
            this.subErrors = subErrors;
            return this;
        }

        public ApiError build() {
            return new ApiError(this);
        }
    }
}
