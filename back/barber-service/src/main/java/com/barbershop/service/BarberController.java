package com.barbershop.service;

import com.barbershop.service.Barber;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/barber")
public class BarberController {

    @Autowired
    private BarberRepository repository;

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody
    Barber add(@RequestBody Barber barber) {
        return  repository.save(barber);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<Barber> getAll() {
        return repository.findAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Barber getById(@PathVariable int id) throws CustomException {
        return repository.findById(id).orElseThrow(() -> new CustomException("Item wasn't found"));
    }

    @RequestMapping(path="/find/{name}", method = RequestMethod.GET)
    public @ResponseBody Barber getIdByName(@PathVariable String name) {
        return repository.findByName(name);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    public @ResponseBody Barber update(@PathVariable Integer id,
                                          @RequestBody Barber barber) throws CustomException {
        repository.findById(id).orElseThrow(() -> new CustomException("Item wasn't found"));
        repository.customUpdate(id, barber.getName(),
                barber.getDescription(), barber.getRating());
        barber.setId(id);
        return barber;
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) throws CustomException {
        Barber existing = repository.findById(id).orElseThrow(() -> new CustomException("Item wasn't found"));
        repository.delete(existing);
        return "Deleted";
    }
}
