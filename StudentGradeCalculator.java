import java.util.Scanner;

public class StudentGradeCalculator {

    public static String getGrade(double average) {
        if (average >= 90) return "A";
        if (average >= 80) return "B";
        if (average >= 70) return "C";
        if (average >= 60) return "D";
        if (average >= 50) return "E";
        return "F";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many subjects? ");
        int count = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String[] subjects = new String[count];
        double[] scores = new double[count];
        double total = 0;

        for (int i = 0; i < count; i++) {
            System.out.print("Enter subject name: ");
            subjects[i] = scanner.nextLine();

            System.out.print("Enter marks for " + subjects[i] + " (out of 100): ");
            double mark = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            if (mark < 0 || mark > 100) {
                System.out.println("Marks must be between 0 and 100.");
                return;
            }

            scores[i] = mark;
            total += mark;
        }

        double average = total / count;
        String grade = getGrade(average);

        System.out.println("\n*** Result ***");
        for (int i = 0; i < count; i++) {
            System.out.println(subjects[i] + ": " + scores[i]);
        }

        System.out.println("Total: " + total + "/" + (count * 100));
        System.out.printf("Average: %.2f%%\n", average);
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
