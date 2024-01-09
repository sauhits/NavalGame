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
    // Coodinateボタンの作成メソッド
    public static Button createButtonCoodinate(String label, int row, int col, Label labelel,
            String EnemySelfCalculate) {
        Button button = new Button(label);
        button.setFont(new Font(12));
        button.setMinSize(50, 50);
        button.setOnAction(event -> buttonCoodinateAction(row, col, labelel, EnemySelfCalculate));
        return button;
    }

    // ラベル作成メソッド
    public static Label createLabel(String labelName, String textOfFirst) {
        Label label = new Label(labelName);
        label.setText(textOfFirst);
        label.setFont(new Font(15));
        label.prefWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    // ボタンの作成メソッド
    public static Button createButton(String text, Double width, Double height) {
        Button button = new Button(text);
        button.setPrefSize(width, height);
        return button;
    }

    // 座標を選択した際のアクションメソッド
    public static void buttonCoodinateAction(int x, int y, Label label, String EnemySelfCalcuration) {
        int affectationX = x + 1;
        int affectationY = y + 1;
        switch (EnemySelfCalcuration) {
            case "Enemy":
                GUI_DB.xEnemy = x;
                GUI_DB.yEnemy = y;
                GUI_DB.flagEnemy = true;
                break;
            case "Self":
                GUI_DB.xSelf = x;
                GUI_DB.ySelf = y;
                GUI_DB.flagSelf = true;
                break;
            case "Calculate":
                GUI_DB.xCalculate = x;
                GUI_DB.yCalculate = y;
                break;
            default:
                System.out.println("Erorr: buttonCoodinateAction");
                break;
        }
        if (label != null) {
            label.setText("(" + affectationX + "," + affectationY + ")");
        }
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
            if (GUI_DB.flagSelf) {
                GUI_DB.flagSelf = false;
                if (switchSetSelf == 4) {
                    GUI_DB.flagStart = true;
                }
                VariableDB.newSelfCoodinate(GUI_DB.xSelf, GUI_DB.ySelf);
                button[GUI_DB.xSelf][GUI_DB.ySelf].setStyle("-fx-base: #62f478");
                textSetSelf = "現在" + switchSetSelf + "隻";
                GUI_DB.localX = GUI_DB.xSelf + 1;
                GUI_DB.localY = GUI_DB.ySelf + 1;
                GUI_DB.listLog.add("(" + GUI_DB.localX + "," + GUI_DB.localY + ") STATE: SET");
                updateListLog();
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
        if (GUI_DB.flagSelf) {
            GUI_DB.flagSelf = false;
            // 先攻後攻の確認
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (VariableDB.getEnemyEvaluation(i, j) != -1) {
                        GUI_DB.isFirst = true;
                        break;
                    }
                }
            }

            VariableDB.setSelfHealth(GUI_DB.xSelf, GUI_DB.ySelf,
                    VariableDB.getSelfHealth(GUI_DB.xSelf, GUI_DB.ySelf) - 1);
            buttonStateEnemyAction(GUI_DB.xSelf, GUI_DB.ySelf, 1, true, label, buttons, true);
            int localX = GUI_DB.xSelf + 1;
            int localY = GUI_DB.ySelf + 1;
            GUI_DB.listLog.add("(" + localX + "," + localY + ") STATE: Be Fired");
            updateListLog();
        } else {
            label.setText("座標を選択してください");
        }
    }

    // Enemy:Moveのアクションメソッド
    public static void buttonEnemyMoveAction(int compass, int moveGain, Button[][] button, Label label) {
        //
        int evaluationGain = 2;
        VariableDB.fillArrayFigure(GUI_DB.copyEnemyCoodinate, 0);
        // 指定した方向にずらしたCopyCoodinateを作成
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int affectationX = i, affectationY = j;
                switch (compass) {
                    case 0:
                        affectationY = j - moveGain;
                        break;// North
                    case 1:
                        affectationX = i + moveGain;
                        break;// East
                    case 2:
                        affectationY = j + moveGain;
                        break;// South
                    case 3:
                        affectationX = i - moveGain;
                        break;// West
                    default:
                        System.out.println("Erorr:compass");
                        break;
                }
                if (affectationX >= 0 && affectationX < 5 && affectationY >= 0 && affectationY < 5) {
                    if (VariableDB.getEnemyEvaluation(i, j) >= evaluationGain)
                        GUI_DB.copyEnemyCoodinate[affectationX][affectationY] = 1;
                }
                // else {
                // System.out.println("Error:rangeOfCopy");
                // }
            }
        }
        // for (int i = 0; i < 5; i++) {
        // for (int j = 0; j < 5; j++) {
        // System.out.print(GUI_DB.copyEnemyCoodinate[i][j]);
        // }
        // System.out.println();
        // }
        System.out.println();
        // Copyをマージ
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                GUI_DB.copyEnemyCoodinate[i][j] += VariableDB.getEnemyEvaluation(i, j);
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                VariableDB.setEnemyEvaluation(i, j, GUI_DB.copyEnemyCoodinate[i][j]);
            }
        }
        switch (compass) {
            case 0:
                GUI_DB.listLog.add("North STATE: MOVE");
                break;
            case 1:
                GUI_DB.listLog.add("East STATE: MOVE");
                break;
            case 2:
                GUI_DB.listLog.add("South STATE: MOVE");
                break;
            case 3:
                GUI_DB.listLog.add("West STATE: MOVE");
                break;
            default:
                System.out.println("Error: compass");
                break;
        }
        updadeCalculateCoodinate(button, label);
        updateListLog();
    }

    // Calculate:Coodinateの更新
    public static void updadeCalculateCoodinate(Button[][] button, Label label) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                button[i][j].setText(String.valueOf(VariableDB.getEnemyEvaluation(i, j)));
                label.setText("Success!");
            }
        }
    }

    // Enemy:stateのアクションメソッド
    public static void buttonStateEnemyAction(int x, int y, int state, boolean flag, Label label, Button[][] button,
            boolean isFired) {
        if (flag) {
            GUI_DB.flagEnemy = false;
            VariableDB.setEnemyCoodinate(x, y, state);
            updadeCalculateCoodinate(button, label);
            if (!isFired) {
                int localX = x + 1;
                int localY = y + 1;
                GUI_DB.listLog.add("(" + localX + "," + localY + ") STATE: " + state);
            }
            updateListLog();
        } else {
            label.setText("座標を選択してください");
        }
    }

    // Log:リストラベルの更新
    public static void updateListLog() {
        String localTexts = GUI_DB.listLog.get(GUI_DB.listLog.size() - 1);
        GUI_DB.labelLog1.setText(localTexts);
        localTexts = GUI_DB.listLog.get(GUI_DB.listLog.size() - 2);
        GUI_DB.labelLog2.setText(localTexts);
        localTexts = GUI_DB.listLog.get(GUI_DB.listLog.size() - 3);
        GUI_DB.labelLog3.setText(localTexts);
        localTexts = GUI_DB.listLog.get(GUI_DB.listLog.size() - 4);
        GUI_DB.labelLog4.setText(localTexts);
        localTexts = GUI_DB.listLog.get(GUI_DB.listLog.size() - 5);
        GUI_DB.labelLog5.setText(localTexts);

    }

    // =====================================================================================================================================
    // =====================================================================================================================================
    @Override
    public void start(Stage stage) {
        // Log:リストの作成
        for (int i = 0; i < 5; i++) {
            GUI_DB.listLog.add("none");
        }
        // =====================================================================================================================================
        // =====================================================================================================================================
        // Enemy:CoodinateButtonの設定
        for (int l = 0; l < 5; l++) {
            for (int j = 0; j < 5; j++) {
                GUI_DB.buttonEnemyCoodinates[l][j] = createButtonCoodinate(GUI_DB.textsCoodinates[l][j], j, l,
                        GUI_DB.labelActiveEnemyCoodinate, "Enemy");
            }
        }
        // // Enemy:Gridpaneの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                GridPane.setConstraints(GUI_DB.buttonEnemyCoodinates[i][j], j, i);
            }
            GUI_DB.gridPaneEnemy.getChildren().addAll(GUI_DB.buttonEnemyCoodinates[i]);
        }
        // Enemy:stateButtonの設定
        GUI_DB.buttonHit.setOnAction(
                event -> buttonStateEnemyAction(GUI_DB.xEnemy, GUI_DB.yEnemy, 2, GUI_DB.flagEnemy,
                        GUI_DB.labelActiveEnemyCoodinate,
                        GUI_DB.buttonCalculateCoodinates, false));
        GUI_DB.buttonNot.setOnAction(
                event -> buttonStateEnemyAction(GUI_DB.xEnemy, GUI_DB.yEnemy, 0, GUI_DB.flagEnemy,
                        GUI_DB.labelActiveEnemyCoodinate,
                        GUI_DB.buttonCalculateCoodinates, false));
        GUI_DB.buttonNear.setOnAction(
                event -> buttonStateEnemyAction(GUI_DB.xEnemy, GUI_DB.yEnemy, 1, GUI_DB.flagEnemy,
                        GUI_DB.labelActiveEnemyCoodinate,
                        GUI_DB.buttonCalculateCoodinates, false));
        // Enemy:Moveボタンの設定
        GUI_DB.buttonEnemyNorth_1
                .setOnAction(event -> buttonEnemyMoveAction(0, 1, GUI_DB.buttonCalculateCoodinates,
                        GUI_DB.labelEnemylNaval));
        GUI_DB.buttonEnemyEast_1
                .setOnAction(event -> buttonEnemyMoveAction(1, 1, GUI_DB.buttonCalculateCoodinates,
                        GUI_DB.labelEnemylNaval));
        GUI_DB.buttonEnemySouth_1
                .setOnAction(event -> buttonEnemyMoveAction(2, 1, GUI_DB.buttonCalculateCoodinates,
                        GUI_DB.labelEnemylNaval));
        GUI_DB.buttonEnemyWest_1
                .setOnAction(event -> buttonEnemyMoveAction(3, 1, GUI_DB.buttonCalculateCoodinates,
                        GUI_DB.labelEnemylNaval));
        // Enemy:Move2ボタンの設定
        GUI_DB.buttonEnemyNorth_2
                .setOnAction(event -> buttonEnemyMoveAction(0, 2, GUI_DB.buttonCalculateCoodinates,
                        GUI_DB.labelEnemylNaval));
        GUI_DB.buttonEnemyEast_2
                .setOnAction(event -> buttonEnemyMoveAction(1, 2, GUI_DB.buttonCalculateCoodinates,
                        GUI_DB.labelEnemylNaval));
        GUI_DB.buttonEnemySouth_2
                .setOnAction(event -> buttonEnemyMoveAction(2, 2, GUI_DB.buttonCalculateCoodinates,
                        GUI_DB.labelEnemylNaval));
        GUI_DB.buttonEnemyWest_2
                .setOnAction(event -> buttonEnemyMoveAction(3, 2, GUI_DB.buttonCalculateCoodinates,
                        GUI_DB.labelEnemylNaval));

        // Calculate:CoodinateButtonの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                GUI_DB.buttonCalculateCoodinates[i][j] = createButtonCoodinate(GUI_DB.textsCalculate[i][j], j, i,
                        null, "Calculate");
            }
        }
        // Calculate:グリットの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                GridPane.setConstraints(GUI_DB.buttonCalculateCoodinates[i][j], j, i);
            }
            GUI_DB.gridpaneCalculate.getChildren().addAll(GUI_DB.buttonCalculateCoodinates[i]);
        }
        // Self:ボタンの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                GUI_DB.buttonSelfCoodinates[i][j] = createButtonCoodinate(GUI_DB.textsCoodinates[i][j], i, j,
                        GUI_DB.labelActiveSelfCoodinate, "Self");
            }
        }
        // Self:gridpaneの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                GridPane.setConstraints(GUI_DB.buttonSelfCoodinates[i][j], j, i);
            }
            GUI_DB.gridPaneSelf.getChildren().addAll(GUI_DB.buttonSelfCoodinates[i]);
        }
        // Self:Setボタンの設定
        GUI_DB.buttonSelfSet.setOnAction(
                event -> buttonSetSelfAction(GUI_DB.labelActiveSelfCoodinate, GUI_DB.buttonSelfCoodinates));
        // Self:Firedボタンの設定
        GUI_DB.buttonSelfFired
                .setOnAction(event -> buttonSelfFiredAction(GUI_DB.labelActiveSelfCoodinate,
                        GUI_DB.buttonCalculateCoodinates));
        // Log:listLogの設定
        GUI_DB.labelLog1.setFont(GUI_DB.fontLog);
        GUI_DB.labelLog2.setFont(GUI_DB.fontLog);
        GUI_DB.labelLog3.setFont(GUI_DB.fontLog);
        GUI_DB.labelLog4.setFont(GUI_DB.fontLog);
        GUI_DB.labelLog5.setFont(GUI_DB.fontLog);
        // =====================================================================================================================================
        // =====================================================================================================================================
        // Enemy:GUIレイアウトを作成
        HBox hboxStateButton = new HBox(GUI_DB.buttonHit, GUI_DB.buttonNot, GUI_DB.buttonNear);
        HBox hBoxMove_1Button = new HBox(GUI_DB.buttonEnemyNorth_1, GUI_DB.buttonEnemyEast_1, GUI_DB.buttonEnemySouth_1,
                GUI_DB.buttonEnemyWest_1);
        HBox hBoxMove_2Button = new HBox(GUI_DB.buttonEnemyNorth_2, GUI_DB.buttonEnemyEast_2, GUI_DB.buttonEnemySouth_2,
                GUI_DB.buttonEnemyWest_2);
        VBox vBoxEnemy = new VBox(GUI_DB.labelEnemylNaval, GUI_DB.gridPaneEnemy, GUI_DB.labelActiveEnemyCoodinate,
                hboxStateButton,
                hBoxMove_1Button, hBoxMove_2Button);
        // Enemy:GUIレイアウトの設定
        hBoxMove_1Button.setPadding(new Insets(5, 0, 5, 0));
        vBoxEnemy.setPadding(new Insets(10));
        // Calucurate:GUIレイアウトの作成
        VBox vboxCalucurate = new VBox(GUI_DB.labelCalculate, GUI_DB.gridpaneCalculate);
        // Calculate:GUIレイアウトの設定
        vboxCalucurate.setPadding(new Insets(10));
        // Self:GUIレイアウトを作成
        HBox hBoxSetFired = new HBox(GUI_DB.buttonSelfSet, GUI_DB.buttonSelfFired);
        VBox vBoxSelf = new VBox(GUI_DB.labelSelf, GUI_DB.gridPaneSelf, GUI_DB.labelActiveSelfCoodinate, hBoxSetFired);
        // Self:GUIレイアウトの設定
        vBoxSelf.setPadding(new Insets(10));
        // Log:GUIレイアウトの作成
        VBox vBoxlabelLog = new VBox(GUI_DB.labelLog1, GUI_DB.labelLog2, GUI_DB.labelLog3, GUI_DB.labelLog4,
                GUI_DB.labelLog5);
        VBox vBoxLog = new VBox(GUI_DB.labelLog, vBoxlabelLog);
        vBoxLog.setPadding(new Insets(10));
        // 全体のレイアウトをマージする
        HBox hboxEnemyToSelf = new HBox(vBoxEnemy, vboxCalucurate, vBoxSelf, vBoxLog);
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
        VariableDB.fillArrayFigure(VariableDB.enemyCoodinate, 0);
        Application.launch(args);
    }
}
