
import java.util.Random;

public class Crocodile {
    private Random random = new Random();


    final String sex;
    private Habitat habitat;
    private int age = 0;
    final int procreationAge = setProcreationAge();
    final int deathDate = setDeathDate();


    public Crocodile(String sex, Habitat habitat) {
        this.sex = sex;
        this.habitat = habitat;
    }
    

    private int setProcreationAge() {
        return (int) (random.nextGaussian() * 1 + 12);
    }


    private int setDeathDate() {
        return (int) (random.nextGaussian() * 4 + 30);
    }


    public Habitat getHabitat() {
        return habitat;
    }


    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }


    public String getSex() {
        return sex;
    }

    
    public int getAge() {
        return age;
    }


    public void increaseAge() {
        this.age = this.getAge() + 1;
    }


    public void procreates(Habitat habitat, Habitat habitat_fight, Habitat habitat_fight2) {
        if (sex == "female" && age >= procreationAge) {
            for (String sex: this.habitat.listOfSexes()) {
                this.habitat.addCrocodile(sex, habitat, habitat_fight, habitat_fight2);
            }
        }
    }
}