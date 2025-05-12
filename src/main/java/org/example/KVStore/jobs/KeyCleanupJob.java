package org.example.KVStore.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.example.KVStore.service.KVService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class KeyCleanupJob {
    
    @Autowired
    private KVService kvService;

    @Scheduled(cron = "0 */3 * * * *")
    public void run() {
        int deletedCount = kvService.cleanupExpiredKeys();
        System.out.println("🧹 Cleanup Job: Removed " + deletedCount + " expired keys.");
    }
}
