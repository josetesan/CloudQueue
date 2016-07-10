package com.codetron.cloud.queue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by josetesan on 10/07/16.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
