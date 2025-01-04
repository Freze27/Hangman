import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ArrayList<String> HANGMAN_STAGES = new ArrayList<String>(Arrays.asList("""
               ______
               |    |
               |
               |
               |
               |
            ===========
            """, """
               ______
               |    |
               |    ◯
               |
               |
               |
            ===========
            """, """
               ______
               |    |
               |    ◯
               |    |
               |
               |
            ===========
            """, """
               ______
               |    |
               |    ◯
               |   /|
               |
               |
            ===========
            """, """
               ______
               |    |
               |    ◯
               |   /|\\
               |
               |
            ===========
            """, """
               ______
               |    |
               |    ◯
               |   /|\\
               |   /
               |
            ===========
            """, """
               ______
               |    |
               |    ◯
               |   /|\\
               |   / \\
               |
            ===========
            """));

    public static void main(String[] args) {
        boolean exitFlag = false;
        while (!exitFlag) {
            System.out.println("1 Начать игру.");
            System.out.println("2 Выход.");
            System.out.println("Выберите номер комманды:");
            String optionNumber = SCANNER.next();
            if (optionNumber.equals("1")) {
                RunGame();
            } else if (optionNumber.equals("2")) {
                exitFlag = true;
                System.out.println("Игра будет закрыта.");
                System.out.println("\uD83D\uDC4B");
            } else {
                System.out.println("Некоректный номер комманды. Попробуйте ещё раз.");
            }
        }
    }

    private static void RunGame() {
        int currentStage = 0;
        String word = GetRandomWord();
        String board = "-".repeat(word.length());
        ArrayList<Character> uncorrectedSymbols = new ArrayList<Character>();
        while (true) {
            char inputSymbol = InputSymbol();
            String resultBoard = OpenSymbolInBoard(word, board, inputSymbol);
            if (board.equals(resultBoard)) {
                currentStage++;
                uncorrectedSymbols.add(inputSymbol);
            }
            board = resultBoard;
            if (word.equals(board)) {
                System.out.println("Вы выиграли \uD83D\uDC4D");
                System.out.println(HANGMAN_STAGES.get(currentStage));
                System.out.println("Верное слово: " + word);
                System.out.println(" ");
                return;
            }
            if (currentStage == HANGMAN_STAGES.size() - 1) {
                System.out.println("Вы проиграли \uD83D\uDE2A");
                System.out.println(HANGMAN_STAGES.get(currentStage));
                System.out.println("Верное слово: " + word);
                System.out.println(" ");
                return;
            }
            OutputCurrentStage(board, currentStage, uncorrectedSymbols);
        }
    }

    private static char InputSymbol() {
        System.out.println("Введите букву:");
        String inputString = SCANNER.next();
        int asciiCode = (int) inputString.charAt(0);
        if (inputString.length() == 1 & asciiCode <= 1103 & asciiCode >= 1040) {
            inputString = inputString.toLowerCase();
            return inputString.charAt(0);
        } else {
            return InputSymbol();
        }
    }

    private static void OutputCurrentStage(String board, int currentStage, ArrayList<Character> uncorrectedSymbols) {
        System.out.println(HANGMAN_STAGES.get(currentStage));
        System.out.println("Неверные буквы: " + uncorrectedSymbols);
        System.out.println("Слово: " + board);
        System.out.println(" ");
    }

    private static String OpenSymbolInBoard(String word, String board, char symbol) {
        StringBuilder newBoard = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (symbol == word.charAt(i)) {
                newBoard.append(symbol);
            } else {
                newBoard.append(board.charAt(i));
            }
        }
        return newBoard.toString();
    }

    private static String GetRandomWord() {
        return "будка";
    }
}