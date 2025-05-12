package org.example.KVStore.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class KVDAOImpl implements KVDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<String> getValue(String key) {
        String query = "SELECT `value` FROM STORE WHERE `key` = :key AND expiration_ts > NOW()";

        try {
            String result = (String) entityManager
                    .createNativeQuery(query)
                    .setParameter("key", key)
                    .getSingleResult();

            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public boolean setValue(String key, String value, String expiryInMinutes) {
        String sql = "INSERT INTO STORE (`key`, `value`, `EXPIRATION_TS`) VALUES (:key, :value, NOW() + INTERVAL :expiryInMinutes MINUTE) "
                +
                "ON DUPLICATE KEY UPDATE `value` = :value, `EXPIRATION_TS` = NOW() + INTERVAL 10 MINUTE";

        return entityManager.createNativeQuery(sql)
                .setParameter("key", key)
                .setParameter("value", value)
                .setParameter("expiryInMinutes", expiryInMinutes)
                .executeUpdate() > 0;
    }

    @Override
    public boolean softDeleteValue(String key) {
        String query = "UPDATE STORE SET expiration_ts = '1970-01-01 00:00:01' WHERE `key` = :key  AND expiration_ts > NOW()";
        return entityManager.createNativeQuery(query).setParameter("key", key).executeUpdate() == 1;
    }

    @Override
    public boolean hardDeleteValue(String key) {
        String query = "DELETE FROM STORE WHERE `key` = :key;";
        return entityManager.createNativeQuery(query).setParameter("key", key).executeUpdate() == 1;
    }

    @Override
    public int cleanupExpiredKeys() {
        String query = "DELETE FROM STORE WHERE expiration_ts < NOW();";
        return entityManager.createNativeQuery(query).executeUpdate();
    }

}
