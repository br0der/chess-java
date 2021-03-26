import processing.core.PApplet;
import java.util.*;

public class main extends PApplet {
    private static piece[][] board = new piece[8][8];
    private static boolean isWhitesMove;
    private static int halfmove, fullmove;
    private static int w = 500;
    private static int h = 500;
    public static void main(String[] args) {
        PApplet.main("main");
        loadFENPosition("r1bqkbnr/pppnpppp/8/8/3Q4/3Q1N1P/PPPQPPPP/RNBQKB1R");

    }

    public void settings() {
        size(w, h);
    }

    public void setup(){
        noStroke();
    }

    public void draw(){
        background(255);
        fill(0,255,0);
        double length = (double)w/8;
        double height = (double)h/8;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((i+j)%2==0){
                    fill(250, 223, 155);
                }
                else{
                    fill(158, 117, 16);
                }
                rect((int) length*j,(int) height*i, (int) (length*j+w/8),(int) (height*i+h/8));
            }
        }
        drawBoard(board);
    }

    public static void loadFENPosition(String FEN){
        //rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
        String[] metaData = FEN.split(" ");
        String[] position = metaData[0].split("/");
        for (int i = 0; i < position.length; i++) {
            //New Line
            int positionInLine = -1;
            for (int j = 0; j < position[i].length(); j++) {
                //Next char in the line
                positionInLine++;
                if(Character.isDigit(position[i].charAt(j))){
                    //If it is a number
                    int num = Character.getNumericValue(position[i].charAt(j));
                    positionInLine+=num-1;
                }
                else{
                    //If it isn't a number
                    char pieceName = Character.toLowerCase(position[i].charAt(j));
                    int pieceVal;
                    if(pieceName=='p')
                        pieceVal=0;
                    else if(pieceName=='n')
                        pieceVal=1;
                    else if(pieceName=='b')
                        pieceVal=2;
                    else if(pieceName=='r')
                        pieceVal=3;
                    else if(pieceName=='q')
                        pieceVal=4;
                    else
                        pieceVal=5;
                    board[positionInLine][i]=new piece(pieceVal, Character.isUpperCase(position[i].charAt(j)));
                }
            }
        }
        if(metaData.length>1) {
            //w for whites move b for blacks move
            if (metaData[1].equals("w")) {
                isWhitesMove = true;
            } else {
                isWhitesMove = false;
            }
            //K Q white can castle king/queen side, k q black can castle king/queen side, - for nothing
            if (!metaData[2].equals("-")) {
                //Can Castle?
            }
            //En Passant possibility, x6 or x2 if there is an en passant possibility, - for none
            if (!metaData[3].equals("-")) {
                board[(int) metaData[3].charAt(0) - 97][8 - (Character.getNumericValue(metaData[3].charAt(1)))] = new piece(0, true);
            }
            //Halfmove clock: This is the number of halfmoves since the last capture or pawn advance. The reason for this field is that the value is used in the fifty-move rule.
            halfmove = Integer.parseInt(metaData[4]);
            //Fullmove number: The number of the full move. It starts at 1, and is incremented after Black's move.
            fullmove = Integer.parseInt(metaData[5]);
        }
        else{
            isWhitesMove = true;
            halfmove = -1;
            fullmove = -1;
        }
    }

    public static void drawBoard(piece[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[j][i]!=null? board[j][i].printName() : " ");
            }
        }
    }
}
