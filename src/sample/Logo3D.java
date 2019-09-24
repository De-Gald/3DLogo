package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Logo3D extends Application {

    private Timeline animation;

    private void init(Stage primaryStage) {
        Group root = new Group();
        root.setDepthTest(DepthTest.ENABLE);
        primaryStage.setTitle("TPU logo");
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root, 500, 400, true));
        primaryStage.getScene().setCamera(new PerspectiveCamera());
        root.getTransforms().addAll(
                new Translate(500 / 2, 400 / 4),
                new Rotate(30, Rotate.X_AXIS)
        );
        root.getChildren().add(create3dContent());
    }

    public Node create3dContent() {
        Cube c = new Cube(100, 200, Color.GREEN,1);
        Cube c2 = new Cube(100, 100, Color.GREEN,1);
        c2.setTranslateX(110);
        Cube c3 = new Cube(100, 100, Color.GREEN,1);
        c3.setTranslateX(-110);
        Cube c4 = new Cube(100, 200, Color.BLACK,1);
        c4.setTranslateX(-110);
        c4.setTranslateY(110);
        Cube c5 = new Cube(100, 100, Color.BLACK,1);
        c5.setTranslateY(210);
        Cube c6 = new Cube(100, 200, Color.BLACK,1);
        c6.setTranslateX(110);
        c6.setTranslateY(110);

        animation = new Timeline();
        animation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(c.ry.angleProperty(), 0d),
                        new KeyValue(c2.ry.angleProperty(), 0d),
                        new KeyValue(c3.ry.angleProperty(), 0d),
                        new KeyValue(c4.ry.angleProperty(), 0d),
                        new KeyValue(c5.ry.angleProperty(), 0d),
                        new KeyValue(c6.ry.angleProperty(), 0d)
                ),
                new KeyFrame(Duration.seconds(9),
                        new KeyValue(c.ry.angleProperty(), 360d),
                        new KeyValue(c2.ry.angleProperty(), 360d),
                        new KeyValue(c3.ry.angleProperty(), 360d),
                        new KeyValue(c4.ry.angleProperty(), 360d),
                        new KeyValue(c5.ry.angleProperty(), 360d),
                        new KeyValue(c6.ry.angleProperty(), 360d)
                ));
        animation.setCycleCount(Animation.INDEFINITE);

        return new Group(c, c2, c3, c4, c5, c6);
    }

    public void play() {
        animation.play();
    }

    @Override public void stop() {
        animation.pause();
    }

    public class Cube extends Group {
        final Rotate rx = new Rotate(0,Rotate.X_AXIS);
        final Rotate ry = new Rotate(0,Rotate.Y_AXIS);
        final Rotate rz = new Rotate(0,Rotate.Z_AXIS);
        public Cube(double sizeW, double sizeH, Color color, double shade) {
            getTransforms().addAll(rz, ry, rx);
            getChildren().addAll(
                    RectangleBuilder.create() // back face
                            .width(sizeW).height(sizeH)
                            .fill(color.deriveColor(0.0, 1.0, (1 - 0.5*shade), 1.0))
                            .translateX(-0.5*sizeW)
                            .translateY(-0.5*sizeW)
                            .translateZ(0.5*sizeW)
                            .build(),
                    RectangleBuilder.create() // bottom face
                            .width(sizeW).height(sizeW)
                            .fill(color.deriveColor(0.0, 1.0, (1 - 0.4*shade), 1.0))
                            .translateX(-0.5*sizeW)
                            .translateY(0)
                            .rotationAxis(Rotate.X_AXIS)
                            .rotate(90)
                            .build(),
                    RectangleBuilder.create() // right face
                            .width(sizeW).height(sizeH)
                            .fill(color.deriveColor(0.0, 1.0, (1 - 0.3*shade), 1.0))
                            .translateX(-1*sizeW)
                            .translateY(-0.5*sizeW)
                            .rotationAxis(Rotate.Y_AXIS)
                            .rotate(90)
                            .build(),
                    RectangleBuilder.create() // left face
                            .width(sizeW).height(sizeH)
                            .fill(color.deriveColor(0.0, 1.0, (1 - 0.2*shade), 1.0))
                            .translateX(0)
                            .translateY(-0.5*sizeW)
                            .rotationAxis(Rotate.Y_AXIS)
                            .rotate(90)
                            .build(),
                    RectangleBuilder.create() // top face
                            .width(sizeW).height(sizeW)
                            .fill(color.deriveColor(0.0, 1.0, (1 - 0.1*shade), 1.0))
                            .translateX(-0.5*sizeW)
                            .translateY(-1*sizeW)
                            .rotationAxis(Rotate.X_AXIS)
                            .rotate(90)
                            .build(),
                    RectangleBuilder.create() // top face
                            .width(sizeW).height(sizeH)
                            .fill(color)
                            .translateX(-0.5*sizeW)
                            .translateY(-0.5*sizeW)
                            .translateZ(-0.5*sizeW)
                            .build()
            );
        }
    }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}