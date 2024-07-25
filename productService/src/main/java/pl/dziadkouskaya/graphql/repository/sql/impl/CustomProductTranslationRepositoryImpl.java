package pl.dziadkouskaya.graphql.repository.sql.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import pl.dziadkouskaya.graphql.entity.ProductTranslation;
import pl.dziadkouskaya.graphql.entity.enums.Location;
import pl.dziadkouskaya.graphql.entity.enums.TranslationType;
import pl.dziadkouskaya.graphql.repository.sql.CustomProductTranslationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;
import static pl.dziadkouskaya.graphql.utils.StringOperations.createSearchingRequest;

public class CustomProductTranslationRepositoryImpl implements CustomProductTranslationRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductTranslation> getTranslationByStrictName(String name, Location location) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductTranslation> cq = cb.createQuery(ProductTranslation.class);
        Root<ProductTranslation> root = cq.from(ProductTranslation.class);

        List<Predicate> predicates = new ArrayList<>();
        if (nonNull(name)) {
            predicates.add(cb.like(cb.lower(root.get("locationTranslation")), name.toLowerCase()));
        }

        if (nonNull(location)) {
            predicates.add(cb.equal(root.get("location"), location));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<ProductTranslation> getTranslationByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductTranslation> cq = cb.createQuery(ProductTranslation.class);
        Root<ProductTranslation> root = cq.from(ProductTranslation.class);
        Predicate namePredicate = cb.like(cb.lower(root.get("locationTranslation")), createSearchingRequest(name));
        cq.where(namePredicate);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<ProductTranslation> getAdditionalByMain(UUID id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductTranslation> cq = cb.createQuery(ProductTranslation.class);
        Root<ProductTranslation> root = cq.from(ProductTranslation.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("translationType"), TranslationType.ADDITIONAL));
        predicates.add(cb.equal(root.get("basicTranslationId"), id));

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }
}
