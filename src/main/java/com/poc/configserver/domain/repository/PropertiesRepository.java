package com.poc.configserver.domain.repository;

import com.poc.configserver.domain.entity.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data JPA repository for the Properties entity.
 */
@Repository
public interface PropertiesRepository extends JpaRepository<Properties, Long> {

    @Query(value = "select p.application from Properties p group by p.application", nativeQuery = true)
    List<String> findAllApplications();

}