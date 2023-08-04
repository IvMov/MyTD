package core;

public class GameRunner {

    public static void main(String[] args) {
        System.out.println("Start of game");
        Game game = new Game();
        game.getGameScreen().initInputs();
        game.startGameThread();
    }
}
