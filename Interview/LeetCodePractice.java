import java.util.*;
import java.io.*;
import java.nio.file.*;

/**
 * This class manages a list of LeetCode problems, allowing users to add new problems
 * and randomly select a problem to practice. The problems are stored in a text file
 * for persistence across sessions.
*/
public class LeetCodePractice {
    // List to store problem numbers
    private static List<String> problems = new ArrayList<>();
    // File to store problem numbers
    private static final String PROBLEMS_FILE = "leetcode_problems.txt";

    /*
     * Load problems from the file into the list at startup.
    */
    private static void loadProblems() {
        try {
            if (Files.exists(Paths.get(PROBLEMS_FILE))) {
                // Read all lines from the file and add them to the list
                problems = new ArrayList<>(Files.readAllLines(Paths.get(PROBLEMS_FILE)));
            }
        } 
        catch (IOException e) {
            System.out.println("Error loading problems: " + e.getMessage());
        }
    }

    /*
     * After adding a new problem, save the current list of problems to the file.
    */
    private static void saveProblems() {
        try {
            // Write all problems to the file
            Files.write(Paths.get(PROBLEMS_FILE), problems);
        } 
        catch (IOException e) {
            System.out.println("Error saving problems: " + e.getMessage());
        }
    }

    /*
     * Add a new problem to the list if it doesn't already exist.
    */
    public static void addProblem(String newProblem){
        if (!problems.contains(newProblem)){
            problems.add(newProblem);
            saveProblems();  
            System.out.println("Leetcode problem number " + newProblem + " added successfully!");
        }
        else{
            System.out.println("Leetcode problem number " + newProblem + " is already in database!");
        }
    }

    /*
     * Randomly select a problem from the list to practice.
    */
    public static String practice(){
        if (problems.isEmpty()) {
            return "No problems available to practice!";
        }
        Random generator = new Random();
        String problemToSolve = problems.get(generator.nextInt(problems.size()));
        return problemToSolve;
    }

    public static void main(String[] args){
        loadProblems();  // Load existing problems at startup
        System.out.println("*********************\tHello! " + "There are currently " + problems.size() + " leetcode problems in the database.\t*********************");
        String prompt ="\nType:\n1 to add a new problem to the list of problems,\n2 to solve a problem,\n0 to exit.\n";
        System.out.println(prompt);
        Scanner input = new Scanner(System.in);
        try {
            while (true) {
                String choice = input.nextLine();

                if (choice.equals("1")){
                    System.out.print("\nEnter problem number:\t");
                    choice = input.nextLine();
                    addProblem(choice);
                    
                    System.out.println(prompt);
                }
                else if (choice.equals("2")){
                    String problem = practice();
                    if (problem.equals("\nNo problems available to practice!")) {
                        System.out.println(problem);
                    } else {
                        System.out.println("\nSolve leetcode problem number: " + problem + ". Good Luck!");
                    }
                    System.out.println(prompt);
                }
                else if (choice.equals("0")){
                    System.out.println("\nProgram stopped!");
                    break;
                }
                else {
                    System.out.println("\nWrong input!\nType:\n1 to add a new problem to the list of problems,\n2 to solve a problem,\n0 to exit.\n");
                }
            }
        } finally {
            input.close();
        }
    }

}
