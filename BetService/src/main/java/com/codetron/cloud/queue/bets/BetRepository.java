package com.codetron.cloud.queue.bets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by josetesan on 10/07/16.
 */

@Repository
public interface BetRepository extends JpaRepository<Bet,Long>{


    List<Bet> findAllByUserId(final Long userId);

}
