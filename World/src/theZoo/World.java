package theZoo;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

import java.util.Random;
public class World {
    public static Player gen_player(boolean immune, int infect_rate, boolean human, int min_nat_immune, int max_nat_immune){
        Player new_guy;
        Random rand = new Random();
        if(human) {
            new_guy = new human();
            new_guy.set_immune(immune);
            new_guy.set_Infect_rate(infect_rate);
            new_guy.set_human(true);
            new_guy.setExposure(101 - (rand.nextInt(max_nat_immune-min_nat_immune)+min_nat_immune));

        }
        else{
            new_guy = new zombie();
            new_guy.set_immune(immune);
            new_guy.set_Infect_rate(infect_rate);
            new_guy.set_human(false);
        }
        return new_guy;
    }
    public static Player[][] initilise(int infect_rate, int x_size, int y_size, int min_nat_immune, int max_nat_immune) {
        Player board[][] = new Player[x_size][y_size];
        for(int i=0; i<x_size; i++){
            for(int j=0; j<y_size;j++){
                boolean immune = false, human = true;
                board[i][j]= gen_player(immune, infect_rate, human, min_nat_immune, max_nat_immune);
            }
        }

        return board;

    }

    public static void infect(Player[][] board, int infect_rate, int x_size, int y_size, int ini_sick, int min_nat_immune, int max_nat_immune){
        Random rand = new Random();
        for(int i = 0; i < x_size; i++){
            for(int j = 0; j < y_size; j++){
                if(rand.nextInt(99) < ini_sick){
                    board[i][j] = gen_player(false, infect_rate, false, min_nat_immune, max_nat_immune);
                }
            }
        }
    }

    /*public static void print_board(Player[][] board){
        System.out.println("Current game state:");
        for(int i = 0; i < board.length; i++){
            for(int j=0; j < board[i].length; j++) {
                if(board[i][j].get_immune()==true) {
                    System.out.print("i  ");
                }
                else if(board[i][j].get_human()==true){
                    System.out.print("h  ");
                }
                else{
                    System.out.print("s  ");
                }
            }
            System.out.println("");
        }
    }
*/
    public static void step_board(Player[][] board,int infect_rate, int x_size, int y_size, int sickness_dir, int min_nat_immune, int max_nat_immune){
        Random rand = new Random();

        for(int k = 0; k < x_size; k++){
            for(int l = 0; l < y_size; l++){
                if(!board[k][l].get_human()){
                    board[k][l].inc_turns();
                    if(board[k][l].get_turn() > sickness_dir - 1){
                        board[k][l] = gen_player(true, infect_rate, true, min_nat_immune, max_nat_immune);
                    }
                }
            }
        }

        for(int i = 0; i < x_size; i++){
            for(int j = 0; j < y_size; j++){
                if(!board[i][j].get_human()){
                    try {
                        if (rand.nextInt(99) < (infect_rate * board[i - 1][j].getExposure() / 100)) {
                            if (board[i - 1][j].get_human()) {
                                if (!board[i - 1][j].get_immune()) {
                                    board[i - 1][j] = gen_player(false, infect_rate, false, min_nat_immune, max_nat_immune);
                                }
                            }
                        }
                    }
                    catch (IndexOutOfBoundsException ex) {
                    }
                    try {
                        if (rand.nextInt(99) < (infect_rate * board[i + 1][j].getExposure() / 100)) {
                            if (board[i + 1][j].get_human()) {
                                if (!board[i + 1][j].get_immune()) {
                                    board[i + 1][j] = gen_player(false, infect_rate, false, min_nat_immune, max_nat_immune);
                                }
                            }
                        }
                    }
                    catch (IndexOutOfBoundsException ex) {
                    }
                    try {
                        if (rand.nextInt(99) < (infect_rate * board[i][j - 1].getExposure() / 100)) {
                            if (board[i][j - 1].get_human()) {
                                if (!board[i][j - 1].get_immune()) {
                                    board[i][j - 1] = gen_player(false, infect_rate, false, min_nat_immune, max_nat_immune);
                                }
                            }
                        }
                    }
                    catch (IndexOutOfBoundsException ex) {
                    }
                    try {
                        if (rand.nextInt(99) < (infect_rate * board[i][j + 1].getExposure() / 100)) {
                            if (board[i][j + 1].get_human()) {
                                if (!board[i][j + 1].get_immune()) {
                                    board[i][j + 1] = gen_player(false, infect_rate, false, min_nat_immune, max_nat_immune);
                                }
                            }
                        }
                    }
                    catch(IndexOutOfBoundsException ex){
                    }
                }
            }
        }
    }

    public static int[] check_stats(Player[][] board, int x_size, int y_size){
        int h = 0, s = 0, i = 0;
        int[] returner = {0,0,0};
        for(int p = 0; p < x_size; p++){
            for(int q = 0; q < y_size; q++){
                if(board[p][q].get_immune()){
                    i++;
                }
                else if(board[p][q].get_human()){
                    h++;
                }
                else{
                    s++;
                }
            }
        }
        returner[0] = i;
        returner[1] = h;
        returner[2] = s;
        return returner;
    }
}
