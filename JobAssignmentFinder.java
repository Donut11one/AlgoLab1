import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JobAssignmentFinder {

    private int[][] benefitMatrix;
    private int length;
    private String matrixString;
    private final ArrayList<Integer> thisList = new ArrayList<>();

    public JobAssignmentFinder(){
        this.length = -1;
        this.matrixString = "";
    }

    public void readDataFile(String s) throws FileNotFoundException{
        File file = new File (s);
        Scanner scan = new Scanner(file);
        length = scan.nextInt();
        benefitMatrix = new int[length][length];

        for(int i = 0; i < length; i++){
            for(int j = 0 ; j <length; j++){
                benefitMatrix[i][j] = scan.nextInt();
            }
        }

        scan.close(); 

    }

    public int getInputSize(){
        return length;
    }

    public int[][] getBenefitMatrix(){
        return benefitMatrix;
    }

    public String benefitMatrixToString(){

        for(int i = 0 ; i < length; i++){
            matrixString +="[";
            for(int j = 0 ; j < length; j++){
                if(j != length - 1){
                    matrixString += benefitMatrix[i][j] + ",";
                }
                else{
                    matrixString += benefitMatrix[i][j];
                }
            }
            matrixString += "]\n";

        }
        return matrixString;
    }

    public ArrayList<Integer>getMaxAssignment(){
        for(int i = 0; i < length; i++){
            int max = benefitMatrix[i][0];
            int index = 0;
            for(int j = 1 ; j <length; j++){
                if(max < benefitMatrix[i][j]){
                    if(!thisList.contains(j)){
                        max = benefitMatrix[i][j];
                        index = j;
                    }
                }
            }
            thisList.add(max);

        }

        return thisList;
    }

    public int getMaxAssignmentTotalValue(){
        int totalvalue = 0;
        for(int i = 0 ; i < thisList.size() ; i ++){
            totalvalue += thisList.get(i);
        }
        return totalvalue;
    }
    
    public int getBenefit(int x,int y){
        return benefitMatrix[x][y];
    }


}