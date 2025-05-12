package org.example.KVStore;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.SpringApplication;

/**
 * Hello world!
 */

@SpringBootApplication
@EnableScheduling
public class KVStore {
    public static void main( String[] args ) {
        SpringApplication.run(KVStore.class, args);
    }
}
