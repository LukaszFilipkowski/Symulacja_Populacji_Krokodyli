import java.util.Random; 

public interface Habitat {
    Random random = new Random();

    public int getSpace();
    public int getResidents();
    public Crocodile[] getListOfCrocodiles();
    public void addCrocodile(String sex, Habitat habitat, Habitat habitat_fight, Habitat habitat_fight2);
    public String[] listOfSexes();
    public int indexOfHabitat();
    public void setListOfCrocodiles(Crocodile[] listOfCrocodiles);
    public void updateListOfCrocodiles();
    public int getMales();
    public int getFemales();
    public int getBorned();
    public void delBorned();

    

    public default String chooseSex() {
        int sexRandomization = random.nextInt(2);
        String sex;

        if (sexRandomization == 0) sex = "female";
        else sex = "male";

        return sex;
    }


    public default int howManyBorned() {
        int borned = random.nextInt(2);
        
        if (borned == 1) return 14;
        return 13;
    }
    
}

