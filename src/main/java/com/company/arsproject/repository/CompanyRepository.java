package com.company.arsproject.repository;

import com.company.arsproject.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "companies")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
