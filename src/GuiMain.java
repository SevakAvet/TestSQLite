import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class GuiMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, 400,300);
		
		TextArea area = new TextArea();
		area.setLayoutX(0);
		area.setLayoutY(0);
		area.setMinWidth(400);
		area.setMinHeight(300);
		root.getChildren().add(area);
		
		MyDataBase db = new MyDataBase();
		db.addIntoDB("СЕВАК", "КУЛХАЦКЕР");
		db.addIntoDB("ТЕСТ", "ПРОФЕССИЯ");
		
		List<String> all = db.getAllFromDB();
		for(String s : all) {
			area.appendText(s);
			area.appendText("\n");
		}
		
		stage.setScene(scene);
		stage.show();
	}
}
