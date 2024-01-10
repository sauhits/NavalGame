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
        // (3,3)が不明な場合は攻撃する
        if (VariableDB.getEnemyEvaluation(2, 2) != -1) {
            GUI_DB.labelNextWay.setText("(C,3) Fire");
        } else {
            // MostEvaluationが存在する
            if (VariableDB.getEnemyMostEvaluation() != null) {
                // MostEvaluationを攻撃
                int xTarget = VariableDB.getEnemyMostEvaluation()[0];
                int yTarget = VariableDB.getEnemyMostEvaluation()[1];
                if (VariableDB.checkRange(xTarget, yTarget)) {
                    GUI_DB.labelNextWay.setText("(" + xTarget + "," + yTarget + ") Fire");
                }else{
                    MoveJudge();
                }
            } else {
                MoveJudge();
            }
        }
    }

    public static void MoveJudge() {

    }
}
