package pl.dziadkouskaya.graphql.repository.sql.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import pl.dziadkouskaya.graphql.entity.Product;
import pl.dziadkouskaya.graphql.repository.sql.CustomProductRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static pl.dziadkouskaya.graphql.utils.StringOperations.createSearchingRequest;

public class CustomProductRepositoryImpl implements CustomProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findByFilters(String name, String firm, Integer type, String productVersion) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (nonNull(name)) {
            predicates.add(cb.like(cb.lower(product.get("name")), createSearchingRequest(name)));
        }

        if (nonNull(firm)) {
            predicates.add(cb.like(cb.lower(product.get("firm")), createSearchingRequest(firm)));
        }

        if (nonNull(type)) {
            predicates.add(cb.equal(product.get("productType"), type));
        }

        if (nonNull(productVersion)) {
            predicates.add(cb.like(cb.lower(product.get("productVersion")), createSearchingRequest(productVersion)));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
