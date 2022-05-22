package com.andydolan94.pizzaperfect.repositories;

import com.andydolan94.pizzaperfect.entities.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("toppingRepository")
public interface ToppingRepository extends JpaRepository<Topping, Long> {}
