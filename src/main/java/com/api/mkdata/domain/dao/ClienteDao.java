package com.api.mkdata.domain.dao;

import com.api.mkdata.domain.DTO.ClienteFiltroDTO;
import com.api.mkdata.domain.entity.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClienteDao {

    EntityManager em;

    public List<Cliente> findClientsFiltro(ClienteFiltroDTO filtro){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
        Root<Cliente> root = cq.from(Cliente.class);

        List<Predicate> listPredicates = new ArrayList<>();

        if(filtro.getNome() != null){
            listPredicates.add(cb.like(cb.upper(root.get("nome")), "%"+filtro.getNome().toUpperCase()+"%"));
        }
        if(filtro.getAtivo() != null){
            listPredicates.add(cb.equal(root.get("ativo"), filtro.getAtivo()));
        }
        cq.where(cb.and(listPredicates.toArray(new Predicate[listPredicates.size()])));

        return em.createQuery(cq).getResultList();
    }

}
