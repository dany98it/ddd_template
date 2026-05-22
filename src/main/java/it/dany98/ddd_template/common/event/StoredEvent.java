package it.dany98.ddd_template.common.event;

import it.dany98.ddd_template.common.AssertionConcern;
import it.dany98.ddd_template.common.domain.DomainEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class StoredEvent extends AssertionConcern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    private String eventBody;
    private LocalDateTime occurredOn;
    private String typeName;

    public StoredEvent(String typeName, LocalDateTime occurredOn, String eventBody) {
        this();

        this.setEventBody(eventBody);
        this.setOccurredOn(occurredOn);
        this.setTypeName(typeName);
    }

    public StoredEvent(String typeName, LocalDateTime occurredOn, String eventBody, Long eventId) {
        this(typeName, occurredOn, eventBody);

        this.setEventId(eventId);
    }

    public String eventBody() {
        return this.eventBody;
    }

    public long eventId() {
        return this.eventId;
    }

    public LocalDateTime occurredOn() {
        return this.occurredOn;
    }

    @SuppressWarnings("unchecked")
    public <T extends DomainEvent> T toDomainEvent() {
        Class<T> domainEventClass = null;

        try {
            domainEventClass = (Class<T>) Class.forName(this.typeName);
        } catch (Exception e) {
            throw new IllegalStateException("Class load error, because: " + e.getMessage());
        }

        return EventSerializer.instance().deserialize(this.eventBody, domainEventClass);
    }

    @Override
    public boolean equals(Object obj) {
        boolean equalObj = false;

        if (obj != null && this.getClass() == obj.getClass()) {
            StoredEvent typedObject = (StoredEvent) obj;
            equalObj =
                    this.eventId() == typedObject.eventId();
        }

        return equalObj;
    }

    @Override
    public int hashCode() {
        return (1237 * 233) + (int) this.eventId();
    }

    @Override
    public String toString() {
        return "StoredEvent [eventId=" + eventId + ", eventBody=" + eventBody + ", occurredOn=" + occurredOn
                + ", typeName=" + typeName + "]";
    }

    protected StoredEvent() {
        super();

        this.setEventId(-1L);
    }

    protected void setEventBody(String eventBody) {
        this.assertArgumentNotEmpty(eventBody, "The event body is required.");
        this.assertArgumentLength(eventBody, 1, 65000, "The event body must be 65000 characters or less.");
        this.eventBody = eventBody;
    }

    protected void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    protected void setOccurredOn(LocalDateTime occurredOn) {
        this.occurredOn = occurredOn;
    }

    protected void setTypeName(String typeName) {
        this.assertArgumentNotEmpty(typeName, "The event type name is required.");
        this.assertArgumentLength(typeName, 1, 100, "The event type name must be 100 characters or less.");

        this.typeName = typeName;
    }
}
