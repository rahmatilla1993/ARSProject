package com.company.arsproject.repository;

import com.company.arsproject.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface AirportRepository extends JpaRepository<Airport, Long> {

    List<Airport> findAllByAddress_City(String city);
}
