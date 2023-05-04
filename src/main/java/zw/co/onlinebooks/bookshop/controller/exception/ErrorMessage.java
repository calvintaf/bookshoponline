package zw.co.onlinebooks.bookshop.controller.exception;

import lombok.Data;

@Data
public class ErrorMessage {
    private String message;
    private int errorCode;
}
