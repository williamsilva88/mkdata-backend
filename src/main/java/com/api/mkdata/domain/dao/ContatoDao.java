package com.api.mkdata.domain.dao;

import com.api.mkdata.domain.DTO.ClienteFiltroDTO;
import com.api.mkdata.domain.entity.Cliente;
import com.api.mkdata.domain.entity.Contato;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContatoDao {

    @PersistenceContext(name = "JPA_DEMO", type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    public List<Contato> findClientsByClient(Long clientId){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contato> cq = cb.createQuery(Contato.class);
        Root<Contato> root = cq.from(Contato.class);

        List<Predicate> listPredicates = new ArrayList<>();
        listPredicates.add(cb.equal(root.get("cliId"), clientId));
        cq.where(cb.and(listPredicates.toArray(new Predicate[listPredicates.size()])));

        return em.createQuery(cq).getResultList();
    }

}
