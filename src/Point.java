/**
 * Point.java 
 * <p>
 * this is the properties class for Point
 * this class is a tuple for the container point 
 * which is eventually the point clicked
 * Point.toString();returns a string like [x, y] i.e. [0, 1]
 * Point.equals(Object o);returns a boolean dependent on oject of type hastable
 * Point.hashCode();returns a int like (xy) i.e. (01)<p>
 * 
 * @see vsComputer.java
 * @see Board.java
 * @see Reversi.java
 */

import java.util.HashSet;

    public class Point{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        //this means for this datatype of Point we override the standard methods from the JDK
        @Override
        public String toString(){
            return "["+x+", "+y+"]";
        }

        //this means for this datatype of Point we override the standard methods from the JDK
        @Override
        public boolean equals(Object o){
            return o.hashCode()==this.hashCode();
        }

        //this means for this datatype of Point we override the standard methods from the JDK
        @Override
        public int hashCode() {
            return Integer.parseInt(x+""+y);
        }
    }