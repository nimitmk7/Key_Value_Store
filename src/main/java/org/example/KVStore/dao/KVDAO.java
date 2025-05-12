package org.example.KVStore.dao;

import java.util.Optional;

public interface KVDAO {
    Optional<String> getValue(String key);
    boolean setValue(String key, String value, String expiryInMinutes);
    boolean softDeleteValue(String key);
    boolean hardDeleteValue(String key);
    int cleanupExpiredKeys();
}
