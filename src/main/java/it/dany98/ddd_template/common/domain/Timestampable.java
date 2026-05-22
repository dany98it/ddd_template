package it.dany98.ddd_template.common.domain;

import java.time.LocalDateTime;

public interface Timestampable {
    LocalDateTime getCreatedAt();
    void setCreatedAt(LocalDateTime created);
     LocalDateTime getUpdatedAt();
    void setUpdatedAt(LocalDateTime updated);
}
