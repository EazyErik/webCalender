package webCalendarSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CalenderService {
    @Autowired
    private EventRepository eventRepository;

    public ArrayList<EventDataObject> getEvents() {
        ArrayList<EventDataObject> results = new ArrayList<EventDataObject>();

        Iterable<EventDataObject> events = eventRepository.findAll();
        events.forEach(results::add);
        return results;
    }

    public ArrayList<EventDataObject> findWithinInterval(LocalDate startDate, LocalDate endDate) {
        ArrayList<EventDataObject> events = getEvents();

        ArrayList<EventDataObject> results = new ArrayList<EventDataObject>();
        Stream<EventDataObject> eventDataObjectStream = events.stream().filter(event ->
                (event.getDate().isAfter(startDate) || event.getDate().isEqual(startDate)) &&
                        (event.getDate().isBefore(endDate) || event.getDate().isEqual(endDate)));
        eventDataObjectStream.forEach(results::add);
        return results;
    }

    public Optional<EventDataObject> findById(int id) {
        return eventRepository.findById(id);
    }

    public void deleteById(int id) {
        eventRepository.deleteById(id);
    }

    public boolean checkEventAvailable(ArrayList<EventDataObject> results) {
        if (results == null || results.isEmpty()) {
            return false;
        }
        return true;
    }


    public boolean saveIfValid(Event event) {
        if (event.getEvent() == null || event.getEvent().isEmpty() || event.getEvent().isBlank()) {
            return false;
        } else if (event.getDate() == null) {
            return false;
        }
        EventDataObject dataObject = new EventDataObject();
        dataObject.setEvent(event.getEvent());
        dataObject.setDate(event.getDate());
        eventRepository.save(dataObject);
        return true;
    }


    public ArrayList<EventDataObject> getEventsToday() {
        ArrayList<EventDataObject> events = getEvents();
        LocalDate today = LocalDate.now();
        ArrayList<EventDataObject> results = new ArrayList<EventDataObject>();
        Stream<EventDataObject> eventDataObjectStream = events.stream().filter(event ->
                event.getDate().equals(today));
        eventDataObjectStream.forEach(results::add);
        return results;

    }


}
