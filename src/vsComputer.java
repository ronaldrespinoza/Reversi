/**
 * vsComputer.java
 * <p>
 * This class find the last set in all Available black moves
 */
public class vsComputer{

    //for all black moves return the last black point available
    //sure we could be clever and return specific points in the set
    //based on difficulty intially selected
    //but this method is another good example of code creep
    public static Point blackMove(){
    Point move = new Point(0, 0);
    //this is cool but we only get the last
    //placeable position in the set
        for(Point pcMoves : PlayableMoves.blackPL){
            move = pcMoves;
        }
        return move;
    }
}