public class piece {
    public int type;
    public boolean isWhite;
    //0 is pawn, 1 is knight, 2 is bishop, 3 is rook, 4 is queen, 5 is king
    public String printName(){
        if(isWhite) {
            if (type == 0) {
                return "♙";
            } else if (type == 1) {
                return "♘";
            } else if (type == 2) {
                return "♗";
            } else if (type == 3) {
                return "♖";
            } else if (type == 4) {
                return "♕";
            } else {
                return "♔";
            }
        }
        else{
            if (type == 0) {
                return "♟︎";
            } else if (type == 1) {
                return "♞";
            } else if (type == 2) {
                return "♝";
            } else if (type == 3) {
                return "♜";
            } else if (type == 4) {
                return "♛";
            } else {
                return "♚";
            }
        }
    }

    public piece (int type, boolean isWhite){
        this.type = type;
        this.isWhite = isWhite;
    }
}
