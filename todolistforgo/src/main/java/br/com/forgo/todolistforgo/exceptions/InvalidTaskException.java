package br.com.forgo.todolistforgo.exceptions;

public class InvalidTaskException extends RuntimeException{

    public InvalidTaskException() {
        super();
    }

    public InvalidTaskException(String message) {
        super(message);
    }
}
