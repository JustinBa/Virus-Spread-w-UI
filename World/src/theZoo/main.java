package theZoo;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class main extends Application implements EventHandler<ActionEvent>{

    Stage window;
    Button button;
    TextField infect_rate = new TextField();
    TextField ini_sick = new TextField();
    TextField sickness_durration = new TextField();
    TextField min_nat_resistance = new TextField();
    TextField max_nat_resistance = new TextField();
    Text infect_rate_text = new Text("Rate of Spread");
    Text ini_sick_text = new Text("Inital Sickness %");
    Text sickness_durration_text = new Text("Length of sickness");
    Text min_nat_resistance_text = new Text("Minimum Resistance %");
    Text max_nat_resistance_text = new Text("Maximum Resistance %");
    Text num_sick = new Text("Number sick: ");
    Text num_healthy = new Text("Number Healthy:");
    Text num_immune = new Text("Number immune");
    Text num_sick_num = new Text();
    Text num_helthy_num = new Text();
    Text num_immune_num = new Text();
    Text spacer = new Text();
    Text spacer2 = new Text();
    Text spacer3 = new Text();
    GridPane game = new GridPane();
    VBox resultsResults = new VBox(5);
    int day = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;


        window.setTitle("Infection Simulator");
        button = new Button("Go!");
        button.setOnAction(this);

        VBox uInput = new VBox(5);
        uInput.getChildren().addAll(spacer3, infect_rate_text, infect_rate, ini_sick_text, ini_sick, sickness_durration_text, sickness_durration, min_nat_resistance_text, min_nat_resistance, max_nat_resistance_text, max_nat_resistance, button);
        HBox gameNui = new HBox(20);
        VBox gameNresults = new VBox(5);
        HBox results = new HBox(5);
        VBox resultsText = new VBox(5);
        resultsText.getChildren().addAll(num_sick, num_healthy, num_immune);
        resultsResults.getChildren().addAll(num_sick_num, num_helthy_num, num_immune_num);
        results.getChildren().addAll(resultsText, resultsResults);

        gameNresults.getChildren().addAll(spacer2, game, results);
        gameNui.getChildren().addAll(spacer, gameNresults, uInput);

        for(int i = 0; i < 10; i++){
            for(int j=0; j < 10; j++) {
                Rectangle rectangle = new Rectangle(20,20, Color.WHITE);
                game.add(rectangle, i, j);
            }
        }

        Scene scene = new Scene(gameNui, 400, 320);

        window.setScene(scene);
        window.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Event handled");
        boolean total_immunity = false;
        int[] stats = {0,0,0};
        Player[][] playfeild = World.initilise(Integer.parseInt(infect_rate.getText()), 10, 10, Integer.parseInt(min_nat_resistance.getText()), Integer.parseInt(max_nat_resistance.getText()));
        World.infect(playfeild, Integer.parseInt(infect_rate.getText()), 10, 10, Integer.parseInt(ini_sick.getText()), Integer.parseInt(min_nat_resistance.getText()), Integer.parseInt(max_nat_resistance.getText()));
        print_board(playfeild);
        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                World.step_board(playfeild, Integer.parseInt(infect_rate.getText()), 10, 10, Integer.parseInt(sickness_durration.getText()), Integer.parseInt(min_nat_resistance.getText()), Integer.parseInt(max_nat_resistance.getText()));
                print_board(playfeild);
                if (World.check_stats(playfeild, 10, 10)[2] == 0) {
                    stop();
                }
                day++;
                int immune = 0, healthy = 0 , sick = 0;
                for(int i = 0; i < playfeild.length; i++){
                    for(int j=0; j < playfeild[i].length; j++) {
                        if(playfeild[i][j].get_immune()==true) {
                            immune++;
                        }
                        else if(playfeild[i][j].get_human()==true){
                            healthy++;
                        }
                        else{
                            sick++;
                        }
                    }
                }
                num_helthy_num.setText(Integer.toString(healthy));
                num_immune_num.setText(Integer.toString(immune));
                num_sick_num.setText(Integer.toString(sick));
            }
        }.start();
        window.show();

    }

    public void print_board(Player[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j=0; j < board[i].length; j++) {
                if(board[i][j].get_immune()==true) {
                    Rectangle rectangle = new Rectangle(20,20, Color.BLUE);
                    game.add(rectangle, i, j);
                }
                else if(board[i][j].get_human()==true){
                    Rectangle rectangle = new Rectangle(20,20, Color.WHITE);
                    game.add(rectangle, i, j);
                }
                else{
                    Rectangle rectangle = new Rectangle(20,20, Color.RED);
                    game.add(rectangle, i, j);
                }
            }
        }
    }

}