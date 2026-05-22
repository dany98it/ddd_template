package it.dany98.ddd_template.common.event;

import it.dany98.ddd_template.common.domain.DomainEvent;

import java.util.List;

public interface EventStore {
    List<StoredEvent> allStoredEventsBetween(Long lowStorageEventId, Long highStorageEventId);
    List<StoredEvent> allStoredEventsSince(Long storageEventId);
    StoredEvent append(DomainEvent domainEvent);
    void close();
    long countStoredEvents();
}
