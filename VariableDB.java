public class VariableDB {
    static int enemyCoodinate[][] = new int[5][5];
    static int selfCoodinate[][] = new int[5][5];
    // 判定の値
    private static final int availableOfNot = 0;
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
        int instantvalue = 0;
        switch (state) {
            case 0:
                // Not処理
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        int affectationX = x + i;
                        int affectationY = y + j;
                        if (affectationX >= 0 && affectationX < 5 && affectationY >= 0 && affectationY < 5) {
                            enemyCoodinate[affectationX][affectationY] = 0;
                        }
                    }
                }
                break;
            case 1:
                // Near処理
                int inti=1;
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        int affectationX = x + i;
                        int affectationY = y + j;
                        if (affectationX >= 0 && affectationX < 5 && affectationY >= 0 && affectationY < 5) {
                            if(enemyCoodinate[affectationX][affectationY]==-1){
                                enemyCoodinate[affectationX][affectationY] += availableOfNear;
                            }
                            enemyCoodinate[affectationX][affectationY] += availableOfNear;
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
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                System.out.print(selfCoodinate[i][j]);
            }
            System.out.println();
        }
    }

    // 指定したhealthを適応する
    public static void setSelfHealth(int x, int y, int health) {
        if (health == 0) {
            selfCoodinate[x][y] = 99;
        } else {
            selfCoodinate[x][y] = health;
        }
    }

    // healthを取得する
    public static int getSelfHealth(int x, int y) {
        return selfCoodinate[x][y];
    }

}
