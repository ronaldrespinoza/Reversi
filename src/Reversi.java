/**
 * Reversi.java
 * <p>
 * This class is a container for the manipulation of the board object
 * The main method called in this class is twoPlayers
 * @see Othello.java
 * <p>
 */


import java.util.HashSet;
import java.util.Set;

public class Reversi{

    //keep in mind the Board b object is the string we pass back to Othello
    //and if we don't place the move then it wont update past its previous state
    public static String twoPlayers(Board b, int row, int col, char plr, char opp){
        Point move = new Point(0, 0);
        int result = 0;
        String displayB = "";
        String input = "";
        PlayableMoves.playableBoard = Board.displayBoard(b);
        HashSet<Point> blackPlaceableLocations = b.getPlaceableLocations('B', 'W');
        PlayableMoves.blackPL = blackPlaceableLocations;
        HashSet<Point> whitePlaceableLocations = b.getPlaceableLocations('W', 'B');
        PlayableMoves.whitePL = whitePlaceableLocations;
        PlayableMoves.boardWMoves = b.showPlaceableLocations(whitePlaceableLocations, 'W', 'B');
        PlayableMoves.boardBMoves = b.showPlaceableLocations(blackPlaceableLocations, 'B', 'W');
        PlayableMoves.result = getResult(whitePlaceableLocations, blackPlaceableLocations, b);
        PlayableMoves.playableBoard = Board.displayBoard(b);
        if(PlayableMoves.playPC && plr == 'B'){
            move = vsComputer.blackMove();
        }
        else{
            move = new Point(row, col);
        }
        if(!invalidMove(whitePlaceableLocations, blackPlaceableLocations, move, plr)){
            PlayableMoves.isInvalidMove = false;
            b.placeMove(move, plr, opp);
            b.updateScores();
            PlayableMoves.playableBoard = Board.displayBoard(b);
        }
        else{
            PlayableMoves.isInvalidMove = true;
        }
        PlayableMoves.result = getResult(whitePlaceableLocations, blackPlaceableLocations, b);
        displayB = "";
        displayB = PlayableMoves.playableBoard;
        moveExists();
        System.out.println("Black: "+b.BScore+" White: "+b.WScore);
        return displayB;
    }

    //Are there playableMoves for
    //black or white
    //if no moves available set flag
    //@see twoPlayers()
    public static void moveExists(){
        if(PlayableMoves.whitePL.isEmpty()){
            PlayableMoves.wHasNoMoves = true;
        }
        if(PlayableMoves.blackPL.isEmpty()){
            PlayableMoves.bHasNoMoves = true;
        }
    }

    //check if the move was invalid
    //if move was valid return false
    //@see twoPlayers()
    public static Boolean invalidMove(HashSet<Point> whitePlaceableLocations, HashSet<Point> blackPlaceableLocations, Point move, char plr){
        boolean isMove = false;
        if(plr == 'B'){
            if(!blackPlaceableLocations.contains(move)){
                PlayableMoves.gameStatus = "Black I";
                isMove = true;
                return isMove;
            }
            else{
                isMove = false;
                return isMove;
            }
        }
        if(plr == 'W'){
            if(!whitePlaceableLocations.contains(move)){
                PlayableMoves.gameStatus = "White I";
                isMove = true;
                return isMove;
            }
            else{
                isMove = false;
                return isMove;
            }
        }
        return isMove;
    }


    //get the current result of game
    //@see twoPlayers()
    public static int getResult(Set<Point> whitePlaceableLocations, Set<Point> blackPlaceableLocations, Board b){
        int result = 0;
        result = b.gameResult(whitePlaceableLocations, blackPlaceableLocations);
        return result;
    }
}