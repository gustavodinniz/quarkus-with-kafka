package br.com.mining.company.exception;

public class CSVException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CSVException(String message) {
        super(message);
    }

    public CSVException(String message, Throwable cause) {
        super(message, cause);
    }
}
