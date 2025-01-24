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
            thisList.add(index);

        }
        for(int i = 0 ; i < length; i ++){
            thisList.set(i, benefitMatrix[i][thisList.get(i)]);
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
        /**
     * Recursive decrease-and-conquer algorithm to generate a list of all
     * permutations of the numbers 0..N-1. This follows the "decrease by 1" pattern
     * of decrease and conquer algorithms.
     * 
     * This method returns an ArrayList of ArrayLists. One permutation is an
     * ArrayList containing 0,1,2,...,N-1 in some order. The final result is an
     * ArrayList containing N! of those permutations.
     * 
     * @param N
     * @return
     */
    private ArrayList<ArrayList<Integer>> getPermutations(int N) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();

        /**
         * This isn't a "base case", it's a "null case". This function does not call
         * itself with an argument of zero, but we can't prevent another caller from
         * doing so. It's a weird result, though. The list of permutations has one
         * permutation, but the one permutation is empty (0 elements).
         */
        if (N == 0) {
            ArrayList<Integer> emptyList = new ArrayList<Integer>();
            results.add(emptyList);

        } else if (N == 1) {
            /**
             * Now THIS is the base case. Create an ArrayList with a single integer, and add
             * it to the results list.
             */
            ArrayList<Integer> singleton = new ArrayList<Integer>();
            singleton.add(0);
            results.add(singleton);

        } else {
            /**
             * And: the main part. First a recursive call (this is a decrease and conquer
             * algorithm) to get all the permutations of length N-1.
             */
            ArrayList<ArrayList<Integer>> smallList = getPermutations(N - 1);

            /**
             * Iterate over the list of smaller permutations and insert the value 'N-1' into
             * every permutation in every possible position, adding each new permutation to 
             * the big list of permutations.
             */
            for (ArrayList<Integer> perm : smallList) {

                /**
                 * Add 'N-1' -- the biggest number in the new permutation -- at each of the
                 * positions from 0..N-1.
                 */
                for (int i = 0; i < perm.size(); i++) {
                    ArrayList<Integer> newPerm = (ArrayList<Integer>) perm.clone();
                    newPerm.add(i, N - 1);
                    results.add(newPerm);
                }

                /**
                 * Add 'N-1' at the end (i.e. at position "size").
                 */
                ArrayList<Integer> newPerm = (ArrayList<Integer>) perm.clone();
                newPerm.add(N - 1);
                results.add(newPerm);

            }

        }

        /**
         * Nothing left to do except:
         */
        return results;
    }

}