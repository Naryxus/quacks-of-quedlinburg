package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class CommandLineInterface implements DecisionInterface {

    private final BufferedReader reader;

    public CommandLineInterface() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private AnswerType ask(String question) throws ParseException {
        System.out.println(question);

        String answer;
        try {
            answer = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return AnswerType.Abort;
        }

        return parseAnswerType(answer);
    }

    @Override
    public AnswerType drawNextIngredient() {
        try {
            return ask("Möchtest Du eine Zutat ziehen?");
        } catch (ParseException e) {
            e.printStackTrace();
            return takeVictoryPoints();
        }
    }

    @Override
    public AnswerType takeVictoryPoints() {
        try {
            return ask("Möchtest Du die Siegpunkte nehmen?");
        } catch (ParseException e) {
            e.printStackTrace();
            return takeVictoryPoints();
        }
    }

    private static AnswerType parseAnswerType(String answer) throws ParseException {
        if (answer.equalsIgnoreCase("stop") || answer.equalsIgnoreCase("abort")) return AnswerType.Abort;
        if (answer.equalsIgnoreCase("ja")) return AnswerType.Confirm;
        if (answer.equalsIgnoreCase("nein")) return AnswerType.Deny;

        throw new ParseException("Entschuldigung, ich konnte deine Eingabe nicht verstehen.", 0);
    }
}
