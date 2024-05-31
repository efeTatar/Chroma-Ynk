package com.example.demo1;

import com.tsan.chromaynk.Context;
import com.tsan.chromaynk.Client;
import com.tsan.chromaynk.datatypes.Cursor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class DrawingAppController {
    @FXML
    private Canvas canvas;
    @FXML
    private Button pauseButton;

    ObservableList<String> SpeedList  = FXCollections.observableArrayList("Instant", "Slow", "Medium", "Fast");

    @FXML
    private ChoiceBox speedBox;

    private GraphicsContext gc;

    private boolean hasDrawn = false;
    private boolean isPaused = false;

    public double getWidth()
    {
        return canvas.getWidth();
    }

    public double getHeight()
    {
        return canvas.getHeight();
    }

    @FXML
    private void initialize() {
        speedBox.setItems(SpeedList);
        speedBox.setValue("Instant");
    }

    public void setGraphicContext() {
        this.gc = this.canvas.getGraphicsContext2D();
    }

    public int handleSpeedBox(){
        int speed = 0;
        switch (speedBox.getValue().toString()){
            case "Instant":
                speed = 0;
                break;

            case "Slow":
                speed = 100;
                break;

            case "Medium":
                speed = 20;
                break;

            case "Fast":
                speed = 5;
                break;
        }
        return speed;
    }

    @FXML
    private void handleLoadFileButtonClicked() throws InterruptedException {
        FileChooser fileChooser = new FileChooser();            // Creates a file selection dialog box
        fileChooser.setTitle("Open Instruction File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.abbas"));
        File file = fileChooser.showOpenDialog(canvas.getScene().getWindow());         // Show file chooser dialog
        hasDrawn = true;
        if (file != null) {
            loadInstructionsFromFile(file);                         // Loads instructions from the file
        }
    }

    private void loadInstructionsFromFile(File file) throws InterruptedException {

        System.out.println("file: " + file.toPath());
        setGraphicContext();
        gc.setLineWidth(1);
        gc.setStroke(Color.BLACK);

        Client client = new Client(this);

        client.tokenize(file.toPath().toString());

        System.out.println("\nresult of tokenization process:");
        client.display();

        System.out.println("\nresult of parsing process:");
        client.parse();

        System.out.println("\nexecuting " + file.toPath().toFile() + ":");
        client.execute(handleSpeedBox());

    }

    @FXML
    private void handleSaveButtonClicked() {             // Save the design
        if (hasDrawn) {
            WritableImage snapshot = canvas.snapshot(null, null);              // Take snapshot of drawing pane

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Drawing");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("PNG Files", "*.png"),
                    new FileChooser.ExtensionFilter("JPEG Files", "*.jpg", "*.jpeg")
            );
            File file = fileChooser.showSaveDialog(canvas.getScene().getWindow());                         // Show file chooser dialog
            if (file != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);   //Save image to file
                } catch (IOException ex) {
                    System.out.println("Error saving image: " + ex.getMessage());                   // Print error if saving fails
                }
            }
        } else {
            System.out.println("Nothing to save. Drawing area is empty.");                          // Print message if there's nothing to save
        }
    }

    @FXML
    private void handleClearButtonClicked() {                      // Deletes the contents of the drawing
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //loadFileButton.setDisable(false);
        hasDrawn = false;
        // Set flag to indicate no drawing has been done
    }

    @FXML
    private void togglePause() {
        isPaused = !isPaused;
        if (isPaused) {
            pauseButton.setText("Resume");
        } else {
            pauseButton.setText("Pause");
        }
    }

    

    public int FWD(Context context, double distance){
        Cursor c = context.getMainCursor();
        if(c == null) return 0;

        System.out.println("FWD CALLED");

        double newX = c.getX() + distance * Math.cos(Math.toRadians(c.getRotation()));   // Calculates the new cursor coordinates
        double newY = c.getY() + distance * Math.sin(Math.toRadians(c.getRotation()));
        if( newX > canvas.getWidth() ||newX < 0){
            System.out.println("You are out of borders, you cannot draw more than " + canvas.getWidth() + " pixels");
            return 0;
        }
        if( newY > canvas.getHeight() || newY < 0){
            System.out.println("You are out of borders, you cannot draw more than " + canvas.getHeight() + " pixels");
            return 0;
        }
        System.out.println("Forwarding ... "+"newX = " + newX + ", newY = " + newY);
        gc.strokeLine(c.getX(), c.getY(), newX, newY);        // Draw line from current position to new position
        System.out.println(c.getX() + " " + c.getY());
        c.setX(newX);                               // New coordinates
        c.setY(newY);
        return 1;
    }

    //BWD function, used in the interpreter
    public int BWD(Cursor c, double size, int percent){
        if(percent != 0 && percent != 1){
            System.out.println("You must put a number between 0 and 1");
            return 0;
        }
        if(percent == 1){
            size = Math.max(canvas.getWidth(), canvas.getHeight()) * size / 100;
        }
        double newX = c.getX() - size * Math.cos(Math.toRadians(c.getRotation()));   // Calculates the new cursor coordinates
        double newY = c.getY() - size * Math.sin(Math.toRadians(c.getRotation()));
        if( newX > canvas.getWidth() || newX < 0){
            System.out.println("You are out of borders, you cannot draw more than " + canvas.getWidth() + " pixels");
            return 0;
        }
        if( newY > canvas.getHeight()  || newY < 0){
            System.out.println("You are out of borders, you cannot draw more than " + canvas.getHeight() + " pixels");
            return 0;
        }
        gc.strokeLine(c.getX(), c.getY(), newX, newY);        // Draw line from current position to new position
        c.setX(newX);                               // New coordinates
        c.setY(newY);
        return 1;
    }

    //TURN function, used in the interpreter
    public int TURN(Context context, double degree){
        Cursor c = context.getMainCursor();
        if(c == null) return 0;
        c.setRotation((c.getRotation() + degree) % 360);
        return 1;
    }

    public int COLORHEX(String hex){
        Color color = Color.web(hex);  // Take the second element as a hexadecimal code or colour name
        gc.setStroke(color);
        return 1;
    }

    public int COLORRGB(double red, double green, double blue){

        Color color = Color.color(red%250 / 255, green, blue);;  // Take the second element as a hexadecimal code or colour name
        gc.setStroke(color);
        return 1;
    }

    public int MOV(Context context, double x, double y){

        Cursor c = context.getMainCursor();
        if(c == null) return 0;

        x = c.getX() + x;
        y = c.getY() + y;

        if( x > canvas.getWidth() || x < 0){
            System.out.println("You are out of borders, you cannot move outside of " + canvas.getWidth());
            return 0;
        }
        if( y > canvas.getHeight()  || y < 0){
            System.out.println("You are out of borders, you cannot move outside of" + canvas.getHeight());
            return 0;
        }
        c.setX(x);                              // New coordinates
        c.setY(y);
        gc.moveTo(x, y);
        return 1;
    }

    public int POS(Context context, double x, double y){
        Cursor c = context.getMainCursor();
        if(c == null) return 0;
        if( x > canvas.getWidth() || x < 0){
            System.out.println("You are out of borders, you cannot move outside of " + canvas.getWidth() + " ,x: "+x);
            return 0;
        }
        if( y > canvas.getHeight()  || y < 0){
            System.out.println("You are out of borders, you cannot move outside of " + canvas.getHeight() + " ,y: "+y);
            return 0;
        }
        gc.strokeLine(c.getX(), c.getY(), x, y);
        c.setX(x);                               // New coordinates
        c.setY(y);
        gc.moveTo(x, y);

        return 1;
    }

    public int PRESS(double value){
        if(value < 0 || value > 1){
            System.out.println("You must put a number between 0 and 1");
            return 0;
        }
        gc.setGlobalAlpha(value);
        return 1;
    }

    public int LOOKAT(Context context, double x, double y) {
        Cursor c = context.getMainCursor();
        if(c == null) return 0;
        if(c.getX() == x && c.getY() == y){
            c.setRotation(0);
            return 1;
        }
        double hyp = Math.sqrt((x - c.getX())*(x - c.getX()) + (y - c.getY())*(y - c.getY()));
        double opp = Math.sqrt((x - c.getX())*(x - c.getX()));
         c.setRotation(Math.toDegrees(Math.acos(opp/hyp)));
        return 1;
    }

    public int LOOKATCursor(Cursor c1, Cursor c2){
        //return LOOKAT(c1, c2.getX(), c2.getY(), 0);
        return 0;
    }

    public int THICK(double value){
        if(value < 0){
            return  0;
        }
        gc.setLineWidth(value);// Width definition
        return 1;
    }

}

