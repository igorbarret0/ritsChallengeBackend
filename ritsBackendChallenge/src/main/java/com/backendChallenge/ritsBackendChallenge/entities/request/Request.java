package com.backendChallenge.ritsBackendChallenge.entities.request;

import com.backendChallenge.ritsBackendChallenge.entities.productRequest.ProductRequest;
import com.backendChallenge.ritsBackendChallenge.entities.client.Client;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "tb_requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    @MapsId
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductRequest> productRequests;

    @CreationTimestamp
    public Instant creationDate;

    @Enumerated(EnumType.STRING)
    public RequestStatus requestStatus;

    public Request() {}

    public Request(Client client) {
        this.client = client;
        this.requestStatus = RequestStatus.PENDENTE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ProductRequest> getProductRequests() {
        return productRequests;
    }

    public void setProductRequests(List<ProductRequest> productRequests) {
        this.productRequests = productRequests;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
