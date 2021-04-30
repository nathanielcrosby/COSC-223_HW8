import java.util.Random;

public class Simulator {
    private int jobs;
    private double p;
    private double q;
    private int nextArr;
    private int CurrTime;
    private double totalJobs = 0;
    private double totalResponseTimes = 0;
    private int buffer;

    public Simulator() {

    }

    public double[] Simulate(int jobs, int buffer, double p, double q) {
        this.jobs = jobs;
        this.buffer = buffer;
        this.p = p;
        this.q = q;
        CurrTime = 0;
        totalJobs = 0;
        totalResponseTimes = 0;

        Server server = new Server();
        nextArr = RandomGeometric(p);

        int i = 0;
        int departures = 0;
        while (i <= jobs) {
            if (nextArr < server.getNextDepartureTime()) {
                CurrTime = nextArr;
                if((i - this.buffer) > 0) {
                    totalJobs += server.getNumJobs();
                }

                server.addJob(new Job(CurrTime, RandomGeometric(q)));
                i++;
                nextArr = CurrTime + RandomGeometric(p);

            }
            else {
                CurrTime = server.getNextDepartureTime();

                if((i - this.buffer) > 0) {
                    departures++;
                    totalResponseTimes += (CurrTime - server.getCurrentJob().getArrivalTime());

                }
                server.departure();
            }

        }

        double[] vals = {(totalJobs / (jobs - buffer)), totalResponseTimes / departures};
        return vals;
    }

    private static int RandomGeometric(double p) {
        int count = 0;
        double flip = 1;
        Random rand = new Random();
        while (flip > p) {
            flip = rand.nextDouble();
            count++;
        }
        return count;
    }

}
