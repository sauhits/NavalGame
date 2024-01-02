import java.util.Set;

public class VariableDB {
    static private int enemyCoodinate[][] = new int[5][5];
    static private int selfCoodinate[][] = new int[5][5];

    public static void setEnemyCoodinate(int x, int y, int state) {
        // ハズレの座標index処理
        if (state == 0) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    int affectationX = x + i;
                    int affectationY = y + j;
                    if (affectationX >= 0 && affectationX < 5 && affectationY >= 0 && affectationY < 5) {
                        enemyCoodinate[affectationX][affectationY] = 0;
                    }
                }
            }
        }
        // 波高しの座標index処理
        if (state == 1) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    int affectationX = x + i;
                    int affectationY = y + j;
                    
                    if (affectationX >= 0 && affectationX < 5 && affectationY >= 0 && affectationY < 5) {
                        enemyCoodinate[affectationX][affectationY] += 1;
                    }
                }
            }
            enemyCoodinate[x][y] = 0;
        }
        // ヒットの座標index処理
        if (state == 2) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    int affectationX = x + i;
                    int affectationY = y + j;
                    if (affectationX >= 0 && affectationX < 5
                            && affectationY >= 0 && affectationY < 5) {
                        if (enemyCoodinate[affectationX][affectationY] >= 1) {
                            enemyCoodinate[affectationX][affectationY] -= 10;
                        }
                    }
                }
            }
            enemyCoodinate[x][y] = 0;
        }
    }

    public static int getEnemyCoodinate(int x, int y) {
        return enemyCoodinate[x][y];
    }

    public static void newSelfCoodinate(int x, int y) {
        selfCoodinate[x][y] = 3;
    }

    public static void setSelfHealth(int x, int y, int health) {
        selfCoodinate[x][y] = health;
    }

    public static int getSelfHealth(int x, int y) {
        return selfCoodinate[x][y];
    }

}