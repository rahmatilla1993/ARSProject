package com.company.arsproject.repository;

import com.company.arsproject.entity.Airport;
import com.company.arsproject.entity.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface FlightsRepository extends JpaRepository<Flights, Long> {
    List<Flights> findAllByAirport(Airport airport);
}
