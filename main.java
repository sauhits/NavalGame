import javax.swing.text.LabelView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class main extends Application {
    static int xEnemy;
    static int yEnemy;
    static int xSelf;
    static int ySelf;
    static int stateEnemy;
    static boolean flagEnemy = false;
    static boolean flagSelf = false;

    // =====================================================================================================================================
    // =====================================================================================================================================
    // Enemy;ボタンの作成メソッド
    private Button createButtonEnemyCoodinate(String label, int row, int col, Label labelel) {
        Button button = new Button(label);
        button.setFont(new Font(12));
        button.setMinSize(50, 50);
        button.setOnAction(event -> buttonCoodinateEnemyAction(row, col, labelel));
        return button;
    }

    // Self;ボタンの作成メソッド
    private Button createButtonSelfCoodinate(String label, int row, int col, Label labelel) {
        Button button = new Button(label);
        button.setFont(new Font(12));
        button.setMinSize(50, 50);
        button.setOnAction(event -> buttonCoodinateSelfAction(row, col, labelel));
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

    // Enemy:座標を選択した際のアクションメソッド
    public static void buttonCoodinateEnemyAction(int x, int y, Label label) {
        int affectationX = x + 1;
        int affectationY = y + 1;
        xEnemy = x;
        yEnemy = y;
        flagEnemy = true;
        label.setText("(" + affectationX + "," + affectationY + ")");
        System.out.println(xEnemy + "," + yEnemy);
    }

    // Self:座標を選択した際のアクションメソッド
    public static void buttonCoodinateSelfAction(int x, int y, Label label) {
        int affectationX = x + 1;
        int affectationY = y + 1;
        xSelf = x;
        ySelf = y;
        flagSelf = true;
        label.setText("(" + affectationX + "," + affectationY + ")");
    }

    // Enemy:stateのアクションメソッド
    public static void buttonStateEnemyAction(int x, int y, int state, boolean flag, Label label, Button[][] button) {
        if (flag) {
            flagEnemy = false;
            VariableDB.setEnemyCoodinate(x, y, state);
            // Calcurate:Coodinateの更新
            String[][] textsCalcurateCoodinate = new String[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (textsCalcurateCoodinate != null) {
                        button[i][j].setText(String.valueOf(VariableDB.getEnemyCoodinate(i, j)));
                        label.setText("Success!");
                    } else {
                        label.setText("Error");
                    }
                }
            }
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
                { "1,1", "1,2", "1,3", "1,4", "1,5" },
                { "2,1", "2,2", "2,3", "2,4", "2,5" },
                { "3,1", "3,2", "3,3", "3,4", "3,5" },
                { "4,1", "4,2", "4,3", "4,4", "4,5" },
                { "5,1", "5,2", "5,3", "5,4", "5,5" }
        };
        // Enemy:Gridpaneの作成
        GridPane gridPaneEnemy = new GridPane();
        // Enemy:CoodinateButtonの作成-----------------------------------
        Button[][] buttonEnemyCoodinates = new Button[5][5];
        // Enemy:stateButtonの作成
        Button buttonHit = new Button("HIT");
        Button buttonNot = new Button("NOT");
        Button buttonNear = new Button("NEAR");
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
        // Self:gridpaneの作成
        GridPane gridPaneSelf = new GridPane();
        // =====================================================================================================================================
        // =====================================================================================================================================
        // Enemy:CoodinateButtonの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttonEnemyCoodinates[i][j] = createButtonEnemyCoodinate(textsCoodinates[i][j], i, j,
                        labelActiveEnemyCoodinate);
            }
        }
        // Enemy:Gridpaneの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                GridPane.setConstraints(buttonEnemyCoodinates[i][j], j, i);
            }
            gridPaneEnemy.getChildren().addAll(buttonEnemyCoodinates[i]);
        }

        // Enemy:stateButtonの設定
        int stateButtonWidth = 250 / 3;
        buttonHit.setPrefHeight(40);
        buttonHit.setPrefWidth(stateButtonWidth);
        buttonHit.setOnAction(
                event -> buttonStateEnemyAction(xEnemy, yEnemy, 2, flagEnemy, labelActiveEnemyCoodinate,
                        buttonCalcurateCoodinates));
        buttonNot.setPrefHeight(40);
        buttonNot.setPrefWidth(stateButtonWidth);
        buttonNot.setOnAction(
                event -> buttonStateEnemyAction(xEnemy, yEnemy, 0, flagEnemy, labelActiveEnemyCoodinate,
                        buttonCalcurateCoodinates));
        buttonNear.setPrefHeight(40);
        buttonNear.setPrefWidth(stateButtonWidth);
        buttonNear.setOnAction(
                event -> buttonStateEnemyAction(xEnemy, yEnemy, 1, flagEnemy, labelActiveEnemyCoodinate,
                        buttonCalcurateCoodinates));

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
                GridPane.setConstraints(buttonCalcurateCoodinates[i][j], j, i);
            }
            gridpaneCalcurate.getChildren().addAll(buttonCalcurateCoodinates[i]);
        }

        // Self:ボタンの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttonSelfCoodinates[i][j] = createButtonSelfCoodinate(textsCoodinates[i][j], i, j,
                        labelActiveSelfCoodinate);
            }
        }

        // Self:gridpaneの設定
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                GridPane.setConstraints(buttonSelfCoodinates[i][j], j, i);
            }
            gridPaneSelf.getChildren().addAll(buttonSelfCoodinates[i]);
        }
        // =====================================================================================================================================
        // =====================================================================================================================================
        // Enemy:GUIレイアウトを作成
        HBox hboxStateButton = new HBox(buttonHit, buttonNot, buttonNear);
        VBox vBoxEnemy = new VBox(labelEnemylNaval, gridPaneEnemy, labelActiveEnemyCoodinate, hboxStateButton);
        // Enemy:GUIレイアウトの設定
        vBoxEnemy.setPadding(new Insets(10));
        // Calucurate:GUIレイアウトの作成
        VBox vboxCalucurate = new VBox(labelCalcurate, gridpaneCalcurate);
        // Calcurate:GUIレイアウトの設定
        vboxCalucurate.setPadding(new Insets(10));
        // Self:GUIレイアウトを作成
        VBox vBoxSelf = new VBox(labelSelf, gridPaneSelf, labelActiveSelfCoodinate);
        // Self:GUIレイアウトの設定
        vBoxSelf.setPadding(new Insets(10));
        // 全体のレイアウトをマージする
        HBox hboxEnemyToSelf = new HBox(vBoxEnemy, vboxCalucurate, vBoxSelf);
        hboxEnemyToSelf.setSpacing(5.0);
        // =====================================================================================================================================
        // =====================================================================================================================================
        // 開く際のサイズ指定-----------------------------------
        stage.setWidth(1500.);
        stage.setHeight(450.);
        // サイズの固定-----------------------------------
        stage.setResizable(false);
        stage.setTitle("Naval Game");
        // 画面の表示-----------------------------------
        stage.setScene(new Scene(hboxEnemyToSelf));
        stage.show();
    }

    public static void main(String[] args) {
        new New_Game();
        Application.launch(args);
    }
}
