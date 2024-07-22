package com.koval.shop.payload.response;

import java.util.Date;

public class ErrorMessageResponse {

    private Integer statusCode;
    private Date timestamp;
    private String message;
    private String description;

    private ErrorMessageResponse(Builder builder) {
        this.statusCode = builder.statusCode;
        this.timestamp = builder.timestamp;
        this.message = builder.message;
        this.description = builder.description;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ErrorMessageResponse{" +
                "statusCode=" + statusCode +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static class Builder {
        private Integer statusCode;
        private Date timestamp;
        private String message;
        private String description;

        public Builder() {

        }

        public Builder statusCode(Integer statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder timestamp(Date timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public ErrorMessageResponse build() {
            return new ErrorMessageResponse(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}