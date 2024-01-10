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
<<<<<<< HEAD
    // Coordinateボタンの作成メソッド
    public static Button createButtonCoordinate(String label, int row, int col, Label labelel,
=======
    // Coodinateボタンの作成メソッド
    public static Button createButtonCoodinate(String label, int row, int col, Label labelel,
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
            String EnemySelfCalculate) {
        Button button = new Button(label);
        button.setFont(new Font(12));
        button.setMinSize(50, 50);
<<<<<<< HEAD
        button.setOnAction(event -> buttonCoordinateAction(row, col, labelel, EnemySelfCalculate));
=======
        button.setOnAction(event -> buttonCoodinateAction(row, col, labelel, EnemySelfCalculate));
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
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
<<<<<<< HEAD
    public static void buttonCoordinateAction(int x, int y, Label label, String EnemySelfCalculation) {
        int affectationX = x + 1;
        int affectationY = y + 1;
        switch (EnemySelfCalculation) {
=======
    public static void buttonCoodinateAction(int x, int y, Label label, String EnemySelfCalcuration) {
        int affectationX = x + 1;
        int affectationY = y + 1;
        switch (EnemySelfCalcuration) {
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
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
<<<<<<< HEAD
                System.out.println("Error: buttonCoordinateAction");
=======
                System.out.println("Erorr: buttonCoodinateAction");
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
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
<<<<<<< HEAD
                    Naval_Judge.FirstJudge();
                }
                VariableDB.newSelfCoordinate(GUI_DB.xSelf, GUI_DB.ySelf);
=======
                    GUI_DB.flagStart = true;
                }
                VariableDB.newSelfCoodinate(GUI_DB.xSelf, GUI_DB.ySelf);
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
                button[GUI_DB.xSelf][GUI_DB.ySelf].setStyle("-fx-base: #62f478");
                textSetSelf = "現在" + switchSetSelf + "隻";
                GUI_DB.localX = GUI_DB.xSelf + 1;
                GUI_DB.localY = GUI_DB.ySelf + 1;
<<<<<<< HEAD
                GUI_DB.listLog.add("(" + GUI_DB.localY + "," + GUI_DB.localX + ") STATE: SET");
=======
                GUI_DB.listLog.add("(" + GUI_DB.localX + "," + GUI_DB.localY + ") STATE: SET");
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
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
<<<<<<< HEAD
=======
            // 先攻後攻の確認
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (VariableDB.getEnemyEvaluation(i, j) != -1) {
                        GUI_DB.isFirst = true;
                        break;
                    }
                }
            }

>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
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
<<<<<<< HEAD
        VariableDB.fillArrayFigure(GUI_DB.copyEnemyCoordinate, 0);
        // 指定した方向にずらしたCopyCoordinateを作成
=======
        VariableDB.fillArrayFigure(GUI_DB.copyEnemyCoodinate, 0);
        // 指定した方向にずらしたCopyCoodinateを作成
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
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
<<<<<<< HEAD
                        System.out.println("Error:compass");
=======
                        System.out.println("Erorr:compass");
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
                        break;
                }
                if (affectationX >= 0 && affectationX < 5 && affectationY >= 0 && affectationY < 5) {
                    if (VariableDB.getEnemyEvaluation(i, j) >= evaluationGain)
<<<<<<< HEAD
                        GUI_DB.copyEnemyCoordinate[affectationX][affectationY] = 1;
=======
                        GUI_DB.copyEnemyCoodinate[affectationX][affectationY] = 1;
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
                }
                // else {
                // System.out.println("Error:rangeOfCopy");
                // }
            }
        }
        // for (int i = 0; i < 5; i++) {
        // for (int j = 0; j < 5; j++) {
<<<<<<< HEAD
        // System.out.print(GUI_DB.copyEnemyCoordinate[i][j]);
=======
        // System.out.print(GUI_DB.copyEnemyCoodinate[i][j]);
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
        // }
        // System.out.println();
        // }
        System.out.println();
        // Copyをマージ
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
<<<<<<< HEAD
                GUI_DB.copyEnemyCoordinate[i][j] += VariableDB.getEnemyEvaluation(i, j);
=======
                GUI_DB.copyEnemyCoodinate[i][j] += VariableDB.getEnemyEvaluation(i, j);
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
<<<<<<< HEAD
                VariableDB.setEnemyEvaluation(i, j, GUI_DB.copyEnemyCoordinate[i][j]);
=======
                VariableDB.setEnemyEvaluation(i, j, GUI_DB.copyEnemyCoodinate[i][j]);
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
            }
        }
        switch (compass) {
            case 0:
<<<<<<< HEAD
                GUI_DB.listLog.add("(MOVE) STATE: ");
                break;
            case 1:
                GUI_DB.listLog.add("(MOVE) STATE: ");
                break;
            case 2:
                GUI_DB.listLog.add("(MOVE) STATE: ");
                break;
            case 3:
                GUI_DB.listLog.add("(MOVE) STATE: ");
=======
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
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
                break;
            default:
                System.out.println("Error: compass");
                break;
        }
<<<<<<< HEAD
        updateCalculateCoordinate(button, label);
        updateListLog();
    }

    // Calculate:Coordinateの更新
    public static void updateCalculateCoordinate(Button[][] button, Label label) {
=======
        updadeCalculateCoodinate(button, label);
        updateListLog();
    }

    // Calculate:Coodinateの更新
    public static void updadeCalculateCoodinate(Button[][] button, Label label) {
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
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
<<<<<<< HEAD
            VariableDB.setEnemyCoordinate(x, y, state);
            updateCalculateCoordinate(button, label);
=======
            VariableDB.setEnemyCoodinate(x, y, state);
            updadeCalculateCoodinate(button, label);
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
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
<<<<<<< HEAD
        // Enemy:CoordinateButtonの設定
        for (int l = 0; l < 5; l++) {
            for (int j = 0; j < 5; j++) {
                GUI_DB.buttonEnemyCoordinates[l][j] = createButtonCoordinate(GUI_DB.textsCoordinate[l][j], j, l,
                        GUI_DB.labelActiveEnemyCoordinate, "Enemy");
=======
        // Enemy:CoodinateButtonの設定
        for (int l = 0; l < 5; l++) {
            for (int j = 0; j < 5; j++) {
                GUI_DB.buttonEnemyCoodinates[l][j] = createButtonCoodinate(GUI_DB.textsCoodinates[l][j], j, l,
                        GUI_DB.labelActiveEnemyCoodinate, "Enemy");
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
            }
        }
        // // Enemy:Gridpaneの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
<<<<<<< HEAD
                GridPane.setConstraints(GUI_DB.buttonEnemyCoordinates[i][j], j, i);
            }
            GUI_DB.gridPaneEnemy.getChildren().addAll(GUI_DB.buttonEnemyCoordinates[i]);
=======
                GridPane.setConstraints(GUI_DB.buttonEnemyCoodinates[i][j], j, i);
            }
            GUI_DB.gridPaneEnemy.getChildren().addAll(GUI_DB.buttonEnemyCoodinates[i]);
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
        }
        // Enemy:stateButtonの設定
        GUI_DB.buttonHit.setOnAction(
                event -> buttonStateEnemyAction(GUI_DB.xEnemy, GUI_DB.yEnemy, 2, GUI_DB.flagEnemy,
<<<<<<< HEAD
                        GUI_DB.labelActiveEnemyCoordinate,
                        GUI_DB.buttonCalculateCoordinates, false));
        GUI_DB.buttonNot.setOnAction(
                event -> buttonStateEnemyAction(GUI_DB.xEnemy, GUI_DB.yEnemy, 0, GUI_DB.flagEnemy,
                        GUI_DB.labelActiveEnemyCoordinate,
                        GUI_DB.buttonCalculateCoordinates, false));
        GUI_DB.buttonNear.setOnAction(
                event -> buttonStateEnemyAction(GUI_DB.xEnemy, GUI_DB.yEnemy, 1, GUI_DB.flagEnemy,
                        GUI_DB.labelActiveEnemyCoordinate,
                        GUI_DB.buttonCalculateCoordinates, false));
        // Enemy:Moveボタンの設定
        GUI_DB.buttonEnemyNorth_1
                .setOnAction(event -> buttonEnemyMoveAction(0, 1, GUI_DB.buttonCalculateCoordinates,
                        GUI_DB.labelEnemyNaval));
        GUI_DB.buttonEnemyEast_1
                .setOnAction(event -> buttonEnemyMoveAction(1, 1, GUI_DB.buttonCalculateCoordinates,
                        GUI_DB.labelEnemyNaval));
        GUI_DB.buttonEnemySouth_1
                .setOnAction(event -> buttonEnemyMoveAction(2, 1, GUI_DB.buttonCalculateCoordinates,
                        GUI_DB.labelEnemyNaval));
        GUI_DB.buttonEnemyWest_1
                .setOnAction(event -> buttonEnemyMoveAction(3, 1, GUI_DB.buttonCalculateCoordinates,
                        GUI_DB.labelEnemyNaval));
        // Enemy:Move2ボタンの設定
        GUI_DB.buttonEnemyNorth_2
                .setOnAction(event -> buttonEnemyMoveAction(0, 2, GUI_DB.buttonCalculateCoordinates,
                        GUI_DB.labelEnemyNaval));
        GUI_DB.buttonEnemyEast_2
                .setOnAction(event -> buttonEnemyMoveAction(1, 2, GUI_DB.buttonCalculateCoordinates,
                        GUI_DB.labelEnemyNaval));
        GUI_DB.buttonEnemySouth_2
                .setOnAction(event -> buttonEnemyMoveAction(2, 2, GUI_DB.buttonCalculateCoordinates,
                        GUI_DB.labelEnemyNaval));
        GUI_DB.buttonEnemyWest_2
                .setOnAction(event -> buttonEnemyMoveAction(3, 2, GUI_DB.buttonCalculateCoordinates,
                        GUI_DB.labelEnemyNaval));

        // Calculate:CoordinateButtonの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                GUI_DB.buttonCalculateCoordinates[i][j] = createButtonCoordinate(GUI_DB.textsCalculate[i][j], j, i,
=======
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
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
                        null, "Calculate");
            }
        }
        // Calculate:グリットの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
<<<<<<< HEAD
                GridPane.setConstraints(GUI_DB.buttonCalculateCoordinates[i][j], j, i);
            }
            GUI_DB.gridpaneCalculate.getChildren().addAll(GUI_DB.buttonCalculateCoordinates[i]);
=======
                GridPane.setConstraints(GUI_DB.buttonCalculateCoodinates[i][j], j, i);
            }
            GUI_DB.gridpaneCalculate.getChildren().addAll(GUI_DB.buttonCalculateCoodinates[i]);
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
        }
        // Self:ボタンの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
<<<<<<< HEAD
                GUI_DB.buttonSelfCoordinates[i][j] = createButtonCoordinate(GUI_DB.textsCoordinate[i][j], i, j,
                        GUI_DB.labelActiveSelfCoordinate, "Self");
=======
                GUI_DB.buttonSelfCoodinates[i][j] = createButtonCoodinate(GUI_DB.textsCoodinates[i][j], i, j,
                        GUI_DB.labelActiveSelfCoodinate, "Self");
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
            }
        }
        // Self:gridpaneの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
<<<<<<< HEAD
                GridPane.setConstraints(GUI_DB.buttonSelfCoordinates[i][j], j, i);
            }
            GUI_DB.gridPaneSelf.getChildren().addAll(GUI_DB.buttonSelfCoordinates[i]);
        }
        // Self:Setボタンの設定
        GUI_DB.buttonSelfSet.setOnAction(
                event -> buttonSetSelfAction(GUI_DB.labelActiveSelfCoordinate, GUI_DB.buttonSelfCoordinates));
        // Self:Firedボタンの設定
        GUI_DB.buttonSelfFired
                .setOnAction(event -> buttonSelfFiredAction(GUI_DB.labelActiveSelfCoordinate,
                        GUI_DB.buttonCalculateCoordinates));
=======
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
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
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
<<<<<<< HEAD
        VBox vBoxEnemy = new VBox(GUI_DB.labelEnemyNaval, GUI_DB.gridPaneEnemy, GUI_DB.labelActiveEnemyCoordinate,
=======
        VBox vBoxEnemy = new VBox(GUI_DB.labelEnemylNaval, GUI_DB.gridPaneEnemy, GUI_DB.labelActiveEnemyCoodinate,
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
                hboxStateButton,
                hBoxMove_1Button, hBoxMove_2Button);
        // Enemy:GUIレイアウトの設定
        hBoxMove_1Button.setPadding(new Insets(5, 0, 5, 0));
        vBoxEnemy.setPadding(new Insets(10));
<<<<<<< HEAD
        // Calculate:GUIレイアウトの作成
        VBox vboxCalculate = new VBox(GUI_DB.labelCalculate, GUI_DB.gridpaneCalculate);
        // Calculate:GUIレイアウトの設定
        vboxCalculate.setPadding(new Insets(10));
        // Self:GUIレイアウトを作成
        HBox hBoxSetFired = new HBox(GUI_DB.buttonSelfSet, GUI_DB.buttonSelfFired);
        VBox vBoxSelf = new VBox(GUI_DB.labelSelf, GUI_DB.gridPaneSelf, GUI_DB.labelActiveSelfCoordinate, hBoxSetFired);
=======
        // Calucurate:GUIレイアウトの作成
        VBox vboxCalucurate = new VBox(GUI_DB.labelCalculate, GUI_DB.gridpaneCalculate);
        // Calculate:GUIレイアウトの設定
        vboxCalucurate.setPadding(new Insets(10));
        // Self:GUIレイアウトを作成
        HBox hBoxSetFired = new HBox(GUI_DB.buttonSelfSet, GUI_DB.buttonSelfFired);
        VBox vBoxSelf = new VBox(GUI_DB.labelSelf, GUI_DB.gridPaneSelf, GUI_DB.labelActiveSelfCoodinate, hBoxSetFired);
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
        // Self:GUIレイアウトの設定
        vBoxSelf.setPadding(new Insets(10));
        // Log:GUIレイアウトの作成
        VBox vBoxlabelLog = new VBox(GUI_DB.labelLog1, GUI_DB.labelLog2, GUI_DB.labelLog3, GUI_DB.labelLog4,
                GUI_DB.labelLog5);
<<<<<<< HEAD
        VBox vBoxLog = new VBox(GUI_DB.labelLog,vBoxlabelLog);
        vBoxLog.setPadding(new Insets(10));
        // Next:GUIレイアウトの作成
        VBox vBoxNext=new VBox(GUI_DB.labelNextTitle,GUI_DB.labelNextWay);
        vBoxNext.setPadding(new Insets(10));
        // 全体のレイアウトをマージする
        VBox vBoxCalculateNext=new VBox(vboxCalculate,vBoxNext);
        HBox hboxEnemyToSelf = new HBox(vBoxEnemy, vBoxCalculateNext, vBoxSelf, vBoxLog);
=======
        VBox vBoxLog = new VBox(GUI_DB.labelLog, vBoxlabelLog);
        vBoxLog.setPadding(new Insets(10));
        // 全体のレイアウトをマージする
        HBox hboxEnemyToSelf = new HBox(vBoxEnemy, vboxCalucurate, vBoxSelf, vBoxLog);
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
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
<<<<<<< HEAD
        VariableDB.fillArrayFigure(VariableDB.enemyCoordinate, 0);
        Naval_Judge.SetSelfNaval();
=======
        VariableDB.fillArrayFigure(VariableDB.enemyCoodinate, 0);
>>>>>>> 8751749f6252ea4a5d426eb70b2bdefaa54e77bc
        Application.launch(args);
    }
}
