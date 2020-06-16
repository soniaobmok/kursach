package com.gateway.apigateway.Barber;

import com.gateway.apigateway.CustomException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="barber-service")
public interface BarberClient {
    @RequestMapping(path="/barber", method = RequestMethod.POST)
    public @ResponseBody Barber add(@RequestBody Barber barber);

    @RequestMapping(path="/barber", method = RequestMethod.GET)
    public @ResponseBody Iterable<Barber> getAll();

    @RequestMapping(path="/barber/{id}", method = RequestMethod.GET)
    public @ResponseBody Barber getById(@PathVariable int id) throws CustomException;

    @RequestMapping(path="/barber/find/{name}", method = RequestMethod.GET)
    public @ResponseBody Barber getIdByName(@PathVariable String name);

//    @RequestMapping(path="/barber/filter/{type}", method = RequestMethod.GET)
//    public @ResponseBody Iterable<Barber> getByFilter(@PathVariable String type);

    @RequestMapping(path="/barber/{id}", method = RequestMethod.PUT)
    public @ResponseBody Barber update(@PathVariable Integer id,
                                          @RequestBody Barber barber) throws CustomException;

    @RequestMapping(path="/barber/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) throws CustomException;
}