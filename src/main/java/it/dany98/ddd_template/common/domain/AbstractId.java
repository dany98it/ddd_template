package it.dany98.ddd_template.common.domain;

import it.dany98.ddd_template.common.AssertionConcern;
import jakarta.persistence.MappedSuperclass;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractId extends AssertionConcern implements Identity, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;

    @Override
    public UUID id() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equalObj = false;

        if (obj != null && this.getClass() == obj.getClass()) {
            AbstractId typedObject = (AbstractId) obj;
            equalObj =
                    this.id().equals(typedObject.id());
        }

        return equalObj;
    }

    @Override
    public int hashCode() {
        return (this.hashOddValue() * this.hashPrimeValue()) + this.id().hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id=" + id + "]";
    }

    protected AbstractId(UUID id) {
        this();
        this.setId(id);
    }

    protected AbstractId() {
        super();
    }

    protected void validateId(UUID id) {
        // implemented by subclasses for validation.
        // throws a runtime exception if invalid.
    }

    protected abstract int hashPrimeValue();

    protected abstract int hashOddValue();

    protected void setId(UUID id) {
        this.assertArgumentNotNull(id, "id cannot be null");

        this.validateId(id);

        this.id = id;
    }
}
