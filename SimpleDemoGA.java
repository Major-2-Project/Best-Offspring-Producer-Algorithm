package major2;
import java.util.*;
//Main class
public class SimpleDemoGA {

	static void display(int n, int m)
	{
		int i, j;
		for (i = 1; i <= n; i++)
		{
			for (j = 1; j <= m; j++)
			{
                if (i==1 || i==n || j==1 || (j==m && i!=4 && i!=5))             
                System.out.print("@");	
                else if (i==4 && j==(m-32))
                System.out.print("@");	   
                else if (i==5 && j==(m-26))
                System.out.print("@");                     
                else if (i==4 && j==57)
                System.out.print("Best Offspring Producer Algorithm");
                else if (i==5 && j==57)
                    System.out.print("Genetic Algorithm          ");
                else
                System.out.print(" "); 
	
			}
			System.out.println();
		}
	
	
        
	}
    Population population = new Population();
    Individual fittest;
    Individual secondFittest;
    int generationCount = 0;

    public static void main(String[] args) {
    	int rows = 9, columns = 135; 
        System.out.println("\t\t\t\t\t\t            MAJOR 2 ");
        System.out.println(); 
       display(rows, columns); 
        System.out.println(); 
        System.out.println("\t\t\t\t\t\t\t           Approved by: ");
        System.out.println();
        System.out.println(" Mr. Abhirup Khanna  \t\t\t\t\t\t                                               " );
        System.out.println("       [MENTOR]  \t\t\t\t\t\t\t\t                                    " );
        System.out.println("\t\t\t\t\t\t\t           Submitted by: ");
        System.out.println();
        System.out.println("Ishika Mahawar [060]                     Sarthak Goyal [122] \t\t       Shreya Aggarwal [133]             \tTanisha Goyal[144]" );
        System.out.println();
Scanner sc=new Scanner(System.in);
        Random rn = new Random();

        SimpleDemoGA demo = new SimpleDemoGA();

        //Initialize population
        int size;
        System.out.println("Enter population size");
        size=sc.nextInt();
        demo.population.initializePopulation(size);

        //Calculate fitness of each individual
        demo.population.calculateFitness();

        System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);

        //While population gets an individual with maximum fitness
        while (demo.population.fittest <5) {
            ++demo.generationCount;

            //Do selection
            demo.selection();

            //Do crossover
            demo.crossover();

            //Do mutation under a random probability
            if (rn.nextInt() < 5) {
                demo.mutation();
            }

            //Add fittest offspring to population
            demo.addFittestOffspring();

            //Calculate new fitness value
            demo.population.calculateFitness();

            System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);
        }

        System.out.println("\nBest Offspring Solution found in generation " + demo.generationCount);
        System.out.println("Fitness of best offspring : "+demo.population.getFittest().fitness);
        System.out.print("Genes: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(demo.population.getFittest().genes[i]);
        }

        System.out.println("");

    }

    //Selection
    void selection() {

        fittest = population.getFittest();

        //Select the second most fittest individual
        secondFittest = population.getSecondFittest();
    }

    //Crossover
    void crossover() {
        Random rn = new Random();

        //Select a random crossover point
        int crossOverPoint = rn.nextInt(population.individuals[0].geneLength);

        //Swap values among parents
        for (int i = 0; i < crossOverPoint; i++) {
            int t = fittest.genes[i];
            fittest.genes[i] = secondFittest.genes[i];
            secondFittest.genes[i] = t;

        }

    }

    //Mutation
    void mutation() {
        Random rn = new Random();

        //Selecting a random mutation point
        int mutationPoint = rn.nextInt(population.individuals[0].geneLength);

        //Change values at the mutation point
        if (fittest.genes[mutationPoint] == 0) {
            fittest.genes[mutationPoint] = 1;
        } else {
            fittest.genes[mutationPoint] = 0;
        }

        mutationPoint = rn.nextInt(population.individuals[0].geneLength);

        if (secondFittest.genes[mutationPoint] == 0) {
            secondFittest.genes[mutationPoint] = 1;
        } else {
            secondFittest.genes[mutationPoint] = 0;
        }
    }

    //Find fittest offspring
    Individual getFittestOffspring() {
        if (fittest.fitness > secondFittest.fitness) {
            return fittest;
        }
        return secondFittest;
    }


    void addFittestOffspring() {

        //Update fitness values of offspring
        fittest.calcFitness();
        secondFittest.calcFitness();

        //Index of least fit individual
        int leastFittestIndex = population.getLeastFittestIndex();

        //Replace the least fittest individual with the most fittest offspring
        population.individuals[leastFittestIndex] = getFittestOffspring();
    }

}