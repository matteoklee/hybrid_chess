package de.kleemann.hybrid_chess.core.game;

public class ChessGame {

    private int id;
    private GameState gameState;
    private Player[] players;
    private Player whoIsPlaying;
    private ChessBoard board;

    public ChessGame() {

    }

    public ChessGame(int id, Player[] players, Player whoIsPlaying, ChessBoard board) {
        this.id = id;
        this.gameState = GameState.RUNNING;
        this.players = players;
        this.whoIsPlaying = whoIsPlaying;
        this.board = board;
        this.getBoard().setChessGame(this);
    }

    public int getId() {
        return id;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWhoIsPlaying() {
        return whoIsPlaying;
    }

    public void setWhoIsPlaying(Player whoIsPlaying) {
        this.whoIsPlaying = whoIsPlaying;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "ChessGame{\nid: " + id + ",\ngamestate: " + gameState + ",\nplayer1: " + players[0].toString()
                + ",\nplayer2: " + players[1].toString() + ",\nwhoIsPlaying: " + whoIsPlaying.toString() + "\n}";
    }
}
