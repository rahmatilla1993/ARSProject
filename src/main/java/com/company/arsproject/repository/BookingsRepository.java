package com.company.arsproject.repository;

import com.company.arsproject.entity.Bookings;
import com.company.arsproject.entity.Flights;
import com.company.arsproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface BookingsRepository extends JpaRepository<Bookings, Long> {
    List<Bookings> findAllByUser(User user);
    List<Bookings> findAllByFlights(Flights flights);
}