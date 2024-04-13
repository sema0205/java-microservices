package org.example.dao;

import org.example.cat.Cat;
import org.example.cat.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CatDao extends JpaRepository<Cat, Long> {

    @Query(value = """
            SELECT * FROM cat
            LEFT JOIN cat_owner_item o ON cat.id = o.cat_id
            WHERE color = :color
            """, nativeQuery = true)
    List<Cat> getAllByColor(
            @Param("color") String color
    );

    List<Cat> findDistinctByColor(Color color);

    @Modifying
    @Query(value = """
            INSERT INTO cat_friend_item
            (cat_id, friend_cat_id)
            VALUES (:catId, :friendId)
            """, nativeQuery = true)
    void addFriend(
            @Param("catId") Long catId,
            @Param("friendId") Long friendId
    );

}