package com.andydolan94.pizzaperfect.repositories;

import com.andydolan94.pizzaperfect.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("sizeRepository")
public interface SizeRepository extends JpaRepository<Size, Long> {}
