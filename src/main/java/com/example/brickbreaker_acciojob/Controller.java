package com.example.brickbreaker_acciojob;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane scene;

    @FXML
    private Circle circle;

    private Rectangle slider;

    private Button left, right;

    //Random random = new Random();

    private final ArrayList<Rectangle> bricks = new ArrayList<>();

    double deltaX = 0.3;
    double deltaY = 1;

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent actionEvent) {
            circle.setLayoutX(circle.getLayoutX() + deltaX);
            circle.setLayoutY(circle.getLayoutY() + deltaY);

            checkCollisionSlider();
            checkCollisionScene(scene);

            if(!bricks.isEmpty()){
                bricks.removeIf(brick -> checkCollisionBricks(brick));
            } else {
                timeline.stop();
            }

            System.out.println(bricks.size());
        }
    }));


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeline.setCycleCount(Animation.INDEFINITE);

        createBricks();

        timeline.play();
        add_Slider();
        add_buttons();
    }

    public void checkCollisionScene(Node node){
        Bounds bounds = node.getBoundsInLocal();
        boolean rightBorder = circle.getLayoutX() >= (bounds.getMaxX() - circle.getRadius());
        boolean leftBorder = circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius());
        boolean bottomBorder = circle.getLayoutY() >= (bounds.getMaxY() - circle.getRadius());
        boolean topBorder = circle.getLayoutY() <= (bounds.getMinY() + circle.getRadius());

        if (rightBorder || leftBorder) {
            deltaX *= -1;
        }
        if (bottomBorder || topBorder) {
            deltaY *= -1;
        }

        if(bottomBorder){
            bricks.clear();
        }
    }

    public void checkCollisionSlider(){
        if(circle.getBoundsInParent().intersects(slider.getBoundsInParent())){
            deltaY *= -1;
        }
    }
    public boolean checkCollisionBricks(Rectangle brick){

        if(circle.getBoundsInParent().intersects(brick.getBoundsInParent())){

            boolean rightBorder = circle.getLayoutX() >= ((brick.getX() + brick.getWidth()) - circle.getRadius());
            boolean leftBorder = circle.getLayoutX() <= (brick.getX() + circle.getRadius());
            boolean bottomBorder = circle.getLayoutY() >= ((brick.getY() + brick.getHeight()) - circle.getRadius());
            boolean topBorder = circle.getLayoutY() <= (brick.getY() + circle.getRadius());

            if (rightBorder || leftBorder) {
                deltaX *= -1;
            }
            if (bottomBorder || topBorder) {
                deltaY *= -1;
            }
            scene.getChildren().remove(brick);
            circle.setFill(brick.getFill());
            return true;
        }
        return false;
    }


    public void createBricks(){
        double width = 560;
        double height = 200;

        int spaceCheck = 1;

        for (double i = height; i > 0 ; i = i - 50) {
            for (double j = width; j > 0 ; j = j - 25) {
                if(spaceCheck % 2 == 0){
                    Rectangle rectangle = new Rectangle(j,i,35,35);
                    if(spaceCheck % 3 == 0)rectangle.setFill(Color.RED);
                    else if(spaceCheck % 3 == 1) rectangle.setFill(Color.BLUE);
                    else rectangle.setFill(Color.GREEN);
                    scene.getChildren().add(rectangle);
                    bricks.add(rectangle);
                }
                spaceCheck++;
            }
        }
    }

    public void add_Slider(){
        slider = new Rectangle(280, 380, 70, 10);
        slider.setFill(Color.BLACK);
        scene.getChildren().add(slider);
    }

    public void add_buttons(){
        left = new Button("LEFT");
        left.setLayoutX(20);
        left.setLayoutY(300);

        right = new Button("RIGHT");
        right.setLayoutX(540);
        right.setLayoutY(300);

        left.setOnAction(moveleft);
        right.setOnAction(moveright);
        scene.getChildren().add(left);
        scene.getChildren().add(right);
    }

    EventHandler<ActionEvent> moveright = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            slider.setLayoutX(slider.getLayoutX() + 10);
        }
    };

    EventHandler<ActionEvent> moveleft = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            slider.setLayoutX(slider.getLayoutX() - 10);
        }
    };
}