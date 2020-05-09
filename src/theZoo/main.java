package theZoo;

public class main {
    public static void main(String args[]){
        Animal[] animals = Zoo.initilise();
        Zoo.listAllAnimals(animals);
        System.out.printf("The average animal height is: %f", Zoo.averageHeight(animals));
    }
}
