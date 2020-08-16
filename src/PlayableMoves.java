/**
 * PlayableMoves.java 
 * <p>
 * this is the properties class for all PlayableMoves that could exist in the game
 * 
 * @see vsComputer.java
 * @see Board.java
 * @see Reversi.java
 * @see Othello.java
 */

import java.util.HashSet;
import java.util.Set;

public class PlayableMoves{

    public static String gameStatus = "";
    public static String playableBoard = "";
    public static String boardWMoves = "";
    public static HashSet<Point> whitePL = null;
    public static String boardBMoves = "";
    public static HashSet<Point> blackPL = null;
    public static String boardMoves = "";
    public static String boardPrompt = "";
    public static Boolean isInvalidMove = false;
    public static int result = 3;
    public static Boolean playPC = false;
    public static Boolean wHasNoMoves = false;
    public static Boolean bHasNoMoves = false;

    //it was either reference this class directly
    //or create getter and setter methods for each property
    PlayableMoves(String gameStatus, String playableBoard, String boardWMoves, HashSet<Point> whitePL, String boardBMoves, HashSet<Point> blackPL, String boardMoves, Boolean isInvalidMove, int result, Boolean playPC, Boolean wHasNoMoves, Boolean bHasNoMoves, String boardPrompt){
        this.gameStatus = gameStatus;
        this.playableBoard = playableBoard;
        this.boardWMoves = boardWMoves;
        this.whitePL = whitePL;
        this.boardBMoves = boardBMoves;
        this.blackPL = blackPL;
        this.boardMoves = boardMoves;
        this.boardPrompt = boardPrompt;
        this.isInvalidMove = isInvalidMove;
        this.result = result;
        this.playPC = playPC;
        this.wHasNoMoves = wHasNoMoves;
        this.bHasNoMoves = bHasNoMoves;
    }
}
