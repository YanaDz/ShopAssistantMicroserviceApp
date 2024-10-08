package pl.dziadkouskaya.productMicroservice.repository.sql.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import pl.dziadkouskaya.productMicroservice.entity.Firm;
import pl.dziadkouskaya.productMicroservice.entity.Seller;
import pl.dziadkouskaya.productMicroservice.repository.sql.CustomSellerRepository;

import java.util.List;

import static pl.dziadkouskaya.productMicroservice.utils.StringOperations.createSearchingRequest;

public class CustomSellerRepositoryImpl implements CustomSellerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Seller> findSellerByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Seller> cq = cb.createQuery(Seller.class);
        Root<Seller> firm = cq.from(Seller.class);
        Predicate namePredicate = cb.like(cb.lower(firm.get("name")), createSearchingRequest(name));
        cq.where(namePredicate);
        return entityManager.createQuery(cq).getResultList();
    }
}
