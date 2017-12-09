package com.codetron.cloud.queue.customer;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by josetesan on 10/07/16.
 */
@Repository
public interface CustomerRepository extends ReactiveSortingRepository<Customer,Long> {


}
