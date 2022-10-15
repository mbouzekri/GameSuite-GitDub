public class Piece {
  private boolean king;
  private int team;
  public static final int BLACK = 0;
  public static final int RED = 1;
  public Piece(int team){
    this.king = false;
    if(team != BLACK && team != RED) {
      throw new java.lang.IllegalArgumentException("Invalid Team Entered");
    }
    this.team = team;
  }
  
  public boolean getKing(){
    return king;
  }
  
  public void setKing(boolean king){
      this.king = king;
  }
  
  public int getTeam(){
    return team;
  }
  
  public void setTeam(int team){
    this.team = team;
  }
}
