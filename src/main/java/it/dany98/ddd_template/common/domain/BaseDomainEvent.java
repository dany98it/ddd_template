package it.dany98.ddd_template.common.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseDomainEvent implements DomainEvent {
    private int eventVersion;
    private LocalDateTime occurredOn;

    public BaseDomainEvent() {
        this.eventVersion = 1;
        this.occurredOn = LocalDateTime.now();
    }

    @Override
    public int eventVersion() {
        return eventVersion;
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }
}
