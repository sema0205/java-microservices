package org.example.dao;

import org.example.cat.Cat;
import org.example.cat.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CatDao extends JpaRepository<Cat, Long> {

    @Query(value = """
            SELECT * FROM cat
            WHERE color = :color
            """, nativeQuery = true)
    List<Cat> getAllByColor(
            @Param("color") Color color
    );

}