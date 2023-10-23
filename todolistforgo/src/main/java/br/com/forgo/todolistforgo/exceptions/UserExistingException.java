package br.com.forgo.todolistforgo.exceptions;

public class UserExistingException extends RuntimeException{

    public UserExistingException() {
        super();
    }

    public UserExistingException(String message) {
        super(message);
    }
}
