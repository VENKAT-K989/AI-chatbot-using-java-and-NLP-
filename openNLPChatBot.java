import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;

public class OpenNLPChatBot {

    private static Map<String, String> knowledgeBase = new HashMap<>();

    static {
        knowledgeBase.put("greeting", "Hello! How can I help you?");
        knowledgeBase.put("name_query", "I'm your Java AI Chatbot!");
        knowledgeBase.put("farewell", "Goodbye! Have a nice day!");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("AI Chatbot: Hello! Type 'bye' to exit.");

        // Load POS Tagger model
        POSModel model = null;
        try {
            model = new POSModel(new FileInputStream("en-pos-maxent.bin"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        POSTaggerME tagger = new POSTaggerME(model);

        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;

        while (true) {
            System.out.print("You: ");
            String userInput = sc.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("AI Chatbot: " + knowledgeBase.get("farewell"));
                break;
            }

            // Tokenization
            String[] tokens = tokenizer.tokenize(userInput);
            // POS Tagging
            String[] posTags = tagger.tag(tokens);

            // Basic intent recognition
            String intent = recognizeIntent(tokens, posTags);

            // Respond
            System.out.println("AI Chatbot: " + knowledgeBase.getOrDefault(intent, 
                "I'm not sure I understand. Can you rephrase?"));
        }
        sc.close();
    }

    // Simple intent recognition based on keywords
    private static String recognizeIntent(String[] tokens, String[] posTags) {
        for (String token : tokens) {
            token = token.toLowerCase();
            if (token.equals("hi") || token.equals("hello") || token.equals("hey")) {
                return "greeting";
            }
            if (token.contains("name")) {
                return "name_query";
            }
        }
        return "unknown";
    }
}
