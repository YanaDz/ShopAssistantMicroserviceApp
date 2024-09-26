package pl.dziadkouskaya.productMicroservice.repository.sql.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import pl.dziadkouskaya.productMicroservice.entity.Firm;
import pl.dziadkouskaya.productMicroservice.entity.Product;
import pl.dziadkouskaya.productMicroservice.entity.enums.ProductType;
import pl.dziadkouskaya.productMicroservice.repository.sql.CustomProductRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static pl.dziadkouskaya.productMicroservice.utils.StringOperations.createSearchingRequest;

public class CustomProductRepositoryImpl implements CustomProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findByFilters(String name, String firm, Integer type, String productVersion) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> productRoot = query.from(Product.class);
        Join<Product, Firm> firmJoin = productRoot.join("firm");

        List<Predicate> predicates = new ArrayList<>();

        if (nonNull(name)) {
            predicates.add(cb.like(cb.lower(productRoot.get("name")), createSearchingRequest(name)));
        }

        if (nonNull(firm)) {
            predicates.add(cb.like(cb.lower(firmJoin.get("name")), createSearchingRequest(firm)));
        }

        if (nonNull(type)) {
            predicates.add(cb.equal(productRoot.get("productType"), type));
        }

        if (nonNull(productVersion)) {
            predicates.add(cb.like(cb.lower(productRoot.get("productVersion")), createSearchingRequest(productVersion)));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
