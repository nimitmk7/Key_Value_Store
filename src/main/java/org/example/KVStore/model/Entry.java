package org.example.KVStore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;

import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="STORE")
@Data
@AllArgsConstructor
public class Entry {

    @Id
    private String key;
    private String value;
    private Timestamp expirationTs;
}
