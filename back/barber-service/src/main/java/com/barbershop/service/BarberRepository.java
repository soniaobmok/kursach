package com.barbershop.service;

import com.barbershop.service.Barber;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BarberRepository extends CrudRepository<Barber, Integer> {
    @Query("SELECT u FROM Barber u WHERE u.name = ?1")
    Barber findByName(String name);
    @Query("SELECT u FROM Barber u WHERE u.id = ?1")
    Barber find(Integer id);
//    @Query("SELECT u FROM Equipment u WHERE u.type = ?1")
//    Iterable<Equipment> findByType(String type);

    @Transactional
    @Modifying
    @Query("UPDATE Barber u SET u.name = :name, u.description = :description, u.rating = :rating WHERE id = :id")
    Integer customUpdate(@Param("id") Integer id,
                         @Param("name") String name,
//                         @Param("price") Float price,
//                         @Param("type") String type,
                         @Param("description") String description,
                         @Param("rating") Integer rating);

}