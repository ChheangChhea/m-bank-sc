package com.example.mbank.base;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;


public class BaseJpaRepositoryImp<T,ID> extends SimpleJpaRepository<T,ID> implements BaseJpaRepository<T,ID>{

    private final EntityManager entityManager;

    public BaseJpaRepositoryImp(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }


    @Override
    public void refresh(T entity) {

    }
}
