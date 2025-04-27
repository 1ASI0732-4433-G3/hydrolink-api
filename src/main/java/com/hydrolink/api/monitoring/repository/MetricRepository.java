package com.hydrolink.api.monitoring.repository;

import com.hydrolink.api.monitoring.model.entities.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MetricRepository extends JpaRepository<Metric, Long> {
}
