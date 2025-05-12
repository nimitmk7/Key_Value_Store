package org.example.KVStore.exception;

public class KeyNotFoundException extends RuntimeException {

    public KeyNotFoundException(String key) {
        super("Key not found: " + key);
    }
    
}
