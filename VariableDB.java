import java.util.Set;

public class VariableDB {
    static private double Coodinate[][] = new double[7][7];

    public static void SetCoodinate(int x, int y, int state) {
        // ハズレの座標index処理
        if (state == 0) {
            Coodinate[x][y] = 0;
            // 最左列を排除
            if (x != 1) {
                Coodinate[x - 1][y] = 0;
                // 最上列を排除
                if (y != 1) {
                    Coodinate[x - 1][y + 1] = 0;
                }
                // 最下列を排除
                if (y != 5) {
                    Coodinate[x - 1][y - 1] = 0;
                }
            }
            // 最右列を排除
            if (x != 5) {
                Coodinate[x + 1][y] = 0;
                // 最上列を排除
                if (y != 1) {
                    Coodinate[x + 1][y + 1] = 0;
                }
                // 最下列を排除
                if (y != 5) {
                    Coodinate[x + 1][y - 1] = 0;
                }
            }
            // 最上列を排除
            if (y != 1) {
                Coodinate[x][y + 1] = 0;
            }
            // 最下列を排除
            if (y != 5) {
                Coodinate[x][y - 1] = 0;
            }
        }

        // 波高しの座標index処理
        if (state == 1) {
            Coodinate[x][y] += 0.1;
            // 最左列を排除
            if (x != 1) {
                Coodinate[x - 1][y] += 0.1;
                // 最上列を排除
                if (y != 1) {
                    Coodinate[x - 1][y + 1] += 0.1;
                }
                // 最下列を排除
                if (y != 5) {
                    Coodinate[x - 1][y - 1] += 0.1;
                }
            }
            // 最右列を排除
            if (x != 5) {
                Coodinate[x + 1][y] += 0.1;
                // 最上列を排除
                if (y != 1) {
                    Coodinate[x + 1][y + 1] += 0.1;
                }
                // 最下列を排除
                if (y != 5) {
                    Coodinate[x + 1][y - 1] += 0.1;
                }
            }
            // 最上列を排除
            if (y != 1) {
                Coodinate[x][y + 1] += 0.1;
            }
            // 最下列を排除
            if (y != 5) {
                Coodinate[x][y - 1] += 0.1;
            }
        }

        // ヒットの座標index処理
        if (state == 2) {
            Coodinate[x][y] += 1;
            // 最左列を排除
            if (x != 1) {
                Coodinate[x - 1][y] = 0;
                // 最上列を排除
                if (y != 1) {
                    Coodinate[x - 1][y + 1] = 0;
                }
                // 最下列を排除
                if (y != 5) {
                    Coodinate[x - 1][y - 1] = 0;
                }
            }
            // 最右列を排除
            if (x != 5) {
                Coodinate[x + 1][y] = 0;
                // 最上列を排除
                if (y != 1) {
                    Coodinate[x + 1][y + 1] = 0;
                }
                // 最下列を排除
                if (y != 5) {
                    Coodinate[x + 1][y - 1] = 0;
                }
            }
            // 最上列を排除
            if (y != 1) {
                Coodinate[x][y + 1] = 0;
            }
            // 最下列を排除
            if (y != 5) {
                Coodinate[x][y - 1] = 0;
            }
        }
    }

    public static double GetCoodinate(int x, int y) {
        return Coodinate[x][y];
    }
}
