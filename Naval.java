
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Naval extends Application {
    static int xEnemy, yEnemy;
    static int xSelf, ySelf;
    static int stateEnemy;
    static boolean flagEnemy = false;
    static boolean flagSelf = false;
    static boolean flagStart = false;
    static int[][] copyEnemyCoodinate = new int[5][5];

    // =====================================================================================================================================
    // =====================================================================================================================================
    // Coodinateボタンの作成メソッド
    private Button createButtonCoodinate(String label, int row, int col, Label labelel, boolean isEnemy) {
        Button button = new Button(label);
        button.setFont(new Font(12));
        button.setMinSize(50, 50);
        button.setOnAction(event -> buttonCoodinateAction(row, col, labelel, isEnemy));
        return button;
    }

    // ラベル作成メソッド
    private Label createLabel(String labelName, String textOfFirst) {
        Label label = new Label(labelName);
        label.setText(textOfFirst);
        label.setFont(new Font(15));
        label.prefWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    // ボタンの作成メソッド
    private Button createButton(String text, Double width, Double height) {
        Button button = new Button(text);
        button.setPrefSize(width, height);
        return button;
    }

    // 座標を選択した際のアクションメソッド
    public static void buttonCoodinateAction(int x, int y, Label label, boolean isEnemy) {
        int affectationX = x + 1;
        int affectationY = y + 1;
        if (isEnemy) {
            xEnemy = x;
            yEnemy = y;
            flagEnemy = true;
        } else {
            xSelf = x;
            ySelf = y;
            flagSelf = true;
        }

        label.setText("(" + affectationX + "," + affectationY + ")");
        // // デバッグ用
        // System.out.println(xEnemy + "," + yEnemy);
        // System.out.println("state" + VariableDB.getEnemyEvaluation(xEnemy, yEnemy));
    }

    // Self:Setのアクションメソッド
    public static void buttonSetSelfAction(Label label, Button[][] button) {
        int switchSetSelf = 1;
        // 船のカウンタ
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (VariableDB.getSelfHealth(i, j) != 0)
                    switchSetSelf += 1;
            }
        }
        if (switchSetSelf < 5) {
            String textSetSelf;
            if (flagSelf) {
                flagSelf = false;
                VariableDB.newSelfCoodinate(ySelf, xSelf);
                button[ySelf][xSelf].setStyle("-fx-base: #f4f162");
                textSetSelf = "現在" + switchSetSelf + "隻";
            } else {
                textSetSelf = "座標を選択してください";
            }
            label.setText(textSetSelf);
        } else {
            label.setText("4隻を超える船は設置できません");
        }
    }

    // Self:Firedのアクションメソッド
    public static void buttonSelfFiredAction(Label label, Button[][] buttons) {
        if (flagSelf) {
            flagSelf = false;
            VariableDB.setSelfHealth(xSelf, ySelf, VariableDB.getSelfHealth(xSelf, ySelf) - 1);
            buttonStateEnemyAction(xSelf, ySelf, 1, true, label, buttons);
        } else {
            label.setText("座標を選択してください");
        }
    }

    // Enemy:Moveのアクションメソッド
    public static void buttonEnemyMoveAction(int compass, int gain, Button[][] button, Label label) {
        // 指定した方向にずらしたCopyCoodinateを作成
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int affectationX = i, affectationY = j;
                switch (compass) {
                    case 0:
                        affectationY = j - gain;
                        break;// North
                    case 1:
                        affectationX = i + gain;
                        break;// East
                    case 2:
                        affectationY = j + gain;
                        break;// South
                    case 3:
                        affectationX = i - gain;
                        break;// West
                    default:
                        System.out.println("Erorr:compass");
                        break;
                }
                if (affectationX >= 0 && affectationX < 5 && affectationY >= 0 && affectationY < 5) {
                    if (VariableDB.getEnemyEvaluation(i, j) != 0)
                        copyEnemyCoodinate[affectationX][affectationY] = 1;
                } else {
                    System.out.println("Error:rangeOfCopy");
                }
            }
        }
        // Copyをマージ
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                copyEnemyCoodinate[i][j] += VariableDB.getEnemyEvaluation(i, j);
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                VariableDB.setEnemyEvaluation(i, j, copyEnemyCoodinate[i][j]);
            }
        }
        updadeCalcurateCoodinate(button, label);
    }

    // Calculate:Coodinateの更新
    private static void updadeCalcurateCoodinate(Button[][] button, Label label) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                button[i][j].setText(String.valueOf(VariableDB.getEnemyEvaluation(i, j)));
                label.setText("Success!");
            }
        }
    }

    // Enemy:stateのアクションメソッド
    public static void buttonStateEnemyAction(int x, int y, int state, boolean flag, Label label, Button[][] button) {
        if (flag) {
            flagEnemy = false;
            VariableDB.setEnemyCoodinate(x, y, state);
            updadeCalcurateCoodinate(button, label);
        } else {
            label.setText("座標を選択してください");
        }
    }

    // =====================================================================================================================================
    // =====================================================================================================================================
    @Override
    public void start(Stage stage) {
        // Enemy:ラベルの作成
        Label labelEnemylNaval = createLabel("EnemyNaval", "Enemy Naval");
        // Enemy:座標を表示するラベルの作成
        Label labelActiveEnemyCoodinate = createLabel("activeEnemyCoodinate", "none");
        // 表示記号の作成-----------------------------------
        String[][] textsCoodinates = {
                { "1,1", "2,1", "3,1", "4,1", "5,1" },
                { "1,2", "2,2", "3,2", "4,2", "5,2" },
                { "1,3", "2,3", "3,3", "4,3", "5,3" },
                { "1,4", "2,4", "3,4", "4,4", "5,4" },
                { "1,5", "2,5", "3,5", "4,5", "5,5" }
        };
        // Enemy:Gridpaneの作成
        GridPane gridPaneEnemy = new GridPane();
        // Enemy:CoodinateButtonの作成-----------------------------------
        Button[][] buttonEnemyCoodinates = new Button[5][5];
        // Enemy:stateButtonの作成
        Button buttonHit = createButton("HIT", 83.3, 40.0);
        Button buttonNot = createButton("NOT", 83.3, 40.0);
        Button buttonNear = createButton("NEAR", 83.3, 40.0);
        // Enemy:moveボタンの作成
        Button buttonEnemySouth_1 =createButton("South_1", 62.5, 40.0);
        Button buttonEnemyNorth_1 = createButton("North:1", 62.5, 40.0);
        Button buttonEnemyEast_1 = createButton("East:1", 62.5, 40.0);
        Button buttonEnemyWest_1 = createButton("West:1", 62.5, 40.0);
        Button buttonEnemySouth_2 = createButton("South:2", 62.5, 40.0);
        Button buttonEnemyNorth_2 = createButton("North:2", 62.5, 40.0);
        Button buttonEnemyEast_2 = createButton("East:2", 62.5, 40.0);
        Button buttonEnemyWest_2 = createButton("West:2", 62.5, 40.0);
        // Calcurate:ラベルの作成
        Label labelCalcurate = createLabel("calcurate", "Calcurate Now");
        // Calcurate:ボタンの作成
        Button[][] buttonCalcurateCoodinates = new Button[5][5];
        // Calucurate:グリットの作成
        GridPane gridpaneCalcurate = new GridPane();
        // Self:ラベルの作成
        Label labelSelf = createLabel("labelSelf", "Self Nabel");
        Label labelActiveSelfCoodinate = createLabel("selfCoodinate", "none");
        // Self:ボタンの作成
        Button[][] buttonSelfCoodinates = new Button[5][5];
        Button buttonSelfSet = createButton("SET", 125.0,40.0);
        Button buttonSelfFired =  createButton("BE FIRED", 125.0,40.0);
        // Self:gridpaneの作成
        GridPane gridPaneSelf = new GridPane();
        // =====================================================================================================================================
        // =====================================================================================================================================
        // Enemy:CoodinateButtonの設定
        for (int l = 0; l < 5; l++) {
            for (int j = 0; j < 5; j++) {
                buttonEnemyCoodinates[l][j] = createButtonCoodinate(textsCoodinates[l][j], j, l,
                        labelActiveEnemyCoodinate,true);
            }
        }
        // // Enemy:Gridpaneの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                GridPane.setConstraints(buttonEnemyCoodinates[i][j], j, i);}
            
            gridPaneEnemy.getChildren().addAll(buttonEnemyCoodinates[i]);
        }
        // Enemy:stateButtonの設定
        buttonHit.setOnAction(
                event -> buttonStateEnemyAction(xEnemy, yEnemy, 2, flagEnemy, labelActiveEnemyCoodinate,
                        buttonCalcurateCoodinates));
        buttonNot.setOnAction(
                event -> buttonStateEnemyAction(xEnemy, yEnemy, 0, flagEnemy, labelActiveEnemyCoodinate,
                        buttonCalcurateCoodinates));
        buttonNear.setOnAction(
                event -> buttonStateEnemyAction(xEnemy, yEnemy, 1, flagEnemy, labelActiveEnemyCoodinate,
                        buttonCalcurateCoodinates));
        // Enemy:Moveボタンの設定
        buttonEnemyNorth_1
                .setOnAction(event -> buttonEnemyMoveAction(0, 1, buttonCalcurateCoodinates, labelEnemylNaval));
        buttonEnemyEast_1
                .setOnAction(event -> buttonEnemyMoveAction(1, 1, buttonCalcurateCoodinates, labelEnemylNaval));
        buttonEnemySouth_1
                .setOnAction(event -> buttonEnemyMoveAction(2, 1, buttonCalcurateCoodinates, labelEnemylNaval));
        buttonEnemyWest_1
                .setOnAction(event -> buttonEnemyMoveAction(3, 1, buttonCalcurateCoodinates, labelEnemylNaval));
        // Enemy:Move2ボタンの設定
        buttonEnemyNorth_2
                .setOnAction(event -> buttonEnemyMoveAction(0, 2, buttonCalcurateCoodinates, labelEnemylNaval));
        buttonEnemyEast_2
                .setOnAction(event -> buttonEnemyMoveAction(1, 2, buttonCalcurateCoodinates, labelEnemylNaval));
        buttonEnemySouth_2
                .setOnAction(event -> buttonEnemyMoveAction(2, 2, buttonCalcurateCoodinates, labelEnemylNaval));
        buttonEnemyWest_2
                .setOnAction(event -> buttonEnemyMoveAction(3, 2, buttonCalcurateCoodinates, labelEnemylNaval));
        // Calcurate:ボタンの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Button button = new Button();
                button.setFont(new Font(12));
                button.setMinSize(50, 50);
                buttonCalcurateCoodinates[i][j] = button;
            }
        }
        // Calcurate:グリットの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                GridPane.setConstraints(buttonCalcurateCoodinates[i][j], i, j);
            }
            gridpaneCalcurate.getChildren().addAll(buttonCalcurateCoodinates[i]);
        }
        // Self:ボタンの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttonSelfCoodinates[i][j] = createButtonCoodinate(textsCoodinates[i][j], j, i,
                        labelActiveSelfCoodinate,false);
            }
        }
        // Self:gridpaneの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                GridPane.setConstraints(buttonSelfCoodinates[i][j], j, i);
            }
            gridPaneSelf.getChildren().addAll(buttonSelfCoodinates[i]);
        }
        // Self:Setボタンの設定
        buttonSelfSet.setOnAction(event -> buttonSetSelfAction(labelActiveSelfCoodinate, buttonSelfCoodinates));
        // Self:Firedボタンの設定
        buttonSelfFired
                .setOnAction(event -> buttonSelfFiredAction(labelActiveSelfCoodinate, buttonCalcurateCoodinates));
        // =====================================================================================================================================
        // =====================================================================================================================================
        // Enemy:GUIレイアウトを作成
        HBox hboxStateButton = new HBox(buttonHit, buttonNot, buttonNear);
        HBox hBoxMove_1Button = new HBox(buttonEnemyNorth_1, buttonEnemyEast_1, buttonEnemySouth_1, buttonEnemyWest_1);
        HBox hBoxMove_2Button = new HBox(buttonEnemyNorth_2, buttonEnemyEast_2, buttonEnemySouth_2, buttonEnemyWest_2);
        VBox vBoxEnemy = new VBox(labelEnemylNaval, gridPaneEnemy, labelActiveEnemyCoodinate, hboxStateButton,
                hBoxMove_1Button, hBoxMove_2Button);
        // Enemy:GUIレイアウトの設定
        hBoxMove_1Button.setPadding(new Insets(5, 0, 5, 0));
        vBoxEnemy.setPadding(new Insets(10));
        // Calucurate:GUIレイアウトの作成
        VBox vboxCalucurate = new VBox(labelCalcurate, gridpaneCalcurate);
        // Calcurate:GUIレイアウトの設定
        vboxCalucurate.setPadding(new Insets(10));
        // Self:GUIレイアウトを作成
        HBox hBoxSetFired = new HBox(buttonSelfSet, buttonSelfFired);
        VBox vBoxSelf = new VBox(labelSelf, gridPaneSelf, labelActiveSelfCoodinate, hBoxSetFired);
        // Self:GUIレイアウトの設定
        vBoxSelf.setPadding(new Insets(10));
        // 全体のレイアウトをマージする
        HBox hboxEnemyToSelf = new HBox(vBoxEnemy, vboxCalucurate, vBoxSelf);
        hboxEnemyToSelf.setSpacing(5.0);
        // =====================================================================================================================================
        // =====================================================================================================================================
        stage.setWidth(1200.);
        stage.setHeight(600.);
        stage.setResizable(false);
        stage.setTitle("Naval Game");
        stage.setScene(new Scene(hboxEnemyToSelf));
        stage.show();
    }

    public static void main(String[] args) {
        VariableDB.fillArrayFigure(VariableDB.enemyCoodinate, -1);
        Application.launch(args);
    }
}
