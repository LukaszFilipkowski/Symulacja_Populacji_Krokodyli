import java.util.Random;

public class DriedWetland implements Habitat {
    final int space;
    private int residents = 0;
    private int females = 0;
    private int males = 0;
    private int borned = 0;
    private Crocodile[] listOfCrocodiles;


    DriedWetland(int k2) {
        this.space = k2;
        listOfCrocodiles = new Crocodile[this.space];
    }

    public int getSpace() {
        return space;
    }

    public int getResidents() {
        return residents;
    }


    public int getFemales() {
        return females;
    }


    public int getMales() {
        return males;
    }

    public int getBorned() {
        return borned;
    }

    public void delBorned() {
        borned = 0;
    }

    public Crocodile[] getListOfCrocodiles() {
        return listOfCrocodiles;
    }

    
    public void setListOfCrocodiles(Crocodile[] listOfCrocodiles) {
        this.listOfCrocodiles = listOfCrocodiles;
    }


    public int indexOfHabitat() {
        return 2;
    }


    public void addCrocodile(String sex, Habitat habitat, Habitat habitat_fight, Habitat habitat_fight2) {
        if (residents < this.space) {
            listOfCrocodiles[residents] = new Crocodile(sex, habitat);
            residents++;
            borned++;
            if (sex == "male") 
                males++;
            else females++;
        } else {
            Random random = new Random();
            int ifLost = random.nextInt(2);
            if (ifLost == 1) {
                habitat_fight.addCrocodile(sex, habitat_fight, habitat_fight2, habitat_fight2);
            }
        }
    }


    public String[] listOfSexes() {
        int numberOfNewborns = howManyBorned();
    
        String[] sexes = new String[numberOfNewborns];
            
        Random random = new Random();
        int number = random.nextInt(2);
        String[] sex = {"male", "female"};
            
        int alreadyAssaigned = 0;
        for (int i = 0; i < numberOfNewborns / 2; i++) {
            sexes[i] = sex[number];
            alreadyAssaigned++;
        }
        for (int i = 0; i < numberOfNewborns - alreadyAssaigned; i++) {
            sexes[i] = sex[1 - number];
        }
        return sexes;
    }

    
    public void updateListOfCrocodiles() {
        int j = 0;
        int currRes = residents;
        residents = 0;
        females = 0;
        males = 0;
        Crocodile[] list = new Crocodile[space];

        for (int i = 0; i < currRes; i++) {
            if (listOfCrocodiles[i] != null) {
                list[j] = listOfCrocodiles[i];
                j++;
                
                residents++;
                if (listOfCrocodiles[i].getSex() == "female") females++;
                else males++;
            }
        }
        setListOfCrocodiles(list);
    }

}


