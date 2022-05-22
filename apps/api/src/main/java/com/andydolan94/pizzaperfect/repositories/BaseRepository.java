package com.andydolan94.pizzaperfect.repositories;

import com.andydolan94.pizzaperfect.entities.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("baseRepository")
public interface BaseRepository extends JpaRepository<Base, Long> {}
