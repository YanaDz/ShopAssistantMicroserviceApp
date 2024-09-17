package pl.dziadkouskaya.graphql.repository.sql.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import pl.dziadkouskaya.graphql.entity.Product;
import pl.dziadkouskaya.graphql.entity.Purchase;
import pl.dziadkouskaya.graphql.entity.filters.PurchaseFilter;
import pl.dziadkouskaya.graphql.entity.Seller;
import pl.dziadkouskaya.graphql.repository.sql.CustomPurchaseRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static pl.dziadkouskaya.graphql.utils.StringOperations.createSearchingRequest;

public class CustomPurchaseRepositoryImpl implements CustomPurchaseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Purchase> findByPurchaseFilter(PurchaseFilter purchaseFilter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Purchase> query = cb.createQuery(Purchase.class);
        Root<Purchase> purchase = query.from(Purchase.class);
        Join<Purchase, Product> product = purchase.join("product", JoinType.LEFT);
        Join<Purchase, Seller> seller = purchase.join("seller", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        if (nonNull(purchaseFilter.getProduct())) {
            predicates.add(cb.like(cb.lower(product.get("name")), createSearchingRequest(purchaseFilter.getProduct())));
        }

        if (nonNull(purchaseFilter.getSeller())) {
            predicates.add(cb.like(cb.lower(seller.get("name")), createSearchingRequest(purchaseFilter.getSeller())));
        }
        if (nonNull(purchaseFilter.getFirm())) {
            predicates.add(cb.like(cb.lower(product.get("firm")), createSearchingRequest(purchaseFilter.getFirm())));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

}
