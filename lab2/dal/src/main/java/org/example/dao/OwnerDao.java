package org.example.dao;

import org.example.cat.Cat;
import org.example.cat.Color;
import org.example.owner.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface OwnerDao extends JpaRepository<Owner, Long> {

    @Query(value = """
            SELECT * FROM owner
            WHERE birthdate is not null
            AND birthdate between :start and :end
            """, nativeQuery = true)
    List<Owner> getAllByBirthDateRange(
            @Param("start") Timestamp start,
            @Param("end") Timestamp end
    );


    @Modifying
    @Query(value = """
            INSERT INTO cat_owner_item
            (owner_id, cat_id)
            VALUES (:ownerId, :catId)
            """, nativeQuery = true)
    void addCat(
            @Param("ownerId") Long ownerId,
            @Param("catId") Long catId
    );

}
