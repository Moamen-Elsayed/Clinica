package clinic;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Clinic extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ArrayList<String> tables = new ArrayList<>();
        tables.add("CREATE TABLE IF NOT EXISTS admin (username varchar(20) primary key, password varchar(20) ,fk_d_id int ,\n" +
"constraint fk9 foreign key (fk_d_id ) references doctor (d_id) on delete cascade);");
        tables.add("CREATE TABLE IF NOT EXISTS doctor (d_id INTEGER primary key AUTOINCREMENT, name varchar(20), phone varchar(11), Email varchar(30), sp varchar(30));");
        tables.add("CREATE TABLE IF NOT EXISTS visit (v_id INTEGER primary key AUTOINCREMENT, type varchar(20), cost number(4));");
        tables.add("CREATE TABLE IF NOT EXISTS patient (p_id INTEGER primary key AUTOINCREMENT, p_name varchar(255), age number(3), med_history text, gender varchar(6), district varchar(100));");
        tables.add("CREATE TABLE IF NOT EXISTS drug (d_id INTEGER primary key AUTOINCREMENT,  d_name varchar(20));");
        tables.add("CREATE TABLE IF NOT EXISTS test (t_id INTEGER primary key AUTOINCREMENT, t_name varchar(50)); ");
        tables.add("CREATE TABLE IF NOT EXISTS checkup (ch_id INTEGER primary key AUTOINCREMENT, ch_date date , end_date date , cost number(4), notes text,report text,fk_v_id int,\n" +
"constraint fk12 foreign key (fk_v_id ) references visit (v_id) on delete cascade);");
        tables.add("CREATE TABLE IF NOT EXISTS p_phones (phone_id INTEGER primary key AUTOINCREMENT ,phone_no varchar(11) ,fk_p_id INT"
                + ", constraint fk13 foreign key (fk_p_id ) references patient (p_id) on delete cascade )");
        tables.add("CREATE TABLE IF NOT EXISTS P_D (fk_p_id int , fk_dr_id int ,\n" +
"constraint pk1 primary key (fk_p_id  , fk_dr_id ),\n" +
"constraint fk1 foreign key (fk_p_id ) references patient (p_id) on delete cascade ,\n" +
"constraint fk2 foreign key (fk_dr_id ) references doctor (d_id) on delete cascade );");
        tables.add("CREATE TABLE IF NOT EXISTS P_check (fk_p_id int , fk_ch_id int ,\n" +
"constraint pk2 primary key (fk_p_id  , fk_ch_id ),\n" +
"constraint fk3 foreign key (fk_p_id ) references patient (p_id) on delete cascade ,\n" +
"constraint fk4 foreign key (fk_ch_id ) references checkup (ch_id) on delete cascade );");
        tables.add("CREATE TABLE IF NOT EXISTS check_test (fk_ch_id int , fk_t_id int ,\n" +
"constraint pk3 primary key (fk_ch_id  , fk_t_id ),\n" +
"constraint fk5 foreign key (fk_ch_id ) references checkup (ch_id) on delete cascade ,\n" +
"constraint fk6 foreign key (fk_t_id ) references test (t_id) on delete cascade );");
        tables.add("CREATE TABLE IF NOT EXISTS check_drug (fk_ch_id int , fk_d_id int ,\n" +
"constraint pk4 primary key (fk_ch_id  , fk_d_id),\n" +
"constraint fk7 foreign key (fk_ch_id ) references checkup (ch_id) on delete cascade ,\n" +
"constraint fk8 foreign key (fk_d_id ) references drug (d_id) on delete cascade );");
        tables.add("CREATE TABLE IF NOT EXISTS d_check (fk_d_id int , fk_ch_id int ,\n" +
"constraint pk5 primary key (fk_d_id  , fk_ch_id ),\n" +
"constraint fk10 foreign key (fk_d_id ) references doctor (d_id) on delete cascade ,\n" +
"constraint fk11 foreign key (fk_ch_id ) references checkup (ch_id) on delete cascade );");
        Database db = new Database();
        db.initializeDB(tables);
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
