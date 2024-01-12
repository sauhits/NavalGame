public class Naval_Judge {
    public static void SetSelfNaval() {
        GUI_DB.labelNextWay.setText("(1,1),(4,2),(2,4),(5,5) Set");
    }

    public static void FirstJudge() {
        if (VariableDB.getEnemyEvaluation(2, 2) != -1) {
            // 先攻もしくは後攻で3,3が攻撃されていない
            GUI_DB.labelNextWay.setText("First:(3,3) Fire" + "\n" + "Second: (3,3)->(1,2) Fire");
        }
    }

    public static void AggressiveJudge() {
        System.out.println("Aggressive Start");
        // (3,3)が不明な場合は攻撃する
        if (VariableDB.getEnemyEvaluation(2, 2) != -1) {
            GUI_DB.labelNextWay.setText("(C,3) Fire");
        } else {
            // MostEvaluationが存在する
            if (VariableDB.getEnemyMostEvaluation(true) != null) {
                // MostEvaluationを攻撃
                int xTarget = VariableDB.getEnemyMostEvaluation(true)[0];
                int yTarget = VariableDB.getEnemyMostEvaluation(true)[1];
                if (VariableDB.checkRange(xTarget, yTarget)) {
                    GUI_DB.labelNextWay.setText("(" + xTarget + "," + yTarget + ") Fire");
                } else {
                    MoveJudge();
                }
            } else {
                // MostEvaluationが存在しない場合は移動する
                MoveJudge();
            }
        }
    }

    static int moveCount = 0;

    public static void MoveJudge() {
        System.out.println("Move Start");
        // [船の指定][0:x,1:y]
        int[][] goNaval = VariableDB.getSelfCoordinate();
        boolean localTerm = true;
        int xDeviation = 0;
        int yDeviation = 0;
        int localRandom = 0;
        int xInstant, yInstant;
        // 初期位置に移動
        // first->second
        for(int i=0;i<4;i++) {
            // System.out.println(moveCount);
            if (moveCount != 0) {
                localTerm = goNaval[i][0] != VariableDB.setFirst[0][0];
                xDeviation = VariableDB.setFirst[i][0] - goNaval[i][0];
                yDeviation = VariableDB.setFirst[i][1] - goNaval[i][1];
            } else {
                localTerm = goNaval[i][0] != VariableDB.setSecond[0][0];
                xDeviation = VariableDB.setSecond[i][0] - goNaval[i][0];
                yDeviation = VariableDB.setSecond[i][1] - goNaval[i][1];
            }
            if (localTerm) {
                if (xDeviation != 0 && yDeviation != 0) {
                    if (moveCount == 0) {
                        moveCount = 1;
                    } else {
                        moveCount = 0;
                    }
                }
                if (xDeviation != 0) {
                    // xを合わせる
                    // 差が+２より大きい場合
                    xInstant = goNaval[i][0] + 2;
                    if (xDeviation >= 2 && VariableDB.getSelfHealth(xInstant, goNaval[i][1]) == 0) {
                        VariableDB.moveSelfNaval(goNaval[i][0], goNaval[i][1], xInstant,
                                goNaval[i][1]);
                        break;
                    }
                    // 差が-2より大きい場合
                    xInstant = goNaval[i][0] - 2;
                    if (xDeviation <= -2 && VariableDB.getSelfHealth(xInstant, goNaval[i][1]) == 0) {
                        VariableDB.moveSelfNaval(goNaval[i][0], goNaval[i][1],
                                xInstant, goNaval[i][1]);
                        break;
                    }
                    // 差が+1の場合// 差が-1の場合
                    VariableDB.moveSelfNaval(goNaval[i][0], goNaval[i][1],
                            goNaval[i][0] + xDeviation,
                            goNaval[i][1]);
                    break;
                } else if (yDeviation != 0) {
                    // yを合わせる
                    // 差が+２より大きい場合
                    yInstant = goNaval[i][1] + 2;
                    if (yDeviation >= 2 && VariableDB.getSelfHealth(goNaval[i][0], yInstant) == 0) {
                        VariableDB.moveSelfNaval(goNaval[i][0], goNaval[i][1],
                                goNaval[i][0], yInstant);
                        break;
                    }
                    // 差が-2より大きい場合
                    yInstant = goNaval[i][1] - 2;
                    if (yDeviation <= -2 && VariableDB.getSelfHealth(goNaval[i][0], yInstant) == 0) {
                        VariableDB.moveSelfNaval(goNaval[i][0], goNaval[i][1],
                                goNaval[i][0], yInstant);
                        break;
                    } // 差が-1の場合// 差が+1の場合
                    VariableDB.moveSelfNaval(goNaval[i][0], goNaval[i][1],
                            goNaval[i][0],
                            goNaval[i][1] + yDeviation);
                    break;
                }
            }
        }
        System.out.println("MoveEnd");
    }

    public static void avoidJudge(int xNow, int yNow) {
        System.out.println("Avoid Start");
        // beFiredとのみ紐づくメソッド
        // 攻撃を受けた際の回避について
        // GUI_DB.xSelf,GUI_DB.ySelfから移動する
        int[] hitCoordinate = VariableDB.getEnemyMostEvaluation(xNow, yNow, false);
        VariableDB.moveSelfNaval(GUI_DB.xSelf, GUI_DB.ySelf, hitCoordinate[0], hitCoordinate[1]);
    }
}