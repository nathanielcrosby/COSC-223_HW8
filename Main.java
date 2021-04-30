import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Simulator simulator = new Simulator();
        //double vals[] = simulator.Simulate(10000000, 2000, 0.4,0.5);
        //System.out.println(vals[0]);
        //System.out.println(vals[1]);
        run("QueueData");

    }


    public static void run(String filename) {
        Simulator simulator = new Simulator();

        double vals[];
        try (PrintWriter writer = new PrintWriter(new File(filename+".csv"))) {
            StringBuilder sb = new StringBuilder();
            sb.append("p,E[N],E[T]\n");

            for (double i = 0.01; i < 0.5; i += 0.01) {
                vals = simulator.Simulate(1000000, 2000, i,0.5);
                sb.append(Double.toString(i) + "," + Double.toString(vals[0]) + "," + Double.toString(vals[1]) + "\n");
            }
            writer.write(sb.toString());
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

}
