package com.api.mkdata.domain.entity.repository;

import com.api.mkdata.domain.entity.Cliente;
import com.api.mkdata.domain.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato,Long> {

    @Modifying
    @Query("delete FROM Contato c WHERE c.cliId = :clientId")
    void deleteByClient(@Param("clientId") Long clientId);
}
