package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class TimeEntryController {

    public TimeEntryRepository timeEntryRepo;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepo = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {

        TimeEntry timeEntry = timeEntryRepo.create(timeEntryToCreate);
        return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepo.find(timeEntryId);
        if(timeEntry==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK);
        }
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> list = timeEntryRepo.list();
        return new ResponseEntity<List<TimeEntry>>(list, HttpStatus.OK);
    }
    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {

        TimeEntry update = timeEntryRepo.update(timeEntryId, timeEntryToUpdate);
        if(update == null) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<TimeEntry>(update, HttpStatus.OK);
        }
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        timeEntryRepo.delete(timeEntryId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}