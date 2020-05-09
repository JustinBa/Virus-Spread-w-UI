package theZoo;

public class Mouse extends Animal {
    boolean cheese, mouseHole;
    int whiskerLength;

    public void setCheese(boolean cheese) {
        this.cheese = cheese;
    }

    public void setHole(boolean mouseHole) {
        this.mouseHole = mouseHole;
    }

    public void setWhiskerLength(int whiskerLength) {
        this.whiskerLength = whiskerLength;
    }

    public int getWhiskerLength() {
        return whiskerLength;
    }

    public boolean getCheese(){
        return this.cheese;
    }

    public boolean getHole(){
        return this.mouseHole;
    }

    public void makeMouseHole(){
        this.mouseHole = true;
    }
}
