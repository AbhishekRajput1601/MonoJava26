package solid.assignment.dip;

public class LoanApplication {
    public static void main(String[] args) {

        CreditScoreService credit = new CreditScoreAPI();
        DocumentVerificationService doc = new DocumentVerifier();

        LoanProcessor processor = new LoanProcessor(credit, doc);

        System.out.println("\n==================================");
        System.out.println("       LOAN PROCESSING SYSTEM");
        System.out.println("==================================");

        while (true) {
            System.out.println("\n1. Apply for Loan");
            System.out.println("2. Exit");

            int choice = Integer.parseInt(InputValidator.getInput("Enter choice: "));

            switch (choice) {
                case 1:
                    String user = InputValidator.getInput("Enter applicant name: ");

                    System.out.println("\nProcessing loan for: " + user);
                    System.out.println("--------------------------------");

                    processor.processLoan(user);

                    System.out.println("--------------------------------");
                    break;

                case 2:
                    System.out.println("\n Exiting Loan System...");
                    return;

                default:
                    System.out.println(" Invalid choice!");
            }
        }
    }
}
