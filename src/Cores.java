import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/*Example code found:
* https://www.baeldung.com/java-knapsack
* https://www.educative.io/edpresso/coin-change-problem-1-in-javafinding-the-minimum-number-of-coins
* It has been revised to meet the expectations of this project
*/

public class Cores {

    int c;
    float v;

    public Cores(int c, float v){
        this.c = c;
        this.v = v;
    }

    private static String FILENAME=null;
    //available cores
    static int my_cores[] = {1, 2, 7, 11};

    //calculating the minimum number of VMs for a client, like the minimum change problem
    public static int vmsForClient(int[] cores, int sum){

        int totalCores = cores.length;

        //array for the solutions
        double[][] arr = new double[totalCores + 1][sum + 1];

        // Initialising first row with +infinity
        for(int j = 0; j <= sum; j++){
            arr[0][j] = Double.POSITIVE_INFINITY;
        }

        // Initialising first column with 0
        for(int i = 1; i <= totalCores; i++){
            arr[i][0] = 0;
        }

        // Implementing the recursive solution
        for(int i = 1; i <= totalCores; i++){
            for(int j = 1; j <= sum; j++){
                if(cores[i - 1] <= j)
                    arr[i][j] = minimum(1 + arr[i][j - cores[i - 1]], arr[i - 1][j]);
                else
                    arr[i][j] = arr[i - 1][j];
            }
        }

        return (int)arr[totalCores][sum];
    }

    //function to return the minimum of a number
    public static double minimum(double a, double b){
        if(a <= b){
            return a;
        }
        else{
            return b;
        }
    }


    public static float knapsackDP(int[] w, float[] v, int n, int W) {
        if (n <= 0 || W <= 0) {
            return 0;
        }

        float[][] m = new float[n + 1][W + 1];
        for (int j = 0; j <= W; j++) {
            m[0][j] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (w[i - 1] > j) {
                    m[i][j] = m[i - 1][j];
                } else {
                    m[i][j] = Math.max(
                            m[i - 1][j],
                            m[i - 1][j - w[i - 1]] + v[i - 1]);
                }
            }
        }
        return m[n][W];
    }


    //calculating the lines of the input file, as this is the number of the of the total cores - 1.

    public static int calculateLines(String filename) throws Exception{
        Path path = Paths.get(filename);
        long lineCount = Files.lines(path).count();
        int lines = (int) lineCount;
        return lines;
    }


     //Loading hte core data from the file.
    public static ArrayList<Cores> loadCoresData(String filename) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(filename));
        int lines = calculateLines(filename);
        //System.out.println("Lines "+lines);
        ArrayList<Cores> cores_data = new ArrayList<>();
        String[] line = br.readLine().split(" ");

        for (int i=0; i<lines-1; i++) {
            //System.out.println("i = "+i);
            //if(i>0) {
                //System.out.print("i = "+i);
                 line = br.readLine().split(" ");
                //System.out.println("Line "+line);
                int x = Integer.parseInt((line[0]));
                //System.out.print(" x = "+x);
                float y = Float.parseFloat((line[1]));
                //System.out.println(" y = "+y);
                Cores c = new Cores(x, y);
                cores_data.add(i, c);
           // }
        }

        br.close();
        return cores_data;
    }


    //Returning the total number of cores, which is at the first line
    public static int loadTotalCores(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String[] line = br.readLine().split(" ");
        int num = Integer.parseInt((line[0]));
        return num;
    }



    public static void main(String args[]) throws Exception {

        //if a filename is not provided we use one of our own
        if (args.length <= 0) {
            FILENAME = "sample.txt";
        } else {
            FILENAME = args[0];
        }

        //loading data from file
        int num_of_cores = loadTotalCores(FILENAME);
        ArrayList<Cores> data = loadCoresData(FILENAME);

        int [] weights = new int[data.size()];
        float [] values = new float[data.size()];

        for (int i=0; i<data.size(); i++){
            weights[i] = data.get(i).c;
        }

        for (int i=0; i<data.size(); i++){
            values[i] = data.get(i).c*data.get(i).v;
        }

        for(int i=0;i<data.size();i++){
            System.out.println("Client "+(i+1)+": "+ vmsForClient(my_cores, data.get(i).c)+" VMs");
        }

        float sol = knapsackDP(weights,values,data.size(),num_of_cores);
        System.out.println("Total amount: "+sol);

    }

}
