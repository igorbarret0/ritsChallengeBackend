package com.backendChallenge.ritsBackendChallenge.repository;

import com.backendChallenge.ritsBackendChallenge.entities.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);

    Optional<Client> findByPhone(String phone);

}
