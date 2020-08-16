/**
 * Board.java
 * <p>
 * This class is a container for the the board object
 * <p>
 * The methods contained in this class provide properties for the current state of the board within a move
 * @see Reversi.java
 * <p>
*/

import java.util.HashSet;
import java.util.Set;

class Board{ 

    private final char[][] board;
    int WScore, BScore, remaining;

    //creating the Board object
    public Board(){ 
        board = new char[][]{
            {'.','.','.','.','.','.','.','.',},
            {'.','.','.','.','.','.','.','.',},
            {'.','.','.','.','.','.','.','.',},
            {'.','.','.','W','B','.','.','.',},
            {'.','.','.','B','W','.','.','.',},
            {'.','.','.','.','.','.','.','.',},
            {'.','.','.','.','.','.','.','.',},
            {'.','.','.','.','.','.','.','.',},
        };
    }

    //create the container for the 
    //hashset of available points
    public HashSet<Point> getPoint(){
        HashSet<Point> placeablePositions = new HashSet<>();
        return placeablePositions;
    }

    //iterating through playable positions row by collumn
    //when a playable position is found then add a point
    public void findPlaceableLocations(char player, char opponent, HashSet<Point> placeablePositions){ 
        for(int i=0;i<8;++i){
            for(int j=0;j<8;++j){
                if(board[i][j] == opponent){
                    int I = i, J = j;  
                    if(i-1>=0 && j-1>=0 && board[i-1][j-1] == '.'){ 
                        i = i+1; j = j+1;
                        while(i<7 && j<7 && board[i][j] == opponent){i++;j++;}
                        if(i<=7 && j<=7 && board[i][j] == player) placeablePositions.add(new Point(I-1, J-1));
                    } 
                    i=I;j=J;
                    if(i-1>=0 && board[i-1][j] == '.'){
                        i = i+1;
                        while(i<7 && board[i][j] == opponent) i++;
                        if(i<=7 && board[i][j] == player) placeablePositions.add(new Point(I-1, J));
                    } 
                    i=I;
                    if(i-1>=0 && j+1<=7 && board[i-1][j+1] == '.'){
                        i = i+1; j = j-1;
                        while(i<7 && j>0 && board[i][j] == opponent){i++;j--;}
                        if(i<=7 && j>=0 && board[i][j] == player) placeablePositions.add(new Point(I-1, J+1));
                    }  
                    i=I;j=J;
                    if(j-1>=0 && board[i][j-1] == '.'){
                        j = j+1;
                        while(j<7 && board[i][j] == opponent)j++;
                        if(j<=7 && board[i][j] == player) placeablePositions.add(new Point(I, J-1));
                    }
                    j=J;
                    if(j+1<=7 && board[i][j+1] == '.'){
                        j=j-1;
                        while(j>0 && board[i][j] == opponent)j--;
                        if(j>=0 && board[i][j] == player) placeablePositions.add(new Point(I, J+1));
                    }
                    j=J;
                    if(i+1<=7 && j-1>=0 && board[i+1][j-1] == '.'){
                        i=i-1;j=j+1;
                        while(i>0 && j<7 && board[i][j] == opponent){i--;j++;}
                        if(i>=0 && j<=7 && board[i][j] == player) placeablePositions.add(new Point(I+1, J-1));
                    }
                    i=I;j=J;
                    if(i+1 <= 7 && board[i+1][j] == '.'){
                        i=i-1;
                        while(i>0 && board[i][j] == opponent) i--;
                        if(i>=0 && board[i][j] == player) placeablePositions.add(new Point(I+1, J));
                    }
                    i=I;
                    if(i+1 <= 7 && j+1 <=7 && board[i+1][j+1] == '.'){
                        i=i-1;j=j-1;
                        while(i>0 && j>0 && board[i][j] == opponent){i--;j--;}
                        if(i>=0 && j>=0 && board[i][j] == player)placeablePositions.add(new Point(I+1, J+1));
                    }
                    i=I;j=J;
                    }
                } 
        } 
    } 

    //return a proper string to display the board
    public static String displayBoard(Board b){  

        String displayB = "";
        
        for(int i=0;i<8; i++){
            for(int j=0;j<8; j++){
                displayB = displayB + (new String(b.board[i][j]+""));
            }
            displayB = displayB + "\n";
        }
        return displayB;
    }

    //this method is an example of code creep
    //crazy thing is sometimes we want
    //to know if the board can be auto
    //completed based on if every move
    //played would result in white or
    //black winning
    //@see Reversi.java - getResult()
    public int gameResult(Set<Point> whitePlaceableLocations, Set<Point> blackPlaceableLocations){ 
        updateScores();
        if(whitePlaceableLocations.isEmpty() && blackPlaceableLocations.isEmpty()){
            if(WScore > BScore) return 1;
            //white wins
            else if(BScore > WScore) return -1;
            //black wins
            else return 0; //Draw
        }
        if(remaining == 0 || remaining == 1){
            if(WScore > BScore) return 1;
            //white wins
            else if(BScore > WScore) return -1;
            //black wins
            else return 0; //Draw
        }
        if(WScore==0 || BScore == 0){
            if(WScore > 0) return 1;
            //white wins
            else if(BScore > 0) return -1; 
            //black wins
        }
        return -2;
        //our continue state
    } 

    // get placeablePositions
    //@see findPlaceableLocations()
    public HashSet<Point> getPlaceableLocations(char player, char opponent){ 
        HashSet<Point> placeablePositions = new HashSet<>();
        findPlaceableLocations(player, opponent, placeablePositions);
        return placeablePositions;
    }

    //create string with * as a playable position
    //@see Reversi.java - twoPlayers()
    public String showPlaceableLocations(HashSet<Point> locations, char player, char opponent){
        String displayB = "";
        for(Point p:locations)
            board[p.x][p.y]='*';
        displayB = displayBoard(this);
        for(Point p:locations)
            board[p.x][p.y]='.';
        return displayB;
    }

    //play the move for current player
    //replace * with W or B
    //@see Reversi.java twoPlayers()
    public void placeMove(Point p, char player, char opponent){
        int i = p.x, j = p.y;
        board[i][j] = player; 
        int I = i, J = j;  
        
        if(i-1>=0 && j-1>=0 && board[i-1][j-1] == opponent){ 
            i = i-1; j = j-1;
            while(i>0 && j>0 && board[i][j] == opponent){i--;j--;}
            if(i>=0 && j>=0 && board[i][j] == player) {while(i!=I-1 && j!=J-1)board[++i][++j]=player;}
        } 
        i=I;j=J; 
        if(i-1>=0 && board[i-1][j] == opponent){
            i = i-1;
            while(i>0 && board[i][j] == opponent) i--;
            if(i>=0 && board[i][j] == player) {while(i!=I-1)board[++i][j]=player;}
        } 
        i=I; 
        if(i-1>=0 && j+1<=7 && board[i-1][j+1] == opponent){
            i = i-1; j = j+1;
            while(i>0 && j<7 && board[i][j] == opponent){i--;j++;}
            if(i>=0 && j<=7 && board[i][j] == player) {while(i!=I-1 && j!=J+1)board[++i][--j] = player;}
        }   
        i=I;j=J;
        if(j-1>=0 && board[i][j-1] == opponent){
            j = j-1;
            while(j>0 && board[i][j] == opponent)j--;
            if(j>=0 && board[i][j] == player) {while(j!=J-1)board[i][++j] = player;}
        }
        j=J; 
        if(j+1<=7 && board[i][j+1] == opponent){
            j=j+1;
            while(j<7 && board[i][j] == opponent)j++;
            if(j<=7 && board[i][j] == player) {while(j!=J+1)board[i][--j] = player;}
        }
        j=J; 
        if(i+1<=7 && j-1>=0 && board[i+1][j-1] == opponent){ 
            i=i+1;j=j-1;
            while(i<7 && j>0 && board[i][j] == opponent){i++;j--;}
            if(i<=7 && j>=0 && board[i][j] == player) {while(i!=I+1 && j!=J-1)board[--i][++j] = player;}
        }
        i=I;j=J; 
        if(i+1 <= 7 && board[i+1][j] == opponent){ 
            i=i+1;
            while(i<7 && board[i][j] == opponent) i++;
            if(i<=7 && board[i][j] == player) {while(i!=I+1)board[--i][j] = player;}
        }
        i=I;

        if(i+1 <= 7 && j+1 <=7 && board[i+1][j+1] == opponent){
            i=i+1;j=j+1;
            while(i<7 && j<7 && board[i][j] == opponent){i++;j++;}
            if(i<=7 && j<=7 && board[i][j] == player)while(i!=I+1 && j!=J+1)board[--i][--j] = player;}
    }  

    //update the current scores of the game
    //@see gameResult()
    //@see Reversi.java - twoPlayers()
    public void updateScores(){
        WScore = 0; BScore = 0; remaining = 0;
        for(int i=0;i<8;++i){
            for(int j=0;j<8;++j){
                if(board[i][j]=='W')WScore++;
                else if(board[i][j]=='B')BScore++;
                else remaining++;
            }
        }
    }
}