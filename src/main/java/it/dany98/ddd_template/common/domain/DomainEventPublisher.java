package it.dany98.ddd_template.common.domain;

import java.util.HashMap;
import java.util.Map;

public class DomainEventPublisher {
    private static final ThreadLocal<DomainEventPublisher> instance = ThreadLocal.withInitial(DomainEventPublisher::new);

    Map<Class, DomainEventSubscriber> subscribers;

    private boolean publishing = false;

    public static DomainEventPublisher instance() {
        return instance.get();
    }

    public void publish(final DomainEvent domainEvent) {
        if (this.isNotPublishing() && hasSubscribers()) {
            this.setPublishing(true);

            try {
                Class<?> eventType = domainEvent.getClass();

                this.subscribers().values().stream()
                        .filter(subscriber -> {
                            Class<?> subscribedType = subscriber.subscribedToEventType();
                            return subscribedType == eventType || subscribedType == DomainEvent.class;
                        })
                        .forEach(subscriber -> subscriber.handleEvent(domainEvent));
            } finally {
                this.setPublishing(false);
            }
        }
    }

    public void subscribe(DomainEventSubscriber subscriber) {
        this.ensureSubscribers();

        if (this.isNotPublishing() && !this.subscribers().containsKey(subscriber.getClass())) {
            this.subscribers.put(subscriber.getClass(), subscriber);
        }
    }

    private boolean isNotPublishing() {
        return !this.publishing;
    }

    private void setPublishing(boolean publishing) {
        this.publishing = publishing;
    }

    private void ensureSubscribers() {
        if (!this.hasSubscribers()) {
            this.setSubscribers(new HashMap<>());
        }
    }

    private void setSubscribers(Map<Class, DomainEventSubscriber> subscribers) {
        this.subscribers = subscribers;
    }

    private Map<Class, DomainEventSubscriber> subscribers() {
        return this.subscribers;
    }

    private boolean hasSubscribers() {
        return this.subscribers() != null;
    }


}
