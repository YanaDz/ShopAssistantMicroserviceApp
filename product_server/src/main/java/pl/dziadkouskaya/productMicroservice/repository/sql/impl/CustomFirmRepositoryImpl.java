package pl.dziadkouskaya.productMicroservice.repository.sql.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import pl.dziadkouskaya.productMicroservice.entity.Firm;
import pl.dziadkouskaya.productMicroservice.repository.sql.CustomFirmRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static pl.dziadkouskaya.productMicroservice.utils.StringOperations.createSearchingRequest;

public class CustomFirmRepositoryImpl implements CustomFirmRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Firm> findFirmByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Firm> cq = cb.createQuery(Firm.class);
        Root<Firm> firm = cq.from(Firm.class);
        Predicate namePredicate = cb.like(cb.lower(firm.get("name")), createSearchingRequest(name));
        cq.where(namePredicate);
        return entityManager.createQuery(cq).getResultList();
    }
}
