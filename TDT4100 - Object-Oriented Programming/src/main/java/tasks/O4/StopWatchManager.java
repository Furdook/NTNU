package tasks.O4;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

public class StopWatchManager {
    private HashMap<String, StopWatch> stopWatches = new HashMap<String, StopWatch>();

    public StopWatch newStopWatch(String name) {
        StopWatch stopWatch = new StopWatch();
        this.stopWatches.put(name, stopWatch);
        return stopWatch;
    }

    public void removeStopWatch(String name) {
        this.stopWatches.remove(name);
    }

    public void tick(int ticks) {
        for (StopWatch stopWatch : this.stopWatches.values()) {
            stopWatch.tick(ticks);
        }
    }

    public StopWatch getStopWatch(String name) {
        return this.stopWatches.get(name);
    }

    public Collection<StopWatch> getAllWatches() {
        return this.stopWatches.values();
    }

    /* public Collection<StopWatch> getStartedWatches() {
        Collection<StopWatch> startedWatches = new ArrayList<StopWatch>();

        for (StopWatch stopWatch : this.stopWatches.values()) {
            if (stopWatch.isStarted()) 
                startedWatches.add(stopWatch);
        }
        return startedWatches;
    } */

    public Collection<StopWatch> getStartedWatches() {
        return this.stopWatches.values().stream().filter(w -> w.isStarted()).collect(Collectors.toList());
    }

    public Collection<StopWatch> getStoppedWatches() {
        return this.stopWatches.values().stream().filter(w -> w.isStopped()).collect(Collectors.toList());
    }
}
