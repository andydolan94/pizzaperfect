package com.andydolan94.pizzaperfect.repositories;

import com.andydolan94.pizzaperfect.entities.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("pizzaRepository")
public interface PizzaRepository extends JpaRepository<Pizza, Long> {}
