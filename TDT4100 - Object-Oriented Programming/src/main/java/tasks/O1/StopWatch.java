package tasks.O1;

public class StopWatch {
    private boolean started;
    private boolean stopped;
    private int ticks = 0;
    private int time = -1;
    private int lap = -1;
    private int lastLap = -1;

    public StopWatch() {}

    public boolean isStarted() {
        return this.started;
    } 
    public boolean isStopped() {
        return this.stopped;
    }
    
    public int getTicks() {
        return this.ticks;
    } 
    
    public int getTime() {
        return this.time;
    } 
   
    public int getLapTime() {
        return this.lap;
    } 
   
    public int getLastLapTime() {
        return this.lastLap;
    } 
    
    public void tick(int ticks) {
        this.ticks += ticks;

        if (this.started && !this.stopped) {
            this.time += ticks;
            this.lap += ticks;
        }
    } 
    
    public void start() {
        this.started = true;
        this.time = 0;
        this.lap = 0;
    }
    
    public void lap() {
        this.lastLap = this.lap;
        this.lap = 0;
    }
    
    public void stop() {
        this.stopped = true;
        this.lap();
    } 
}
