package pl.dziadkouskaya.graphql.repository.sql.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import pl.dziadkouskaya.graphql.entity.Firm;
import pl.dziadkouskaya.graphql.entity.Product;
import pl.dziadkouskaya.graphql.repository.sql.CustomFirmRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static pl.dziadkouskaya.graphql.utils.StringOperations.createSearchingRequest;

public class CustomFirmRepositiryImpl implements CustomFirmRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Firm> findFirmByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Firm> query = cb.createQuery(Firm.class);
        Root<Firm> firm = query.from(Firm.class);

        List<Predicate> predicates = new ArrayList<>();

        if (nonNull(name)) {
            predicates.add(cb.like(cb.lower(firm.get("name")), createSearchingRequest(name)));
        }
        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
