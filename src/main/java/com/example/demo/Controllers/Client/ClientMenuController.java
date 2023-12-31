package com.example.demo.Controllers.Client;

import com.example.demo.Models.Model;
import com.example.demo.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button transaction_btn;
    public Button accounts_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners(){
        dashboard_btn.setOnAction(e->onMenuClick(ClientMenuOptions.DASHBOARD));
        transaction_btn.setOnAction(e->onMenuClick(ClientMenuOptions.TRANSACTIONS));
        accounts_btn.setOnAction(e->onMenuClick(ClientMenuOptions.ACCOUNTS));
        logout_btn.setOnAction(e->onLogout());
    }

    private void onMenuClick(Enum setName){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set((ClientMenuOptions) setName);
    }

    private void onLogout(){
        Stage stage = (Stage) dashboard_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();

        //setting flags
        Model.getInstance().setClientLoginSuccessFlag(false);

    }

}
