package com.peekaboo.toy.repository;

import com.peekaboo.toy.entity.Toy;
import com.peekaboo.toy.enums.ToyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToyRepository extends JpaRepository<Toy, Long> {

    List<Toy> findToysByStatus(ToyStatus toyStatus);
    List<Toy> findToyByNameContainingIgnoreCase(String name);
    List<Toy> findByBrandContainingIgnoreCase(String brand);

}
