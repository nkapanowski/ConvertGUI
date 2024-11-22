import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ConvertGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
       
        Pane pane = new Pane();

        Label inputLabel = new Label("Enter Value (or type 'exit'):");
        inputLabel.setLayoutX(20);
        inputLabel.setLayoutY(20);

        TextField inputField = new TextField();
        inputField.setLayoutX(200);
        inputField.setLayoutY(20);

        // Dropdown 
        Label conversionLabel = new Label("Conversion Type:");
        conversionLabel.setLayoutX(20);
        conversionLabel.setLayoutY(60);

        ComboBox<String> conversionBox = new ComboBox<>();
        conversionBox.getItems().addAll(
            "Kilograms to Pounds",
            "Meters to Feet",
            "Celsius to Fahrenheit",
            "Liters to Gallons"
        );
        conversionBox.setValue("Kilograms to Pounds"); // Default 
        conversionBox.setLayoutX(200);
        conversionBox.setLayoutY(60);

        // Convert Button
        Button convertButton = new Button("Convert");
        convertButton.setLayoutX(20);
        convertButton.setLayoutY(100);

        // Result Label
        Label resultLabel = new Label("Result: ");
        resultLabel.setLayoutX(20);
        resultLabel.setLayoutY(140);

        // Action for Convert Button
        convertButton.setOnAction(e -> {
            String inputText = inputField.getText();

            // Check if user wants to exit
            if (inputText.equalsIgnoreCase("exit")) {
                primaryStage.close(); // Close the application
                return;
            }

            try {
                // Get user input
                double inputValue = Double.parseDouble(inputText);
                String conversionType = conversionBox.getValue();

                // Perform the conversion
                double result = performConversion(inputValue, conversionType);

                // Result
                resultLabel.setText(String.format("Result: %.2f", result));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input! Please enter a number or 'exit'.");
            }
        });

        // Add all components to the pane
        pane.getChildren().addAll(inputLabel, inputField, conversionLabel, conversionBox, convertButton, resultLabel);

        // Scene
        Scene scene = new Scene(pane, 450, 200);
        primaryStage.setTitle("Metric Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // Conversion
    private double performConversion(double value, String conversionType) {
        switch (conversionType) {
            case "Kilograms to Pounds":
                return value * 2.20462;
            case "Meters to Feet":
                return value * 3.28084;
            case "Celsius to Fahrenheit":
                return (value * 1.8) + 32;
            case "Liters to Gallons":
                return value * 0.26417;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
