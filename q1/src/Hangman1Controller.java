import com.sun.javafx.stage.StageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;

public class Hangman1Controller {

    @FXML
    private Button newGameButton;

    @FXML
    private Button submitButton;

    @FXML
    private ComboBox<String> comboChoice;

    @FXML
    private Canvas canvas;

    @FXML
    private Label theWord;

    @FXML
    private Label usedLetters;
    private GraphicsContext gc;//= canvas.getGraphicsContext2D()
    private String word;
    private int countMistakes = 0;
private String guessedLetters ="" ;
    @FXML
    void newGameButtonPressed(ActionEvent event) {

        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        countMistakes = 0;
        guessedLetters ="";
        usedLetters.setText("Letters you used: ");
        getWord();
        setTheWord();


    }

    @FXML
    void submitButtonPressed(ActionEvent event) {
        String guess = comboChoice.getValue();
        if (word.contains(guess)&&!guessedLetters.contains(guess)){
            updateWord(guess);
            guessedLetters = guessedLetters + guess;
        }
        else if (guess == null || guessedLetters.contains(guess)) {
            //statusLabel.setText("Invalid or repeated guess. Try again!");
            JOptionPane.showMessageDialog(null, "You can't repeat guesses", "Wrong input", JOptionPane.ERROR_MESSAGE);
//            return;
        }

        else if (!word.contains(guess)&&!guessedLetters.contains(guess)) {
            countMistakes++;
            if (countMistakes<6) {
                updateCanvas(countMistakes);
                guessedLetters = guessedLetters + guess;
            }
            else if (countMistakes==6) {
                updateCanvas(countMistakes);

                int n = JOptionPane.showConfirmDialog(null, "you lose! \n wanna new game?",
                        "End game",JOptionPane.YES_NO_OPTION );

            }

        }

        usedLetters.setText("Letters you used:"+"\n"+guessedLetters);

    }
    public void initialize() {
        getWord();
        setComboChoice();
//        comboChoice.getItems().addAll("a", "b", "c", "d", "e", "f", "g", "h", "i",
//                "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");

        gc= canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
//        for (int i = 0; i < word.length(); i++) {
//
//            underline = underline + " _";
//        }
//        theWord.setText(underline);
        setTheWord();


    }

    private void updateWord(String guess){
        StringBuilder wordGuess = new StringBuilder(theWord.getText());
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess.charAt(0)) {
                    wordGuess.setCharAt(i * 2+1, guess.charAt(0));
                }
            }
            theWord.setText(wordGuess.toString());
    }

    private void updateCanvas(int i){

        double width = canvas.getWidth(), height = canvas.getHeight();


        if (i==1){        gc.strokeOval(width/2, 50, 60,60);//head
        }
        else if (i==2) {
            gc.strokeLine(width / 2 + 30, 110, width / 2 + 30, 250);}//body
        else if (i==3) {
            gc.strokeLine(width/2+30, 140, width/2, 150);//left hand

        } else if (i==4) {
            gc.strokeLine(width/2+30, 140, width/2+60, 150);//right hand

        } else if (i==5) {
            gc.strokeLine(width/2+30, 210, width/2, 250);//left leg

        } else if (i==6) {
            gc.strokeLine(width/2+30, 210, width/2+60, 250);//right leg

        }


    }

    private  void getWord(){
        word ="java";
    }
    private void setComboChoice(){
        comboChoice.getItems().addAll("a", "b", "c", "d", "e", "f", "g", "h", "i",
                "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");

    }

    private void setTheWord(){
        String underline="";
        for (int i = 0; i < word.length(); i++) {

            underline = underline + " _";
        }
        theWord.setText(underline);

    }


}




