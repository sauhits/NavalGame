import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class GUI_DB {
        static int xEnemy, yEnemy;
        static int xSelf, ySelf;
        static int xCalculate, yCalculate;
        static boolean flagEnemy = false;
        static boolean flagSelf = false;
        static boolean flagStart = false;
        static boolean isFirst = false;
<<<<<<< HEAD
        static int[][] copyEnemyCoordinate = new int[5][5];
=======
        static int[][] copyEnemyCoodinate = new int[5][5];
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
        static int logCapacity = 5;
        static List<String> listLog = new ArrayList<String>(logCapacity);
        static int countLog = 1;
        static Font fontLog = new Font(20);
        static int localX, localY;

        // Enemy:ラベルの作成
<<<<<<< HEAD
        static Label labelEnemyNaval = Naval.createLabel("EnemyNaval", "Enemy Naval");
        // Enemy:座標を表示するラベルの作成
        static Label labelActiveEnemyCoordinate = Naval.createLabel("activeEnemyCoordinate", "none");
        // 表示記号の作成-----------------------------------
        static String[][] textsCoordinate = {
=======
        static Label labelEnemylNaval = Naval.createLabel("EnemyNaval", "Enemy Naval");
        // Enemy:座標を表示するラベルの作成
        static Label labelActiveEnemyCoodinate = Naval.createLabel("activeEnemyCoodinate", "none");
        // 表示記号の作成-----------------------------------
        static String[][] textsCoodinates = {
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
                        { "1,1", "2,1", "3,1", "4,1", "5,1" },
                        { "1,2", "2,2", "3,2", "4,2", "5,2" },
                        { "1,3", "2,3", "3,3", "4,3", "5,3" },
                        { "1,4", "2,4", "3,4", "4,4", "5,4" },
                        { "1,5", "2,5", "3,5", "4,5", "5,5" }
        };
        static String[][] textsCalculate = {
                        { "0", "0", "0", "0", "0" },
                        { "0", "0", "0", "0", "0" },
                        { "0", "0", "0", "0", "0" },
                        { "0", "0", "0", "0", "0" },
                        { "0", "0", "0", "0", "0" }
        };
        // Enemy:グリッドの作成
        static GridPane gridPaneEnemy = new GridPane();
<<<<<<< HEAD
        // Enemy:CoordinateButtonの作成-----------------------------------
        static Button[][] buttonEnemyCoordinates = new Button[5][5];
=======
        // Enemy:CoodinateButtonの作成-----------------------------------
        static Button[][] buttonEnemyCoodinates = new Button[5][5];
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
        // Enemy:stateButtonの作成
        static Button buttonHit = Naval.createButton("HIT", 83.3, 40.0);
        static Button buttonNot = Naval.createButton("NOT", 83.3, 40.0);
        static Button buttonNear = Naval.createButton("NEAR", 83.3, 40.0);
        // Enemy:moveボタンの作成
        static Button buttonEnemySouth_1 = Naval.createButton("South_1", 62.5, 40.0);
        static Button buttonEnemyNorth_1 = Naval.createButton("North:1", 62.5, 40.0);
        static Button buttonEnemyEast_1 = Naval.createButton("East:1", 62.5, 40.0);
        static Button buttonEnemyWest_1 = Naval.createButton("West:1", 62.5, 40.0);
        static Button buttonEnemySouth_2 = Naval.createButton("South:2", 62.5, 40.0);
        static Button buttonEnemyNorth_2 = Naval.createButton("North:2", 62.5, 40.0);
        static Button buttonEnemyEast_2 = Naval.createButton("East:2", 62.5, 40.0);
        static Button buttonEnemyWest_2 = Naval.createButton("West:2", 62.5, 40.0);
        // Calculate:ラベルの作成
        static Label labelCalculate = Naval.createLabel("calculate", "Calculate Now");
        // Calculate:ボタンの作成
<<<<<<< HEAD
        static Button[][] buttonCalculateCoordinates = new Button[5][5];
        // Calculate:グリッドの作成
        static GridPane gridpaneCalculate = new GridPane();
        // Self:ラベルの作成
        static Label labelSelf = Naval.createLabel("labelSelf", "Self Naval");
        static Label labelActiveSelfCoordinate = Naval.createLabel("selfCoordinate", "none");
        // Self:ボタンの作成
        static Button[][] buttonSelfCoordinates = new Button[5][5];
=======
        static Button[][] buttonCalculateCoodinates = new Button[5][5];
        // Calucurate:グリッドの作成
        static GridPane gridpaneCalculate = new GridPane();
        // Self:ラベルの作成
        static Label labelSelf = Naval.createLabel("labelSelf", "Self Nabel");
        static Label labelActiveSelfCoodinate = Naval.createLabel("selfCoodinate", "none");
        // Self:ボタンの作成
        static Button[][] buttonSelfCoodinates = new Button[5][5];
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
        static Button buttonSelfSet = Naval.createButton("SET", 125.0, 40.0);
        static Button buttonSelfFired = Naval.createButton("BE FIRED", 125.0, 40.0);
        // Self:gridpaneの作成
        static GridPane gridPaneSelf = new GridPane();
        // Log:ラベルの作成
        static Label labelLog = Naval.createLabel("labelLog", "LOG");
        // Log:リストラベルの作成
        static Label labelLog1 = new Label("none");
        static Label labelLog2 = new Label("none");
        static Label labelLog3 = new Label("none");
        static Label labelLog4 = new Label("none");
        static Label labelLog5 = new Label("none");
<<<<<<< HEAD
        // Next:ラベルの作成
        static Label labelNextTitle = Naval.createLabel("labelNextTitle","NEXT ->");
        static Label labelNextWay = Naval.createLabel("labelNextWay","none");

=======
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
}