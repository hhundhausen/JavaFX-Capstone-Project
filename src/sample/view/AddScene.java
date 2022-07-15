package sample.view;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import sample.controller.Controller;
import sample.model.PrivateLandLord;
import sample.model.PropertyManagementCompanies;
import sample.model.Review;
import java.awt.*;
public class AddScene extends Scene {
    private Scene mPrevScene;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    private ImageView reviewIV = new ImageView();
    private Controller controller = Controller.getInstance();
    private ObservableList<Review> allReviewsList;

    private TextField reviewTypeTF = new TextField();
    private ComboBox<String> reviewTypeCB;
    private Label     reviewErrLabel  = new Label("Type is required.");

    private TextField companyNameTF = new TextField();
    private Label companyNameLabel = new Label("Renting type: ");
    private Label companyNameErrLabel = new Label("Company/Name is required");

    private TextField addressTF = new TextField();
    private Label addressErrLabel = new Label("Address is required.");

    private TextField communicationRatingTF   = new TextField();
    private TextField neighborhoodRatingTF    = new TextField();
    private TextField conditionRatingSliderTF = new TextField();
    private TextField overallRatingSliderTF   = new TextField();

    private TextField rentTF = new TextField();
    private Label rentErrLabel = new Label("Amount of Rent Per Month is Required.");

    private TextField notesTF = new TextField();
    //private Label notesErrLabel = new Label("Notes is required.");

    private Button saveButton = new Button("+ Save");
    private Button cancelButton = new Button ("- Cancel");


    public AddScene(Scene prevScene) {
        super(new GridPane(), WIDTH, HEIGHT);
        mPrevScene = prevScene;

        GridPane pane = new GridPane();
        pane.setHgap(50.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        pane.add(new Label("Renters Type:"), 0, 0);
        reviewTypeCB = new ComboBox<>(controller.getDistinctType());
        reviewTypeCB.getSelectionModel().select(0);
        pane.add(reviewTypeCB, 1, 0);
        //pane.add(reviewTypeTF, 1, 0);
        pane.add(reviewErrLabel, 2, 0);
        reviewErrLabel.setTextFill(Color.RED);
        reviewErrLabel.setVisible(false);


        //pane.add(reviewIV, 0, 0, 3, 1);
        //reviewTypeCB.getItems().add(" ");
        //this will not work because it is never null

        reviewTypeCB.getSelectionModel().selectedItemProperty().addListener((observableValue, review, t1) -> selectedReview(t1));

        pane.add(companyNameLabel, 0, 1);
        pane.add(companyNameTF, 1, 1);
        pane.add(companyNameErrLabel, 2, 1);
        companyNameErrLabel.setTextFill(Color.RED);
        companyNameErrLabel.setVisible(false);

        pane.add(new Label("Address:"), 0, 2);
        pane.add(addressTF, 1, 2);
        pane.add(addressErrLabel, 2, 2);
        addressErrLabel.setTextFill(Color.RED);
        addressErrLabel.setVisible(false);

        pane.add(new Label("Communication \nRating [0-5]:"), 0, 3);
        pane.add(communicationRatingTF, 1, 3);


        //Label neighborhoodLabel = new Label("Neighborhood Rating [0-5]:");
        //neighborhoodLabel.setMinWidth(50);
        //pane.add(neighborhoodLabel, 0, 4);
        pane.add(new Label("Neighborhood \nRating [0-5]:"), 0, 4);
        pane.add(neighborhoodRatingTF, 1, 4);


        pane.add(new Label("Condition Rating \n[0-5]:"), 0, 5);
        pane.add(conditionRatingSliderTF, 1, 5);

        pane.add(new Label("Overall Rating \n[0-5]:"), 0, 6);
        pane.add(overallRatingSliderTF, 1, 6);

        pane.add(new javafx.scene.control.Label("Rent $"), 0, 7);
        pane.add(rentTF, 1, 7);
        pane.add(rentErrLabel, 2, 7);
        rentErrLabel.setTextFill(Color.RED);
        rentErrLabel.setVisible(false);


        pane.add(new javafx.scene.control.Label("Notes:"), 0, 8);
        pane.add(notesTF, 1, 8);

        pane.add(cancelButton, 0, 9);
        pane.add(saveButton, 1, 9);

        saveButton.setOnAction(event -> save());
        cancelButton.setOnAction(event -> goBackToPrevScene());
        this.setRoot(pane);
    }

    private void selectedReview(String t1)
    {
        System.out.println(t1);
        if(t1 != null)
        {
            if(t1.equals("Private Landlord"))
                companyNameLabel.setText("Landlords \nName:");
            else
                companyNameLabel.setText("ManagementCO:");
        }
    }


    private void save() {
        String type, companyName, rName = null, address, rent, notes;
        double communicationRating=0.0, neighborhoodRating=0.0, conditionRating=0.0, overallRating=0.0;

        type = reviewTypeCB.getSelectionModel().getSelectedItem();
        reviewErrLabel.setVisible(type.isEmpty());

        companyName = companyNameTF.getText();
        companyNameErrLabel.setVisible(companyName.isEmpty());

        address = addressTF.getText();
        addressErrLabel.setVisible(address.isEmpty());

        rent = rentTF.getText();
        rentErrLabel.setVisible(rent.isEmpty());

        notes = notesTF.getText();  // Notes are optional, can be empty

        try {
            communicationRating = Double.parseDouble(communicationRatingTF.getText());
        }
        catch (NumberFormatException e) {
            // Quietly ignored, since rating can be empty
        }
        try {
            neighborhoodRating = Double.parseDouble(neighborhoodRatingTF.getText());
        }
        catch (NumberFormatException e) {
            // Quietly ignored, since rating can be empty
        }
        try {
            conditionRating = Double.parseDouble(conditionRatingSliderTF.getText());
        }
        catch (NumberFormatException e) {
            // Quietly ignored, since rating can be empty
        }
        try {
            overallRating = Double.parseDouble(overallRatingSliderTF.getText());
        }
        catch (NumberFormatException e) {
            // Quietly ignored, since rating can be empty
        }
        //priceLabel.setVisible(priceTF.getText().isEmpty());

        if(rentErrLabel.isVisible() || companyNameErrLabel.isVisible() || addressErrLabel.isVisible() || rentErrLabel.isVisible())
            return; 
        
        if(reviewTypeCB.getSelectionModel().getSelectedItem().equals("Private Landlord"))
            controller.getmAllReviews().add(new PrivateLandLord(type, rName, companyName, address, communicationRating, 
                    neighborhoodRating, conditionRating, overallRating, rent, notes)); 
        else    
            controller.getmAllReviews().add(new PropertyManagementCompanies(type, rName, companyName, address, communicationRating, 
                    neighborhoodRating, conditionRating, overallRating, rent, notes)); 

        goBackToPrevScene();
    }

    private void goBackToPrevScene() {
        ViewNavigator.loadScene("Add Review", mPrevScene);
    }

}
