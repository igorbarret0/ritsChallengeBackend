package com.backendChallenge.ritsBackendChallenge.repository;

import com.backendChallenge.ritsBackendChallenge.entities.request.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

}
