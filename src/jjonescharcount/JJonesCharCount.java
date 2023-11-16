package jjonescharcount;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author: Jacob Jones
 * Date Completed: 10/21/2019
 * 
 * This program will find out how many times a given string occurs in a given sentence.
 * The program achieves this using recursion.
 * Also has an option to check with case sensitivity
 */
public class JJonesCharCount extends Application {
    
    //Initializes the scene and layout
    Scene scene;
    GridPane layout;
    
    //Initializes the HBoxes
    HBox infoRow;
    HBox buttonRow;
    HBox resultRow;
    
    //Initializes the VBoxes
    VBox labelCol;
    VBox fieldCol;
    VBox mainCol;
    
    //Initializes the labels
    Label title;
    Label sentenceLabel;
    Label characterLabel;
    Label countLabel;
    Label countNumberLabel;
    
    //Initializes the text fields
    TextField sentenceField;
    TextField characterField;
    
    //Initializes the buttons
    Button confirmButton;
    Button resetButton;
    
    //Initializes the the checkbox
    CheckBox caseSensitive;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        //Sets up the layout of the scene
        layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));
        
        //Sets up the scene
        scene = new Scene(layout, 400, 300);
        scene.getStylesheets().add("styles/main.css"); //Thanks to the last project, I figured out how to link the css file
        primaryStage.setTitle("Character Counter");
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(400);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //Sets up the title label
        title = new Label("Character Counter");
        title.getStyleClass().add("header");
        
        //Sets up the sentence label
        sentenceLabel = new Label("Enter Sentence:");
        sentenceLabel.setPadding(new Insets(5));
        
        //Sets up the character label
        characterLabel = new Label("Enter Character(s):");
        characterLabel.setPadding(new Insets(5));
        
        //Sets up the VBox of the labels
        labelCol = new VBox(sentenceLabel, characterLabel);
        labelCol.setAlignment(Pos.CENTER_LEFT);
        labelCol.setPadding(new Insets(5));
        labelCol.setSpacing(10);
        
        //Sets up the fields
        sentenceField = new TextField();
        characterField = new TextField();
        
        //Sets up the VBox of the fields
        fieldCol = new VBox(sentenceField, characterField);
        fieldCol.setAlignment(Pos.CENTER_RIGHT);
        fieldCol.setSpacing(10);
        
        //Combines the two VBoxes into one HBox for organization purposes
        infoRow = new HBox(labelCol, fieldCol);
        infoRow.setAlignment(Pos.CENTER);
        
        //Sets up the button that confirms the information
        confirmButton = new Button();
        confirmButton.setText("Enter");
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                //Checks if either field is empty
                if(!sentenceField.getText().isEmpty() && !characterField.getText().isEmpty()){
                    //Sets the countNumberLabel text depending on the method's result
                    countNumberLabel.setText("" + getCharacterCount(sentenceField.getText(), characterField.getText()));
                }
            }
        });
        
        //Sets up the button that that resets the fields
        resetButton = new Button();
        resetButton.setText("Reset");
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Makes the fields blank again
                sentenceField.setText("");
                characterField.setText("");
                countNumberLabel.setText("0");
            }
        });
        
        //Sets up thee check box that checks if the user wants the search to be case sensitive
        caseSensitive = new CheckBox("Case Sensitive?");
        
        //Sets up the HBox that holds the buttons and check box
        buttonRow = new HBox(confirmButton, resetButton, caseSensitive);
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.setSpacing(10);
        
        //Sets up the character count labels
        countLabel = new Label("Character Count:");
        countNumberLabel = new Label("0");
        
        //Sets up the BOx that combines the character count labels
        resultRow = new HBox(countLabel, countNumberLabel);
        resultRow.setAlignment(Pos.CENTER);
        resultRow.setSpacing(5);
        
        //Combines the title and all of the HBoxes into one VBox for organization
        mainCol = new VBox(title, infoRow, buttonRow, resultRow);
        mainCol.setAlignment(Pos.CENTER);
        mainCol.setSpacing(10);
        layout.add(mainCol, 0, 0);
    }

    /**
     * This method will recursively find the number of times the search string occurs in the word string
     * 
     * @param word The word being looked into
     * @param search The string of characters being searched
     * @return # of times the search occurs
     */
    public int getCharacterCount(String word, String search){
        //Checks if the check box is ticked on
        //If it is, then check the letters without adjusting the case
        if(caseSensitive.isSelected()){ 
            //Checks if the word has the search string and if it isn't empty
            if(word.contains(search) && !word.isEmpty()){ 
                return 1 + getCharacterCount(word.substring(word.indexOf(search) + 1), search);
            } else { //Returns zero if the word does not contain the search string
                return 0;
            }
        }
        //Will activate if the check box isn't ticked, meaning it will adjust the case for checking
        //Checks if the word has the search string and if it isn't empty, adjusting the word and search string
        //to lower case
        if(word.toLowerCase().contains(search.toLowerCase()) && !word.isEmpty()){
            return 1 + getCharacterCount(word.toLowerCase().substring(word.toLowerCase().indexOf(search.toLowerCase()) + 1).toLowerCase(), search.toLowerCase());
        } else { //Returns zero if the word does not contain the search string
            return 0;
        }
    }
    
}
