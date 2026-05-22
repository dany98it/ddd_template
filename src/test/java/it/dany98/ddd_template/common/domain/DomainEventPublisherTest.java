package it.dany98.ddd_template.common.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class DomainEventPublisherTest {
    private boolean eventHandled1 = false;
    private boolean eventHandled2 = false;

    @Test
    public void testDomainEventPublisherPublished() {
        DomainEventPublisher.instance().subscribe(new DomainEventSubscriber<BaseDomainEvent>(){
            @Override
            public void handleEvent(BaseDomainEvent domainEvent) {
                eventHandled1 = true;
            }

            @Override
            public Class<BaseDomainEvent> subscribedToEventType() {
                return BaseDomainEvent.class;
            }
        });
        DomainEventPublisher.instance().publish(new BaseDomainEvent());
        assertTrue(eventHandled1);
    }

    @Test
    public void testDomainEventPublisherBlocker() {
        eventHandled1 = false;
        DomainEventPublisher.instance().subscribe(new DomainEventSubscriber<BaseDomainEvent>(){
            @Override
            public void handleEvent(BaseDomainEvent domainEvent) {
                eventHandled1 = true;
                DomainEventPublisher.instance().publish(new TestableDomainEvent());
            }

            @Override
            public Class<BaseDomainEvent> subscribedToEventType() {
                return BaseDomainEvent.class;
            }
        });

        DomainEventPublisher.instance().subscribe(new DomainEventSubscriber<TestableDomainEvent>(){
            @Override
            public void handleEvent(TestableDomainEvent domainEvent) {
                eventHandled2 = true;
            }

            @Override
            public Class<TestableDomainEvent> subscribedToEventType() {
                return TestableDomainEvent.class;
            }
        });

        DomainEventPublisher.instance().publish(new BaseDomainEvent());
        assertTrue(eventHandled1);
        assertFalse(eventHandled2);
    }
}