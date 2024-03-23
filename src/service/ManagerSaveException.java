package service;

import java.io.IOException;

class ManagerSaveException extends RuntimeException {
    public ManagerSaveException(String message, IOException exception) {
        super(message, exception);
    }
}
