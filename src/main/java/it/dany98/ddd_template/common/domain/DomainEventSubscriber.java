package it.dany98.ddd_template.common.domain;

public interface DomainEventSubscriber<T> {
    void handleEvent(final T domainEvent);
    Class<T> subscribedToEventType();
}
