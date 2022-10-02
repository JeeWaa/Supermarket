package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {
    public JFXButton btnCustomer;
    public JFXButton btnItem;
    public JFXButton btnOrder;
    public JFXButton btnNo;

    public void mouseClick(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof JFXButton){
            JFXButton jfxButton = (JFXButton) mouseEvent.getSource();

            Parent root =null;

            switch (jfxButton.getId()){
                case "btnCustomer":
                    root = FXMLLoader.load(this.getClass().getResource("../View/ManageCustomerForm.fxml"));
                    break;
                case "btnItem":
                    root = FXMLLoader.load(this.getClass().getResource("../View/ManageItemForm.fxml"));
                    break;
                case "btnOrder":
                    root = FXMLLoader.load(this.getClass().getResource("../View/OrderForm.fxml"));
                    break;
                case "btnNo":
                    root = null;
                    break;
            }

            if (root != null){
                Parent load = root;
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        }
    }
}
