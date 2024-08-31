package com.example.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.app.entity.AccountEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.app.entity.AccountEntity;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    Optional<AccountEntity> findByUsername(String Username);

    void deleteById(Integer id);
}



