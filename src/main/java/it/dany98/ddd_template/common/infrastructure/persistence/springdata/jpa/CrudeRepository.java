package it.dany98.ddd_template.common.infrastructure.persistence.springdata.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface CrudeRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
