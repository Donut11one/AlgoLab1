import java.io.FileNotFoundException;

public class driver {
    public static void main(String[] args) throws FileNotFoundException {
        JobAssignmentFinder jobs = new JobAssignmentFinder();
        jobs.readDataFile("./data0.txt");
        System.out.println(jobs.getInputSize());
        System.out.println(jobs.benefitMatrixToString());
        System.out.println(jobs.getMaxAssignment());
        System.out.println(jobs.getMaxAssignmentTotalValue());
    }
}