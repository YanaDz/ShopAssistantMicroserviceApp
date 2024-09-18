package pl.dziadkouskaya.search_server.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import pl.dziadkouskaya.search_server.entity.Seller;
import pl.dziadkouskaya.search_server.entity.enums.Location;
import pl.dziadkouskaya.search_server.entity.params.SellerParam;
import pl.dziadkouskaya.search_server.repository.CustomSellerRepository;

import java.util.List;

public class SellerRepositoryImpl implements CustomSellerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Seller> getSellers(Location location) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Seller> cq = cb.createQuery(Seller.class);
        Root<Seller> root = cq.from(Seller.class);
        Predicate namePredicate = cb.equal(root.get("location"), location);
        cq.where(namePredicate);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Seller> getSellersByNameOrSearchUrl(String name, String searchUrl) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Seller> cq = cb.createQuery(Seller.class);
        Root<Seller> root = cq.from(Seller.class);
        Predicate namePredicate = cb.equal(root.get("name"), name);
        Predicate searchUrlPredicate = cb.equal(root.get("searchUrl"), searchUrl);
        Predicate finalPredicate = cb.or(namePredicate, searchUrlPredicate);
        cq.where(finalPredicate);
        return entityManager.createQuery(cq).getResultList();
    }
}
