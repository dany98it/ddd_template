package it.dany98.ddd_template.common.event;

import it.dany98.ddd_template.common.domain.DomainEvent;
import it.dany98.ddd_template.common.serializer.AbstractSerializer;

public class EventSerializer extends AbstractSerializer {
    private static EventSerializer eventSerializer;

    public static synchronized EventSerializer instance() {
        if (EventSerializer.eventSerializer == null) {
            EventSerializer.eventSerializer = new EventSerializer();
        }
        return EventSerializer.eventSerializer;
    }

    public EventSerializer(boolean isCompact) {
        this(false, isCompact);
    }

    public EventSerializer(boolean isPretty, boolean isCompact) {
        super(isPretty, isCompact);
    }

    public String serialize(DomainEvent domainEvent) {
        return this.gson().toJson(domainEvent);
    }

    public <T extends DomainEvent> T deserialize(String serialization, Class<T> type) {
        return this.gson().fromJson(serialization, type);
    }

    private EventSerializer() {
        this(false, false);
    }
}
