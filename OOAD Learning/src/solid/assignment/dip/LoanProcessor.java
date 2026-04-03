package solid.assignment.dip;

public class LoanProcessor {
    private CreditScoreService creditService;
    private DocumentVerificationService documentService;

    public LoanProcessor(CreditScoreService creditService,
                         DocumentVerificationService documentService) {
        this.creditService = creditService;
        this.documentService = documentService;
    }

    public void processLoan(String user) {
        int score = creditService.getCreditScore(user);
        boolean verified = documentService.verifyDocuments(user);

        if (score > 700 && verified)
            System.out.println("Loan Approved");
        else
            System.out.println("Loan Rejected");
    }
}
