package webCalendarSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping("/event")
public class CalenderController {
    @Autowired
    private CalenderService calenderService;


    @GetMapping("")
    public ResponseEntity<ArrayList<EventDataObject>>
    getEventsForInterval(@RequestParam(value = "start_time",required = false)
                         LocalDate startDate,
                         @RequestParam(value = "end_time",required = false)
                         LocalDate endDate) {
        if(startDate == null || endDate == null){
            ArrayList<EventDataObject> events = calenderService.getEvents();
            if (calenderService.checkEventAvailable(events)) {
                return ResponseEntity.ok(events);
            }

            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
        ArrayList<EventDataObject> results = calenderService.findWithinInterval(startDate, endDate);
        if (results.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(results);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteEventById(@PathVariable("id") int id) {
        Optional<EventDataObject> optionalEvent = calenderService.findById(id);
        if (optionalEvent.isEmpty()) {
            return new ResponseEntity<>(new EventError("The event doesn't exist!"), HttpStatus.NOT_FOUND);
        }
        calenderService.deleteById(id);
        return new ResponseEntity<>(optionalEvent.get(), HttpStatus.OK);
        //todo: deleting event results in 404 instead of 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getEventById(@PathVariable("id") int id) {
        Optional<EventDataObject> optionalEvent = calenderService.findById(id);
        if (optionalEvent.isEmpty()) {
            return new ResponseEntity<>(new EventError("The event doesn't exist!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalEvent.get(), HttpStatus.OK);
    }

    @GetMapping("/today")
    public ResponseEntity<ArrayList<EventDataObject>> getEventsToday() {

        ArrayList<EventDataObject> events = calenderService.getEventsToday();
        // if(calenderService.checkEventAvailable(events)){
        return ResponseEntity.ok(events);
        //  }
        // return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<Event> postToday(@RequestBody Event event) {
        if (calenderService.saveIfValid((event))) {
            event.setMessage(("The event has been added!"));
            return ResponseEntity.ok(event);
        }
        return ResponseEntity.badRequest().build();

    }
}
