import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GradeAnalyzer {
    private static int invalidLinesSkipped = 0;

    public static void main(String[] args) {
        ArrayList<Integer> scores = readScores("scores.txt");
        double average = calculateAverage(scores);

        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;

        for (int score : scores) {
            if (score > highest) {
                highest = score;
            }
            if (score < lowest) {
                lowest = score;
            }
        }

        if (scores.isEmpty()) {
            System.out.println("No valid scores were found.");
        }

        writeReport(scores, average, highest, lowest, "report.txt");
    }

    // Returns a list of valid scores read from the file
    public static ArrayList<Integer> readScores(String filename) {
        ArrayList<Integer> scores = new ArrayList<>();
        invalidLinesSkipped = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    invalidLinesSkipped++;
                    System.out.println("Warning: blank line skipped.");
                    continue;
                }

                try {
                    int score = Integer.parseInt(line);

                    if (score < 0 || score > 100) {
                        invalidLinesSkipped++;
                        System.out.println("Warning: score out of range skipped: " + line);
                    } else {
                        scores.add(score);
                    }
                } catch (NumberFormatException e) {
                    invalidLinesSkipped++;
                    System.out.println("Warning: invalid score skipped: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }

        return scores;
    }

    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {
        if (scores.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        for (int score : scores) {
            total += score;
        }

        return total / scores.size();
    }

    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
                                   double avg, int high, int low,
                                   String outputFile) {
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;

        for (int score : scores) {
            if (score >= 90) {
                countA++;
            } else if (score >= 80) {
                countB++;
            } else if (score >= 70) {
                countC++;
            } else if (score >= 60) {
                countD++;
            } else {
                countF++;
            }
        }

        String highestText = scores.isEmpty() ? "N/A" : Integer.toString(high);
        String lowestText = scores.isEmpty() ? "N/A" : Integer.toString(low);
        String report = String.format(
                "=== Grade Analysis Report ===%n"
                        + "Total scores processed:  %d%n"
                        + "Invalid lines skipped:    %d%n%n"
                        + "Average score:   %.2f%n"
                        + "Highest score:   %s%n"
                        + "Lowest score:    %s%n%n"
                        + "Grade distribution:%n"
                        + "  A (90-100):    %d%n"
                        + "  B (80-89):     %d%n"
                        + "  C (70-79):     %d%n"
                        + "  D (60-69):     %d%n"
                        + "  F (below 60):  %d%n",
                scores.size(), invalidLinesSkipped, avg, highestText, lowestText,
                countA, countB, countC, countD, countF);

        System.out.print(report);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(report);
        } catch (IOException e) {
            System.out.println("Could not write report: " + e.getMessage());
        }
    }
}