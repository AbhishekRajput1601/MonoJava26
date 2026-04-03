package solid.assignment.ocp;

public class LMSApplication {
    public static void main(String[] args) {

        ContentRenderer renderer = new ContentRenderer();

        System.out.println("\n==================================");
        System.out.println("    LMS CONTENT RENDER SYSTEM");
        System.out.println("==================================");

        while (true) {
            System.out.println("\nChoose Content Type:");
            System.out.println("1.  Video");
            System.out.println("2.  Article");
            System.out.println("3.  Quiz");
            System.out.println("4.  Podcast");
            System.out.println("5.  Exit");

            int choice = InputValidator.getChoice("\nEnter your choice: ");

            Content content;

            switch (choice) {
                case 1:
                    content = new VideoContent();
                    break;
                case 2:
                    content = new ArticleContent();
                    break;
                case 3:
                    content = new QuizContent();
                    break;
                case 4:
                    content = new PodcastContent();
                    break;
                case 5:
                    System.out.println("\n Exiting LMS System...");
                    return;
                default:
                    System.out.println(" Invalid choice! Try again.");
                    continue;
            }

            System.out.println("\n--- Rendering Output ---");
            renderer.render(content);
            System.out.println("------------------------");
        }
    }
}
