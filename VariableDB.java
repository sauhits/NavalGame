import java.util.Set;

public class VariableDB {
    static private double EnemyCoodinate[][] = new double[7][7];
    static private double SelfCoodinate[][] = new double[7][7];

    public static void SetEnemyCoodinate(int x, int y, int state) {
        // ハズレの座標index処理
        if (state == 0) {
            EnemyCoodinate[x][y] = 0;
            // 最左列を排除
            if (x != 1) {
                EnemyCoodinate[x - 1][y] = 0;
                // 最上列を排除
                if (y != 1) {
                    EnemyCoodinate[x - 1][y + 1] = 0;
                }
                // 最下列を排除
                if (y != 5) {
                    EnemyCoodinate[x - 1][y - 1] = 0;
                }
            }
            // 最右列を排除
            if (x != 5) {
                EnemyCoodinate[x + 1][y] = 0;
                // 最上列を排除
                if (y != 1) {
                    EnemyCoodinate[x + 1][y + 1] = 0;
                }
                // 最下列を排除
                if (y != 5) {
                    EnemyCoodinate[x + 1][y - 1] = 0;
                }
            }
            // 最上列を排除
            if (y != 1) {
                EnemyCoodinate[x][y + 1] = 0;
            }
            // 最下列を排除
            if (y != 5) {
                EnemyCoodinate[x][y - 1] = 0;
            }
        }

        // 波高しの座標index処理
        if (state == 1) {
            EnemyCoodinate[x][y] += 0.1;
            // 最左列を排除
            if (x != 1) {
                EnemyCoodinate[x - 1][y] += 0.1;
                // 最上列を排除
                if (y != 1) {
                    EnemyCoodinate[x - 1][y + 1] += 0.1;
                }
                // 最下列を排除
                if (y != 5) {
                    EnemyCoodinate[x - 1][y - 1] += 0.1;
                }
            }
            // 最右列を排除
            if (x != 5) {
                EnemyCoodinate[x + 1][y] += 0.1;
                // 最上列を排除
                if (y != 1) {
                    EnemyCoodinate[x + 1][y + 1] += 0.1;
                }
                // 最下列を排除
                if (y != 5) {
                    EnemyCoodinate[x + 1][y - 1] += 0.1;
                }
            }
            // 最上列を排除
            if (y != 1) {
                EnemyCoodinate[x][y + 1] += 0.1;
            }
            // 最下列を排除
            if (y != 5) {
                EnemyCoodinate[x][y - 1] += 0.1;
            }
        }

        // ヒットの座標index処理
        if (state == 2) {
            EnemyCoodinate[x][y] += 1;
            // 最左列を排除
            if (x != 1) {
                EnemyCoodinate[x - 1][y] = 0;
                // 最上列を排除
                if (y != 1) {
                    EnemyCoodinate[x - 1][y + 1] = 0;
                }
                // 最下列を排除
                if (y != 5) {
                    EnemyCoodinate[x - 1][y - 1] = 0;
                }
            }
            // 最右列を排除
            if (x != 5) {
                EnemyCoodinate[x + 1][y] = 0;
                // 最上列を排除
                if (y != 1) {
                    EnemyCoodinate[x + 1][y + 1] = 0;
                }
                // 最下列を排除
                if (y != 5) {
                    EnemyCoodinate[x + 1][y - 1] = 0;
                }
            }
            // 最上列を排除
            if (y != 1) {
                EnemyCoodinate[x][y + 1] = 0;
            }
            // 最下列を排除
            if (y != 5) {
                EnemyCoodinate[x][y - 1] = 0;
            }
        }
    }

    public static double GetEnemyCoodinate(int x, int y) {
        return EnemyCoodinate[x][y];
    }

    public static void SetSelfCoodinate(int x, int y) {
        SelfCoodinate[x][y] = 3;
    }

    public static String GetSelfCoodinate() {
        String result = "SelfNaval";
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (SelfCoodinate[i][j] != 0) {
                    result = result + "(" + i + "," + j + ")";
                }
            }
        }
        return result;
    }

}