package com.example.ticktacktoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class Ticktacktoe extends Application {
private Button buttons[][]= new Button[3][3];
    private Label playerXscoreLabel,playerOscoreLabel;

    private int playerXScore=0,playerOScore=0;
private  boolean playerXTurn=true;
   private BorderPane createContent(){
       BorderPane root=new BorderPane();

       //Title
Label titleLabel=new Label("Tic Tack Toe");
titleLabel.setStyle("-fx-font-size : 24pt; -fx-font-weight :bold;");
root.setTop(titleLabel);
BorderPane.setAlignment(titleLabel, Pos.CENTER);

       //Game board
       GridPane gridPane=new GridPane();
       //gridPane.setPadding(20);
       gridPane.setAlignment(Pos.CENTER);
       gridPane.setHgap(10);
       gridPane.setVgap(10);
       for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
               Button button=new Button();
               button.setStyle("-fx-font-size :16pt; -fx-font-weight :bold;");
               button.setOnAction(event->buttonClicked(button));
               button.setPrefSize(100,100);
               buttons[i][j]=button;
               gridPane.add(button,j,i);

           }

       }
       //put in centre
       root.setCenter(gridPane);
       BorderPane.setAlignment(gridPane, Pos.CENTER);



       //score
HBox scoreBoard =new HBox(20);
scoreBoard.setAlignment(Pos.CENTER);
playerXscoreLabel=new Label("Player X:0");
playerXscoreLabel.setStyle("-fx-font-size :16pt; -fx-font-weight :bold;");
       playerOscoreLabel=new Label("Player O:0");
       playerOscoreLabel.setStyle("-fx-font-size :16pt; -fx-font-weight :bold;");

scoreBoard.getChildren().addAll(playerXscoreLabel,playerOscoreLabel);

root .setBottom(scoreBoard);
       return root;
   }

private void buttonClicked(Button button){
       if(button.getText().equals("")){
           if(playerXTurn){
               button.setText("X");
           }
           else{
               button.setText("O");
           }
           playerXTurn=!playerXTurn;
           checkWinner();
       }
       return;
}

private void checkWinner(){
       //row
    for (int row = 0; row < 3; row++) {
        if( buttons[row][0].getText().equals(buttons[row][1].getText())
            && buttons[row][1].getText().equals(buttons[row][2].getText())
                && !buttons[row][0].getText().isEmpty()){
    //we will have a winner
            String winner=buttons[row][0].getText();
            showWinnerDialogue(winner);
            updateScore(winner);
            resetBoard();
            return;
        }

    }



    ///col
    for (int col = 0; col < 3; col++) {
        if( buttons[0][col].getText().equals(buttons[1][col].getText())
                && buttons[1][col].getText().equals(buttons[2][col].getText())
                && !buttons[0][col].getText().isEmpty()){
            //we will have a winner
            String winner=buttons[0][col].getText();
            showWinnerDialogue(winner);
            updateScore(winner);
            resetBoard();
            return;
        }
    }

    //diagonal

        if( buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().isEmpty()){
            //we will have a winner
            String winner=buttons[0][0].getText();
            showWinnerDialogue(winner);
            updateScore(winner);
            resetBoard();
            return;
        }
    if( buttons[2][0].getText().equals(buttons[1][1].getText())
            && buttons[1][1].getText().equals(buttons[0][2].getText())
            && !buttons[2][0].getText().isEmpty()){
        //we will have a winner
        String winner=buttons[2][0].getText();
        showWinnerDialogue(winner);
        updateScore(winner);
        resetBoard();
        return;
    }


    ///////Tie
    boolean tie=true;
    for(Button row[] :buttons){
        for(Button button :row){
          if(button.getText().isEmpty()){
              tie=false;
              break;
          }
        }
    }
    if(tie){
        showTieDialogue();
        resetBoard();
    }


}
    private void showWinnerDialogue(String winner){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congratulation "+ winner +"! You Won the game" );
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void showTieDialogue(){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("Game Over! It's a tie." );
        alert.setHeaderText("");
        alert.showAndWait();
    }

    private void updateScore(String winner){
       if(winner.equals("X")){
           playerXScore++;
           playerXscoreLabel.setText("Player X :"+ playerXScore);
       }
       else{
           playerOScore++;
           playerOscoreLabel.setText("Player O : "+playerOScore);
       }
    }

    private void resetBoard(){
       for(Button row[] :buttons){
           for(Button button :row){
               button.setText("");
           }
       }
    }



    @Override



    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createContent());
        stage.setTitle("TickTackToe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}