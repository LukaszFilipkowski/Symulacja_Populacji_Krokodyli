public class Emblankment implements Habitat {
    final int space;
    private int residents = 0;
    private int females = 0;
    private int males = 0;
    private int borned = 0;
    private Crocodile[] listOfCrocodiles;


    Emblankment(int k3) {
        this.space = k3;
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
        return 3;
    }


    public void addCrocodile(String sex, Habitat habitat, Habitat habitat_fight, Habitat habitat_fight2) {     
        if (residents < this.space) {
            listOfCrocodiles[residents] = new Crocodile(sex, habitat);
            residents++;
            borned++;
            if (sex == "male") males++;
            else females++;
        }
    }


    public String[] listOfSexes(){
        int numberOfNewborns = howManyBorned();

        String[] sexes = new String[numberOfNewborns];
        
        for (int i = 0; i < numberOfNewborns; i++) {
            sexes[i] = "male";
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


