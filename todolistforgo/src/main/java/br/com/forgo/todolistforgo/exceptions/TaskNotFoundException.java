package br.com.forgo.todolistforgo.exceptions;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException() {
        super();
    }

    public TaskNotFoundException(String message) {
        super(message);
    }
}
