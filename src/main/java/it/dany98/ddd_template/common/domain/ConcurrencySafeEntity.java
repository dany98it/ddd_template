package it.dany98.ddd_template.common.domain;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@MappedSuperclass
@Getter
@Setter
public class ConcurrencySafeEntity extends RootEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Version
    private int concurrencyVersion;

    protected ConcurrencySafeEntity() {
        super();
    }
}
