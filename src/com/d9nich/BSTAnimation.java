package com.d9nich;

import com.d9nich.AVL.AVLTree;
import com.d9nich.AVL.BTView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BSTAnimation extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AVLTree<Integer> tree = new AVLTree<>();

        BorderPane pane = new BorderPane();
        BTView view = new BTView(tree); // Create a View
        pane.setCenter(view);

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        TextField tfValue = new TextField();
        tfValue.setPrefColumnCount(5);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
        Button btSearch = new Button("Search");
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Enter a key: "), tfKey, btInsert, btDelete, btSearch);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);

        btInsert.setOnAction(e -> {
            final int key = Integer.parseInt(tfKey.getText());
            if (tree.search(key)) // key is in the tree already
                view.setStatus(key + " is already in the tree");
            else {
                tree.insert(key); // Insert a new key
                view.displayTree();
                view.setStatus(key + " is inserted in the tree");
            }
        });

        btDelete.setOnAction(e -> {
            final int key = Integer.parseInt(tfKey.getText());
            if (!tree.search(key)) // key is not in the tree
                view.setStatus(key + " is not in the tree");
            else {
                tree.delete(key); // Delete a key
                view.displayTree();
                view.setStatus(key + " is deleted from the tree");
            }
        });

        btSearch.setOnAction(e -> {
            final int key = Integer.parseInt(tfKey.getText());
            view.setStatus(key + " is " + (tree.search(key) ? "" : "not ") + " in the tree");
        });

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 500, 250);
        primaryStage.setTitle("BSTAnimation"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        view.displayTree();
    }

}
