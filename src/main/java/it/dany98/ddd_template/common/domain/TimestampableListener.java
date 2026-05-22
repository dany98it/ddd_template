package it.dany98.ddd_template.common.domain;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.Clock;
import java.time.LocalDateTime;

public class TimestampableListener {
    // for testing
    private static final Clock clock = Clock.systemDefaultZone();

    @PrePersist
    public void generateCreatedAt(Timestampable object) {
        object.setCreatedAt(LocalDateTime.now(clock));
    }

     @PreUpdate
    public void generateUpdatedAt(Timestampable object) {
         object.setUpdatedAt(LocalDateTime.now(clock));
     }
}
