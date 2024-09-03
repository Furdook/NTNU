package tasks.O4;

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
        if (ticks < 0) throw new IllegalArgumentException();
        this.ticks += ticks;

        if (this.started && !this.stopped) {
            this.time += ticks;
            this.lap += ticks;
        }
    } 
    
    public void start() {
        if (this.started) throw new IllegalStateException();
        this.started = true;
        this.time = 0;
        this.lap = 0;
    }
    
    public void lap() {
        if (!this.started || this.stopped) throw new IllegalStateException();
        this.lastLap = this.lap;
        this.lap = 0;
    }
    
    public void stop() {
        if (this.stopped) throw new IllegalStateException();
        this.lap();
        this.stopped = true;
    } 
}
