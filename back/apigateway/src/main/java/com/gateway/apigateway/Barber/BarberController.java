package com.gateway.apigateway.Barber;

import com.gateway.apigateway.Booking.Booking;
import com.gateway.apigateway.Booking.BookingClient;
import com.gateway.apigateway.CustomException;
import com.gateway.apigateway.Feedback.Feedback;
import com.gateway.apigateway.Feedback.FeedbackClient;
import com.gateway.apigateway.User.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/barber")
public class BarberController {
    @Autowired
    BarberClient client;

    @Autowired
    FeedbackClient feedbackClient;

    @Autowired
    BookingClient bookingClient;

    @Autowired
    UserClient userClient;

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody Barber add(@RequestBody Barber barber,
                                       @RequestHeader(value = "Authorization") String token) {
        userClient.isAdmin(token);
        return client.add(barber);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<Barber> getAll() {
        return client.getAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Barber getById(@PathVariable int id) throws CustomException {
        return client.getById(id);
    }

    @RequestMapping(path="/find/{name}", method = RequestMethod.GET)
    public @ResponseBody Barber getIdByName(@PathVariable String name) {
        return client.getIdByName(name);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    public @ResponseBody Barber update(@PathVariable Integer id,
                                          @RequestBody Barber barber,
                                          @RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isAdmin(token);
        return client.update(id, barber);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id,
                                       @RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isAdmin(token);
        feedbackClient.deleteByBarberId(id);
        bookingClient.deleteByBarberId(id);
        return client.delete(id);
    }
}
