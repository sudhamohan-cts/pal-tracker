package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Long id =0L;
    Map<Long,TimeEntry> map = new HashMap<>();

    public TimeEntry create(TimeEntry timeEntry){
        ++id;
        timeEntry.setId(id);
        map.put(id,timeEntry);
        return timeEntry;
    }

    public TimeEntry find(Long id){
        return map.get(id);
    }

    public List<TimeEntry> list(){
        return map.values().stream()
                .collect(Collectors.toList());
    }

    public TimeEntry update(Long id, TimeEntry timeEntry){
        TimeEntry existOne = map.get(id);
        if(existOne!=null){
            timeEntry.setId(id);
            map.put(id,timeEntry);
        }
        return map.get(id);
    }

    public TimeEntry delete(Long id){
        return map.remove(id);
    }
}
