public class VariableDB {
    static int enemyCoodinate[][] = new int[5][5];
    static int selfCoodinate[][] = new int[5][5];
    // 判定の値
    private static final int availableOfNot = -1;
    private static final int availableOfNear = 1;
    private static final int availableOfHit = 10;

    public static void fillArrayFigure(int[][] twoArray, int fillFigure) {
        for (int i = 0; i < twoArray.length; i++) {
            for (int j = 0; j < twoArray.length; j++) {
                twoArray[i][j] = fillFigure;
            }
        }
    }

    public static void setEnemyCoodinate(int x, int y, int state) {
        switch (state) {
            case 0:
                // Not処理
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        int affectationX = x + i;
                        int affectationY = y + j;
                        if (affectationX >= 0 && affectationX < 5 && affectationY >= 0 && affectationY < 5) {
                            enemyCoodinate[affectationX][affectationY] = availableOfNot;
                        }
                    }
                }
                break;
            case 1:
                // Near処理
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        int affectationX = x + i;
                        int affectationY = y + j;
                        // 範囲の確認
                        if (affectationX >= 0 && affectationX < 5 && affectationY >= 0 && affectationY < 5) {
                            // Not地点の回避
                            if (enemyCoodinate[affectationX][affectationY] != -1) {
                                enemyCoodinate[affectationX][affectationY] += availableOfNear;
                            }
                        }
                    }
                }
                // 攻撃地点をNot処理する
                enemyCoodinate[x][y] = availableOfNot;
                break;
            case 2:
                // Hit処理
                enemyCoodinate[x][y] = availableOfHit;
                break;
            default:
                break;
        }
    }

    // Enemyの評価を返す
    public static int getEnemyEvaluation(int x, int y) {
        return enemyCoodinate[x][y];
    }

    // Enemyの評価を設定する
    public static void setEnemyEvaluation(int x, int y, int evaluation) {
        enemyCoodinate[x][y] = evaluation;
    }

    // 新たに自軍の船を設定する
    public static void newSelfCoodinate(int x, int y) {
        selfCoodinate[x][y] = 3;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(selfCoodinate[i][j]);
            }
            System.out.println();
        }
    }

    // 指定したhealthを適応する
    public static void setSelfHealth(int x, int y, int health) {
        String localStyle = "-fx-base: #f185ff";
        System.out.println(health);
        // 自軍の船があるか確認
        if (selfCoodinate[x][y] != 0 && selfCoodinate[x][y] != -99) {
            if (health == 0) {
                selfCoodinate[x][y] = -99;
                // black
                localStyle = "-fx-base: #080808";
            } else {
                selfCoodinate[x][y] = health;
                switch (health) {
                    case 1:
                        // red
                        localStyle = "-fx-base: #ff8585";
                        break;
                    case 2:
                        // yellow
                        localStyle = "-fx-base: #f5ff85";
                        break;
                    case 3:
                        // green
                        localStyle = "-fx-base: #85ff8d";
                        break;
                    default:
                        break;
                }
                GUI_DB.buttonSelfCoodinates[x][y].setStyle(localStyle);
            }
        }
    }

    // healthを取得する
    public static int getSelfHealth(int x, int y) {
        return selfCoodinate[x][y];
    }

    // 指定座標が攻撃できるかどうか
    public static boolean checkRange(int x, int y) {
        boolean check = false;
        int affectationX, affectationY;
        for (int i = -1; i < 1; i++) {
            for (int j = -1; j < 1; j++) {
                affectationX = x + i;
                affectationY = y + j;
                check = getSelfHealth(affectationX, affectationY) != 0;
                if (check) {
                    return check;
                }
            }
        }
        return check;
    }

}
