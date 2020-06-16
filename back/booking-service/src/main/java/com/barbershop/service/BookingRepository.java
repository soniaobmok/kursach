package com.barbershop.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
    @Query("SELECT u FROM Booking u WHERE u.clientId = ?1")
    List<Booking> findUsersBookings(Integer id);

    @Query("SELECT u FROM Booking u WHERE u.barberId = ?1")
    List<Booking> findBarberBookings(Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Booking u WHERE u.barberId = ?1")
    void deleteByBarberId(Integer id);
}