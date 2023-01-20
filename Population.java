import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Population {
    int k1, k2, k3;
    int cycle;
    int borned[];
    int list_of_residents[];
    int males[];
    int females[];
    float crocodileAtFirst;
    Wetland wetland;
    DriedWetland driedWetland;
    Emblankment emblankment; 


    Population(int cycle, int k1, int k2, int k3, float crocodileAtFirst) {
        this.cycle = cycle;
        this.k1 = k1;   
        this.k2 = k2;
        this.k3 = k3;
        this.crocodileAtFirst = crocodileAtFirst;
        initiateHabitats(this.k1, this.k2, this.k3);
    }


    Population() {
        this.cycle = 70;
        this.k1 = 30200;
        this.k2 = 23300;
        this.k3 = 14100;
        this.crocodileAtFirst = 0.0003f;
        initiateHabitats(this.k1, this.k2, this.k3);
    }

    // GŁÓWNA METODA
    public void startPopulation() {
        residentsAtFirst(wetland, driedWetland, emblankment);
        residentsAtFirst(driedWetland, emblankment, emblankment);
        residentsAtFirst(emblankment, emblankment, emblankment);
        wetland.delBorned();
        driedWetland.delBorned();
        emblankment.delBorned();
        borned = new int[this.cycle];
        list_of_residents = new int[this.cycle + 1];
        males = new int[this.cycle];
        females = new int[this.cycle];

        printHabitats();

        XYSeries series = new XYSeries("POPULACJA");
        
        for (int i = 0; i < cycle; i++) {

            list_of_residents[i] = wetland.getResidents() + driedWetland.getResidents() + emblankment.getResidents();

            for (Habitat habitat : new Habitat[] {wetland, driedWetland, emblankment}) {
                allAgeing(habitat);
                ifMustThenDie(habitat);
            }
            ifCanThenProcreate(wetland, driedWetland, emblankment);
            ifCanThenProcreate(driedWetland, emblankment, emblankment);
            ifCanThenProcreate(emblankment, emblankment, emblankment);
            borned[i] = wetland.getBorned() + driedWetland.getBorned() + emblankment.getBorned();
            males[i] = wetland.getMales() + driedWetland.getMales() + emblankment.getMales();
            females[i] = wetland.getFemales() + driedWetland.getFemales() + emblankment.getFemales();
            wetland.delBorned();
            driedWetland.delBorned();
            emblankment.delBorned();
            
            printPopulation(i+1);


            int allResidents = wetland.getResidents() + driedWetland.getResidents() + emblankment.getResidents();
            series.add(i, allResidents);
        }
        list_of_residents[this.cycle] = wetland.getResidents() + driedWetland.getResidents() + emblankment.getResidents();
       chartFrame(series);
            create_table();
    }


    private void printHabitats() {
        System.out.println();
        System.out.println("Habitats (all space): " + (wetland.space + driedWetland.space + emblankment.space));
        System.out.println("Wetland: " + wetland.space + " Dried wetland: " + driedWetland.space + " Emblankment: " + emblankment.space);
        System.out.println();
    }


    private void printPopulation(int cycleNumber) {
        System.out.println();
        System.out.println("Cycle: " + cycleNumber);
        System.out.println("Residents: " + (wetland.getResidents() + driedWetland.getResidents() + emblankment.getResidents()));
        System.out.println("Males: " + (wetland.getMales() + driedWetland.getMales() + emblankment.getMales()));
        System.out.println("Females: " + (wetland.getFemales() + driedWetland.getFemales() + emblankment.getFemales()));
        System.out.println();
    }


    private void initiateHabitats(int k1, int k2, int k3) {
        this.wetland = new Wetland(k1);
        this.driedWetland = new DriedWetland(k2);
        this.emblankment = new Emblankment(k3);
    }


    private void residentsAtFirst(Habitat habitat, Habitat habitat_fight, Habitat habitat_fight2) {
        int habitatResidents = (int)(habitat.getSpace() * crocodileAtFirst);

        for (int i = 0; i < habitatResidents; i++) {
            habitat.addCrocodile(habitat.chooseSex(), habitat, habitat_fight, habitat_fight2);
        }
    }


    private void allAgeing(Habitat habitat) {
        Crocodile[] habitatCrocodiles = habitat.getListOfCrocodiles();

        for (int i = 0; i < habitat.getResidents(); i++) {
            habitatCrocodiles[i].increaseAge();
        }
    }

    
    private void ifCanThenProcreate(Habitat habitat, Habitat habitat_fight, Habitat habitat_fight2) {
        Crocodile[] habitatCrocodyles = habitat.getListOfCrocodiles();

        for (int i = 0; i < habitat.getResidents(); i++) {
            if (habitat.getMales() >= 1) 
                habitatCrocodyles[i].procreates(habitat, habitat_fight, habitat_fight2);
        }

    }

    private void ifMustThenDie(Habitat habitat) {
        Crocodile[] habitatCrocodiles = habitat.getListOfCrocodiles();
        
        for (int i = 0; i < habitat.getResidents(); i++) {
            Crocodile crocodile = habitatCrocodiles[i];
            if (crocodile.getAge() == crocodile.deathDate) 
                habitatCrocodiles[i] = null;
        }

        habitat.setListOfCrocodiles(habitatCrocodiles);
        habitat.updateListOfCrocodiles();
    }

    public void chartFrame(XYSeries series) {

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
			"Wykres populacji",//Tytuł
			"cykl", // x-axis Opis
			"liczba krokodyli", // y-axis Opis
			dataset, // Dane
			PlotOrientation.VERTICAL, // Orjentacja wykresu /HORIZONTAL
			true, // pozkaż legende
			true, // podpowiedzi tooltips
 		false
		);

		ChartFrame frame1=new ChartFrame("Wykres populacji", chart);
		frame1.setVisible(true);
		frame1.setSize(1500,1000);

    }

    public void create_table() {
        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);
        System.out.println("TABELA STANU POPULACJI:");
        st.setHeaders("Cykl", "Wyklutych", "Zmarłych", "Obecnych", "Samce", "Samice");
        for (int i = 0; i < cycle; i++) {
            st.addRow(Integer.toString (i + 1), Integer.toString (borned[i]), Integer.toString (list_of_residents[i] - list_of_residents[i + 1] + borned[i]), Integer.toString (list_of_residents[i+1]),Integer.toString (males[i]), Integer.toString (females[i]));
        }
        st.print();

    }
}
