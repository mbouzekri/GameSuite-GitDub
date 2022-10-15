/**
@author Vishal Cherukuri, Ryan Day
@version 1.1
 */
public class Board {
    private final Piece[][] playSpace;// tin;
    private int blackCaptures;// number of red pieces captured by black;
    private int redCaptures;//number of black pieces captured by red;
    private boolean turn; // true = red, false = black;
    public static final int BLACK = 0;
    public static final int RED = 1;
    /**
       Constructs Board Array and Populates Initial Piece placements;
       !!IMPORTANT!! : The Board is arranged with the top left of the board being 0,0, meaning Down == + and Up == -;
     */
    public Board() {
	//Declare all instance values;
	playSpace = new Piece[8][8];
	blackCaptures = 0;
	redCaptures = 0;
	turn = true;
	//Populate Black Team;
	for (int i = 0; i <= 3; i++) {
	    for (int j = 0; j < 8; j++) {
		if ((i + j) % 2 == 0) { playSpace[i][j] = new Piece(BLACK);}
	    }
	}
	//Populate Red Team;
	for (int i = 5; i <= 7; i++) {
	    for (int j = 0; j < 8; j++) {
		if ((i + j) % 2 == 0) { playSpace[i][j] = new Piece(RED);}
	    }
	}
    }
    /**
       Switches turn from black to red or red to black;
     */
    private void changeTurn(){
	if(turn){
	    turn = false;
	} else {
	    turn = true;
	}
    }
    /**
       Performs move
       @param ox Origin X Coord;
       @param oy Origin Y Coord;
       @param ex Ending X Coord;
       @param ey Ending Y Coord;
     */
    public void move(int ox, int oy, int ex, int ey){
	if(validMove(ox, oy, ex, ey)){
	    playSpace[ex][ey] = playSpace[ox][oy];
	    playSpace[ox][oy] = null;
	    changeTurn();
	}
    }
    /**
       Checks if coordinates are valid for move
       @param ox Origin X Coord;
       @param oy Origin Y Coord;
       @param ex Ending X Coord;
       @param ey Ending Y Coord;
     */
    public boolean validMove(int ox, int oy, int ex, int ey){
        if(ox < 8 && ox >= 0 && // check if coords in array bounds;
	   oy < 8 && oy >= 0 && 
	   ex < 8 && ex >= 0 && 
	   ey < 8 && ey >= 0 && 
	   playSpace[ox][oy] != null && // check if there is a piece at origin;
	   (ex == ox + 1 || ex == ox - 1) && // check if ex is in range of ox to move;
	   ((ey == oy + 1 && playSpace[ox][oy].getTeam() == BLACK) || // check if ey is south of oy and that the team is black OR;
	    (ey == oy - 1 && playSpace[ox][oy].getTeam() == RED) || // check if ey is north of oy and that the team is red OR;
	    ((ey == oy + 1 || ey == oy - 1) && playSpace[ox][oy].getKing())) && // check if ey is in range of oy and that the piece is a king;
	   playSpace[ex][ey] == null){ // check if the exit is empty;
	    return true;
	}
	return false;
    }
    /**
       Performs capture
       @param ox Origin X Coord;
       @param oy Origin Y Coord;
       @param ex Ending X Coord;
       @param ey Ending Y Coord;
    */
    public void capture(int ox, int oy, int ex, int ey){
	if(validCapture(ox, oy, ex, ey)){
	    playSpace[(ex + ox) / 2][(ey + oy) / 2] = null;
	    playSpace[ex][ey] = playSpace[ox][oy];
	    playSpace[ox][oy] = null;
	    // increment captures based on piece;
	    if(playSpace[ex][ey].getTeam() == BLACK){
		blackCaptures++;
	    }
	    if(playSpace[ex][ey].getTeam() == RED){
		redCaptures++;
	    }
	    //check if there are any other possible captures or moves that are valid, if not change turn;
	    if(!validCapture(ex,ey,ex + 2, ey + 2) &&
	       !validCapture(ex,ey,ex + 2, ey - 2) &&
	       !validCapture(ex,ey,ex - 2, ey - 2) &&
	       !validCapture(ex,ey,ex - 2, ey + 2) &&
	       !validMove(ex,ey,ex + 1, ey + 1) &&
	       !validMove(ex,ey,ex + 1, ey - 1) &&
	       !validMove(ex,ey,ex - 1, ey - 1) &&
	       !validMove(ex,ey,ex - 1, ey + 1)){
		changeTurn();
	    }
	}
    }
    /**
       Checks for a valid capture
       @param ox Origin X Coord;
       @param oy Origin Y Coord;
       @param ex Ending X Coord;
       @param ey Ending Y Coord;
    */
    public boolean validCapture(int ox, int oy, int ex, int ey){
	if(ox < 8 && ox >= 0 && //check if coords are in bounds of array;
	   oy < 8 && oy >= 0 &&
	   ex < 8 && ex >= 0 &&
	   ey < 8 && ey >= 0 &&
	   playSpace[ox][oy] != null && //check if origin has a piece;
	   (ex == ox + 2 || ex == ox - 2) && //check if ending is in range;
	   (ey == oy + 2 || ey == oy - 2) &&
	   playSpace[ex][ey] == null && //check if ending is open;
	   playSpace[(ex + ox) / 2][(ey + oy) / 2] != null && //check if target has a piece to hop over;
	   playSpace[(ex + ox) / 2][(ey + oy) / 2].getTeam() != playSpace[ox][oy].getTeam()){ // check if target piece is on the opposite team as origin piece;
	    return true;
	}
	return false;
    }
}
