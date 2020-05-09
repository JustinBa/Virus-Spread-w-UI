package theZoo;

public class Zoo {
    public static Animal[] initilise() {
            Animal animals[] = new Animal[3];
        animals[0] = new Cat();
        animals[1] = new Dog();
        animals[2] = new Mouse();


        animals[0].Animal(7,12,2,"Bob");
        animals[1].Animal(14,22,4,"Sam");
        animals[2].Animal(3,2,6,"Joe");

        return animals;

    }

    public static void listAllAnimals(Animal[] theAnimals){
        System.out.println("List of all animals:");
        for( int i = 0; i < theAnimals.length; i++){
            System.out.println(theAnimals[i].getName());
        }
    }

    public static double averageHeight(Animal[] animals){
        double workingHeight = 0;

        for(int i = 0; i < animals.length; i++){
            workingHeight = workingHeight + animals[i].height;
        }
        workingHeight = workingHeight * 1.0 / animals.length;

        return workingHeight;

    }
}
