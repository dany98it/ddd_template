package it.dany98.ddd_template.common.infrastructure.persistence.springdata.jpa;

import it.dany98.ddd_template.common.event.StoredEvent;

import java.time.LocalDateTime;
import java.util.List;

public interface StoredEventJpaRepository extends CrudeRepository<StoredEvent, Long> {
    List<StoredEvent> findAllByEventIdLessThanEqualAndEventIdGreaterThanEqual(Long lowStorageEventId, Long highStorageEventId);
    List<StoredEvent> findAllByOccurredOnGreaterThanEqual(LocalDateTime occurredOnIsGreaterThan);
    List<StoredEvent> findAllByEventIdGreaterThanEqual(Long lowStorageEventId);
}
