package org.example.KVStore.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.example.KVStore.exception.KeyNotFoundException;
import org.example.KVStore.service.KVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@RestController
@RequestMapping("/api/kv")
@AllArgsConstructor
public class KVController {
    
    @Autowired
    private KVService kvService;

    /**
     * Retrieves the value associated with the given key.
     *
     * @param key The key whose associated value is to be returned.
     * @return The value associated with the specified key.
     * @throws KeyNotFoundException if the key does not exist in the database.
     */

    @GetMapping("/")
    public String getValue(@RequestBody Map<String, Object> payload) throws KeyNotFoundException {
        String key = (String) payload.get("key");
        return kvService.getValue(key);
    }

    @PutMapping("/")
    public String setValue(@RequestBody Map<String, Object> payload) throws RuntimeException {
        String key = (String) payload.get("key");
        String value = (String) payload.get("value");
        String expiryInMinutes = (String) payload.getOrDefault("expiry", "10");
        System.out.println(key + " " + value);
        if(kvService.setValue(key, value, expiryInMinutes)) {
            return "Value " + value + " set for key " + key;
        } else {
            throw new RuntimeException("Failed to set value for key " + key);
        }
    }

    /**
     * Marks the value associated with the given key as deleted by setting its
     * expiration timestamp to a past value, effectively performing a soft delete.
     *
     * @param key The key of the value to be soft deleted.
     * @return true if the value was successfully soft deleted, false otherwise.
     */

    @DeleteMapping("/")
    public String softDeleteValue(@RequestBody Map<String, Object> payload) {
        String key = (String) payload.get("key");
        return kvService.softDeleteValue(key);
    }

}
