package com.barbershop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
@RequestMapping(path="/booking")
public class BookingController {

    @Autowired
    private BookingRepository repository;

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody Booking add(@RequestBody Booking booking) throws ParseException {
        Booking b = new Booking();
        b.setDate(booking.getDate());
        b.setBarberId(booking.getBarberId());
        b.setClientId(booking.getClientId());
        return repository.save(b);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getAll() {
        return repository.findAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Booking getById(@PathVariable int id) throws CustomException {
        return repository.findById(id).orElseThrow(() -> new CustomException("Item wasn't found"));
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getUsersOrders(@PathVariable Integer id) {
        return repository.findUsersBookings(id);
    }

    @RequestMapping(path = "/barber/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getBarberOrders(@PathVariable Integer id) {
        return repository.findBarberBookings(id);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) throws CustomException {
        Booking existing = repository.findById(id).orElseThrow(() -> new CustomException("Item wasn't found"));
        repository.delete(existing);
        return "Deleted";
    }

    @RequestMapping(path="/barber/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteByBarberId(@PathVariable Integer id) throws CustomException {
        repository.deleteByBarberId(id);
        return "Deleted";
    }
}
