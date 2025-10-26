import java.util.*;

public class ChatBot {
    private static Map<String, String> knowledgeBase = new HashMap<>();

    static {
        knowledgeBase.put("hi", "Hello! How can I help you?");
        knowledgeBase.put("hello", "Hi there! What can I do for you?");
        knowledgeBase.put("how are you", "I'm just a program, but I'm doing great!");
        knowledgeBase.put("what is your name", "I'm your Java AI Chatbot!");
        knowledgeBase.put("bye", "Goodbye! Have a nice day!");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("AI Chatbot: Hello! Type 'bye' to exit.");

        while (true) {
            System.out.print("You: ");
            String userInput = sc.nextLine().toLowerCase();

            // Simple tokenization (simulate NLP)
            String[] tokens = userInput.split(" ");

            // Find best response
            String response = getResponse(userInput);
            System.out.println("AI Chatbot: " + response);

            if (userInput.contains("bye"))
                break;
        }
        sc.close();
    }

    private static String getResponse(String input) {
        for (String key : knowledgeBase.keySet()) {
            if (input.contains(key)) {
                return knowledgeBase.get(key);
            }
        }
        return "I'm not sure I understand. Can you rephrase?";
    }
}