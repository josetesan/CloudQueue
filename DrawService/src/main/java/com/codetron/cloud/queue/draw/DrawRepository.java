package com.codetron.cloud.queue.draw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by josetesan on 10/07/16.
 */
@Repository
public interface DrawRepository extends JpaRepository<Draw,Long> {
}
