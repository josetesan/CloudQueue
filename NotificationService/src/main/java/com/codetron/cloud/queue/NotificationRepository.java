package com.codetron.cloud.queue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by josetesan on 10/07/16.
 */

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long>{


    List<Notification> findAllByCustomerId(final Long customerId);

}
