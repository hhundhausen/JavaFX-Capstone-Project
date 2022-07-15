package sample.view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sample.controller.Controller;
import sample.model.Review;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ModifyScene extends Scene {
    private Scene mPrevScene;
    private Review mReviewToModify;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;

    private Controller controller = Controller.getInstance();
    private ObservableList<Review> allReviewList;
    private Controller selectedController;

    //TextFields and Labels
    private TextField reviewTypeTF = new TextField();
    private ComboBox<String> reviewTypeCB;
    private Label reviewErrLabel  = new Label("Type is required.");

    private TextField companyNameTF = new TextField();
    private Label companyNameLabel = new Label("Renting type: ");
    private Label companyNameErrLabel = new Label("Company/Name is required");

    private TextField addressTF = new TextField();
    private Label addressErrLabel = new Label("Address is required.");

    private TextField communicationRatingTF   = new TextField();
    private TextField neighborhoodRatingTF    = new TextField();
    private TextField conditionRatingSliderTF = new TextField();
    private TextField overallRatingSliderTF   = new TextField();

    private TextField rentTF   = new TextField();
    private Label rentErrLabel = new Label("Amount of Rent Per Month \nis Required.");

    private TextField notesTF   = new TextField();
    private Button modifyButton = new Button("* Modify");
    private Button cancelButton = new Button ("Cancel");

    public ModifyScene(Scene prevScene, Review reviewToModify)
    {
        super(new GridPane(), WIDTH, HEIGHT);
        mPrevScene = prevScene;
        mReviewToModify = reviewToModify;

        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        pane.add(new Label("Renters Type:"), 0, 0);
        reviewTypeTF.setText(mReviewToModify.getmType());
        pane.add(reviewTypeTF, 1, 0);
        pane.add(reviewErrLabel, 2, 0);
        reviewErrLabel.setTextFill(Color.RED);
        reviewErrLabel.setVisible(false);

        pane.add(new Label("Company/Name:"), 0, 1);
        companyNameTF.setText(mReviewToModify.getmCompanyName());
        pane.add(companyNameTF, 1, 1);
        pane.add(companyNameErrLabel, 2, 1);
        companyNameErrLabel.setTextFill(Color.RED);
        companyNameErrLabel.setVisible(false);

        pane.add(new Label("Address:"), 0, 2);
        addressTF.setText(mReviewToModify.getmAddress());
        pane.add(addressTF, 1, 2);
        pane.add(addressErrLabel, 2, 2);
        addressErrLabel.setTextFill(Color.RED);
        addressErrLabel.setVisible(false);

        pane.add(new Label("Communication Rating [0-5]:"), 0, 3);
        pane.add(communicationRatingTF, 1, 3);

        pane.add(new Label("Neighborhood Rating [0-5]:"), 0, 4);
        pane.add(neighborhoodRatingTF, 1, 4);

        pane.add(new Label("Condition Rating [0-5]:"), 0, 5);
        pane.add(conditionRatingSliderTF, 1, 5);

        pane.add(new Label("Overall Rating [0-5]:"), 0, 6);
        pane.add(overallRatingSliderTF, 1, 6);

        pane.add(new Label("Rent $"), 0, 7);
        rentTF.setText(mReviewToModify.getmRent());
        pane.add(rentTF, 1, 7);
        pane.add(rentErrLabel, 2, 7);
        rentErrLabel.setTextFill(Color.RED);
        rentErrLabel.setVisible(false);

        pane.add(new Label("Notes:"), 0, 8);
        pane.add(notesTF, 1, 8);

        pane.add(cancelButton, 0, 9);
        pane.add(modifyButton, 1, 9);

        modifyButton.setOnAction(event -> modify());
        cancelButton.setOnAction(event -> goBackToPrevScene());
        this.setRoot(pane);
    }

    private void modify() {
        String type, companyName, address, rent, notes;
        double communicationRate = 0.0, neighborhoodRate = 0.0, conditionRate = 0.0, overallRating = 0.0;

        type = reviewTypeTF.getText();
        reviewErrLabel.setVisible(type.isEmpty());

        companyName = companyNameTF.getText();
        companyNameLabel.setVisible(companyName.isEmpty());

        address = addressTF.getText();
        addressErrLabel.setVisible(address.isEmpty());

        notes = notesTF.getText();  // Notes are optional, can be empty

        rent = rentTF.getText();
        rentErrLabel.setVisible(rent.isEmpty());

        try {
            communicationRate = Double.parseDouble(communicationRatingTF.getText());
        }
        catch (NumberFormatException e)
        {
            // Quietly ignored, since rating can be empty
        }
        try{
            neighborhoodRate = Double.parseDouble(neighborhoodRatingTF.getText());
        }
        catch(NumberFormatException e)
        {

        }
        try{
            conditionRate = Double.parseDouble(communicationRatingTF.getText());
        }
        catch(NumberFormatException e)
        {

        }
        try{
            overallRating = Double.parseDouble(overallRatingSliderTF.getText());
        }
        catch(NumberFormatException e)
        {

        }

        //rentLabel.setVisible(rentTF.getText().isEmpty());

        if (reviewErrLabel.isVisible() || companyNameLabel.isVisible() || addressErrLabel.isVisible() || rentErrLabel.isVisible())
            return;

        //type, companyName, address, communicationRating, neighborhoodRating, conditionRating, overallRating, rent, notes)
        mReviewToModify.setmType(type);
        mReviewToModify.setmCompanyName(companyName);
        mReviewToModify.setmAddress(address);
        mReviewToModify.setmCommunicationRating(communicationRate);
        mReviewToModify.setmNeighborhoodRating(neighborhoodRate);
        mReviewToModify.setmConditionRating(conditionRate);
        mReviewToModify.setmOverallRating(overallRating);
        mReviewToModify.setmRent(rent);
        mReviewToModify.setmNotes(notes);
        goBackToPrevScene();
    }

    private void goBackToPrevScene() {
        ViewNavigator.loadScene("Modify Review", mPrevScene);
    }

}
