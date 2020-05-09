package theZoo;

public class Dog extends Animal {
    boolean running, chewing, playing;

    public void setChewing(boolean chewing) {
        this.chewing = chewing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
    public boolean getChewing(){
        return this.chewing;
    }
    public boolean getRunning(){
        return this.running;
    }
    public boolean getPlaying(){
        return this.playing;
    }
    public void findBall(){
        this.chewing = true;
        this.playing = true;
    }
}
