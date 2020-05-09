package theZoo;

public class Player {
    protected boolean immune, human;
    protected int infect_rate, turns;

    public void Player(boolean immune, int infect_rate, boolean human){
        this.infect_rate=infect_rate;
        this.immune=immune;
        this.human=human;
    }

    public void set_Infect_rate(int rate){
        this.infect_rate=rate;
    }
    public void set_immune(boolean immune){
        this.immune=immune;
    }
    public int getInfect_rate(){
        return infect_rate;
    }
    public boolean get_immune(){
        return immune;
    }
    public void set_human(boolean human){
        this.human=human;
    }
    public boolean get_human(){
        return human;
    }
    public int get_turn(){
        return 10;
    }
    public void set_turns(){
    }
    public void inc_turns(){
    }
    public int getExposure(){
        return 0;
    }
    public void setExposure(int exposure){
    }
}
