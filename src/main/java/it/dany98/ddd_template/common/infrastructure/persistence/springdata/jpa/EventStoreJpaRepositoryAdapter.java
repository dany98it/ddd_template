package it.dany98.ddd_template.common.infrastructure.persistence.springdata.jpa;

import it.dany98.ddd_template.common.domain.DomainEvent;
import it.dany98.ddd_template.common.event.EventSerializer;
import it.dany98.ddd_template.common.event.EventStore;
import it.dany98.ddd_template.common.event.StoredEvent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventStoreJpaRepositoryAdapter implements EventStore {
    StoredEventJpaRepository jpaRepository;

    public EventStoreJpaRepositoryAdapter(StoredEventJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    @Override
    public List<StoredEvent> allStoredEventsBetween(Long lowStorageEventId, Long highStorageEventId) {
        return jpaRepository.findAllByEventIdLessThanEqualAndEventIdGreaterThanEqual(lowStorageEventId, highStorageEventId);
    }

    @Override
    public List<StoredEvent> allStoredEventsSince(Long storageEventId) {
        return jpaRepository.findAllByEventIdGreaterThanEqual(storageEventId);
    }

    @Override
    public StoredEvent append(DomainEvent domainEvent) {
        String eventSerialization = EventSerializer.instance().serialize(domainEvent);
        StoredEvent storedEvent = new StoredEvent(
                domainEvent.getClass().getName(),
                domainEvent.occurredOn(),
                eventSerialization);
        jpaRepository.save(storedEvent);
        return storedEvent;
    }

    @Override
    public void close() {

    }

    @Override
    public long countStoredEvents() {
        return jpaRepository.count();
    }
}
