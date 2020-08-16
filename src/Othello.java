/**
 * Othello.java
 * <p>
 * This class creates the GUI
 * <p>
 * 
 * @see OthelloInterface - only available from decompiled jar
 * @see PlayableMoves.java
 * @see Reversi.java
 */

import cs251.lab2.*;

public class Othello implements OthelloInterface  {

    //OthelloInterface.Piece values: 
    //          {"DARK","EMPTY","LIGHT"}
    //OthelloInterface.Result values: 
    //          {"DARK_WINS", "DRAW", "GAME_NOT_OVER", "LIGHT_WINS"}
    //set default player to PC and allow for changes later
    public static OthelloInterface.Piece p = OthelloInterface.Piece.valueOf("DARK");

    public static String board =     "........\n"
                                    +"........\n"
                                    +"........\n"
                                    +"...WB...\n"
                                    +"...BW...\n"
                                    +"........\n"
                                    +"........\n"
                                    +"........\n";
    public static Board b = new Board();

    public void initializeGame(){
        return;
    }

    public void setComputerPlayer(java.lang.String opponent){
        return;
    }

    public void showGUI(OthelloInterface game){
        return;
    }

    public String getBoardString(){
        String bd = board;
        return bd;
    }

    public OthelloInterface.Piece getCurrentTurn(){
        OthelloInterface.Piece turn = p;
        return turn;
    }

    public int getSize(){
        return DEFAULT_SIZE;
    }
    
    public int setBoardSize(){
        int bSize = 0;
        bSize = (getSize() * getSize()) + getSize();
        return bSize;
    }

    //move a Black piece according to result from vsComputer
    public char moveBlack(char piece){
        p = OthelloInterface.Piece.valueOf("LIGHT");
        String wboard = "";
        wboard = Reversi.twoPlayers(b, 0, 1, piece, 'W');
        PlayableMoves.boardMoves = PlayableMoves.boardWMoves;
        if(PlayableMoves.isInvalidMove){
            PlayableMoves.boardMoves = PlayableMoves.boardBMoves;
            piece = 'W';
            p = OthelloInterface.Piece.valueOf("DARK");
        }
        return piece;
    }

    // this is our infinite while loop
    //because we are always checking for a click event
    //move a pice with a call to twoPlayers() in Reversi
    //return the current result of the game
    //Note how the OthelloInterface.Result is always set to continue no matter the result
    //this was due to an error in the jar where the new game feature
    //still needed to be implemented
    //another example of code creep
    public OthelloInterface.Result handleClickAt(int row, int col){
        char piece;

        if(p.toString().equals("LIGHT")){
            p = OthelloInterface.Piece.valueOf("DARK");
            piece = 'W';
            String bboard = "";
            bboard = Reversi.twoPlayers(b, row, col, piece, 'B');
            PlayableMoves.boardMoves = PlayableMoves.boardBMoves;
            if(PlayableMoves.isInvalidMove){
                PlayableMoves.boardMoves = PlayableMoves.boardWMoves;
                piece = 'B';
                p = OthelloInterface.Piece.valueOf("LIGHT");
            }
            else{
                piece = moveBlack('B');
            }
        }

        if(PlayableMoves.result == 0){
            System.out.println("Draw between both\n New Game\n");
        }
        else if(PlayableMoves.result == -1){
            System.out.println("Black wins\n");
        }
        else if(PlayableMoves.result == 1){
            System.out.println("White wins");
        }
        board = PlayableMoves.playableBoard;
        OthelloInterface.Result r = OthelloInterface.Result.valueOf("GAME_NOT_OVER");
        return r;
    }

    //create the GUI and make black move first if computer is selected
    //if you don't want to play the PC then the logic
    //would need to be restructured in the handleClickAt()
    public static void main(String[] args){

        Othello game = new Othello();
        if(args.length > 0){
            game.setComputerPlayer(args[0]);//choose either - NONE or COMPUTER
            PlayableMoves.playPC = true;
        }
        OthelloGUI.showGUI(game);
        String something = Reversi.twoPlayers(b, 0, 1, 'B', 'W');
        board = something;
        p = OthelloInterface.Piece.valueOf("LIGHT");
    }
}