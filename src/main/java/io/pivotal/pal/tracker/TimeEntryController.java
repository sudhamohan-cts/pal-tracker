package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){
        System.out.println("1"+timeEntry);
        TimeEntry timeEntry1 = timeEntryRepository.create(timeEntry);
        System.out.println("2"+timeEntry1);
        return new ResponseEntity<TimeEntry>(timeEntry1, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id){
        TimeEntry timeEntry =timeEntryRepository.find(id);
        if(timeEntry!=null){
            return new ResponseEntity<TimeEntry>(timeEntry,HttpStatus.OK);
        }
        return new ResponseEntity<TimeEntry>(timeEntry,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id,@RequestBody TimeEntry timeEntry){
        System.out.println(""+id+timeEntry);
        TimeEntry timeEntry1 = timeEntryRepository.update(id,timeEntry);
        if(timeEntry1!=null){
            return new ResponseEntity<TimeEntry>(timeEntry,HttpStatus.OK);
        }
        return new ResponseEntity<TimeEntry>(timeEntry,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        timeEntryRepository.delete(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list(){
        return new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(),HttpStatus.OK);
    }
}
