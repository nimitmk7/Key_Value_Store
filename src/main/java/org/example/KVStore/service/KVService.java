package org.example.KVStore.service;

import java.util.Optional;

import org.example.KVStore.dao.KVDAO;
import org.example.KVStore.exception.KeyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class KVService {

    @Autowired
    private KVDAO kvDAO;
    
    /**
     * Gets the value associated with the given key from the database.
     *
     * @param key The key to look up in the database.
     * @return An Optional containing the value associated with the key if it
     * exists, otherwise an empty Optional.
     */
    public String getValue(String key) throws KeyNotFoundException {
        Optional<String> value = kvDAO.getValue(key);
        if (value.isPresent()) {
            return value.get();
        }
        throw new KeyNotFoundException(key);
    }

    public boolean setValue(String key, String value, String expiryInMinutes) {
        return kvDAO.setValue(key, value, expiryInMinutes);
    }

    public String softDeleteValue(String key) {
        if (!kvDAO.softDeleteValue(key)) 
            throw new KeyNotFoundException(key); 
        return "Key " + key + " soft deleted";
    }

    public boolean hardDeleteValue(String key) {
        return kvDAO.hardDeleteValue(key);
    }

    public int cleanupExpiredKeys() {
        return kvDAO.cleanupExpiredKeys();
    }
}
