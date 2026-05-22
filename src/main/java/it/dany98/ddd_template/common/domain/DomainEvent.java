package it.dany98.ddd_template.common.domain;

import java.time.LocalDateTime;

public interface DomainEvent {
    int eventVersion();
    LocalDateTime occurredOn();
}
