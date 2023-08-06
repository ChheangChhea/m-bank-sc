package co.istad.mbank.base;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;


public class BaseJpaRepositoryImpl<T,ID> extends SimpleJpaRepository<T,ID> implements BaseJpaRepository<T,ID>{

    private final EntityManager entityManager;

    public BaseJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }


    @Override
    public void refresh(T entity) {
        entityManager.refresh(entity);

    }
}
