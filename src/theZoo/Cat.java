package theZoo;

public class Cat extends Animal {
    Boolean purring, eating, sleeping;

    public void setEating(Boolean eating) {
        this.eating = eating;
    }

    public void setPurring(Boolean purring) {
        this.purring = purring;
    }

    public void setSleeping(Boolean sleeping) {
        this.sleeping = sleeping;
    }

    public Boolean getEating() {
        return eating;
    }

    public Boolean getPurring() {
        return purring;
    }

    public Boolean getSleeping() {
        return sleeping;
    }
    public void catchMouse(){
        this.eating = true;
    }
}
