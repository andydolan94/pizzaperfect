package com.andydolan94.pizzaperfect.repositories;

import com.andydolan94.pizzaperfect.entities.PizzaOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Pizza Orders
 */
@Repository("pizzaOrderRepository")
public interface PizzaOrderRepository extends JpaRepository<PizzaOrder, Long> {}
