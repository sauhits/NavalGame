public class VariableDB {
    static int enemyCoordinate[][] = new int[5][5];
    static int selfCoordinate[][] = new int[5][5];
    // 二種の初期位置
    static int[][] setFirst={{0,0},{1,3},{3,1},{4,4}};
    static int[][] setSecond={{0,4},{1,1},{3,3},{4,0}};
    // 判定の値
    private static final int availableOfNot = -1;
    private static final int availableOfNear = 1;
    private static final int availableOfHit = 10;

    // 二次元配列を埋める
    public static void fillArrayFigure(int[][] twoArray, int fillFigure) {
        for (int i = 0; i < twoArray.length; i++) {
            for (int j = 0; j < twoArray.length; j++) {
                twoArray[i][j] = fillFigure;
            }
        }
    }

    // 二次元配列を表示する
    public static void showTwoArray(int[][] array) {
        System.out.print("\n");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(array[j][i]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    // 敵艦の評価を入力する
    public static void setEnemyCoordinate(int x, int y, int state) {
        switch (state) {
            case 0:
                // Not処理
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        int affectationX = x + i;
                        int affectationY = y + j;
                        if (affectationX >= 0 && affectationX < 5 && affectationY >= 0 && affectationY < 5) {
                            enemyCoordinate[affectationX][affectationY] = availableOfNot;
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
                            if (enemyCoordinate[affectationX][affectationY] != -1) {
                                enemyCoordinate[affectationX][affectationY] += availableOfNear;
                            }
                        }
                    }
                }
                // 攻撃地点をNot処理する
                enemyCoordinate[x][y] = availableOfNot;
                break;
            case 2:
                // Hit処理
                enemyCoordinate[x][y] = availableOfHit;
                break;
            default:
                break;
        }
    }

    // 全体で一番評価の高い座標を返す(複数存在するならnullを返す)
    public static int[] getEnemyMostEvaluation(boolean okNull) {
        int[] coordinate = new int[2];
        boolean localboolean = false;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (getEnemyEvaluation(i, j) >= getEnemyEvaluation(coordinate[0], coordinate[1])) {
                    localboolean = false;
                    coordinate[0] = i;
                    coordinate[1] = j;
                    if (getEnemyEvaluation(i, j) == getEnemyEvaluation(coordinate[0], coordinate[1])) {
                        localboolean = true;
                    }
                }
            }
        }
        if (okNull) {
            if (localboolean) {
                return null;
            }
        }
        return coordinate;
    }

    // 指定した座標の周囲でMostEvaluationを返す
    public static int[] getEnemyMostEvaluation(int x, int y, boolean cellOf8) {
        int[] localCoordinate = { x, y };
        if (cellOf8) {
            // 周囲８マスでの探索
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    int localX = x + i;
                    int localY = y + j;
                    if (localX >= 0 && localX < 5 && localY >= 0 && localY < 5) {
                        if (getEnemyEvaluation(localX, localY) >= getEnemyEvaluation(localCoordinate[0],
                                localCoordinate[1])&&getSelfHealth(localX, localY)!=-99) {
                            localCoordinate[0] = localX;
                            localCoordinate[1] = localY;
                        }
                    }
                }
            }
        } else {
            // 対角線を除く8マス
            // x増減２での探索
            for (int i = -2; i < 3; i++) {
                int localX = x + i;
                if (localX >= 0 && localX < 5) {
                    if (getEnemyEvaluation(localX, y) >= getEnemyEvaluation(localCoordinate[0],
                            localCoordinate[1])&&getSelfHealth(localX, y)!=-99) {
                        localCoordinate[0] = localX;
                    }
                }
            }
            // y増減２での探索
            for (int i = -2; i < 3; i++) {
                int localY = y + i;
                if (localY >= 0 && localY < 5) {
                    if (getEnemyEvaluation(x, localY) >= getEnemyEvaluation(localCoordinate[0],
                            localCoordinate[1])&&getSelfHealth(x, localY)!=-99) {
                        localCoordinate[0] = x;
                        localCoordinate[1] = localY;
                    }
                }
            }
        }
        return localCoordinate;
    }

    // Enemyの評価を返す
    public static int getEnemyEvaluation(int x, int y) {
        return enemyCoordinate[x][y];
    }

    // Enemyの評価を設定する
    public static void setEnemyEvaluation(int x, int y, int evaluation) {
        enemyCoordinate[x][y] = evaluation;
    }

    // 新たに自軍の船を設定する
    public static void newSelfCoordinate(int x, int y) {
        selfCoordinate[x][y] = 3;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(selfCoordinate[i][j]);
            }
            System.out.println();
        }
    }

    // 指定したhealthを適応する
    public static void setSelfHealth(int x, int y, int health, boolean isMove) {
        String localStyle = "-fx-base: #f185ff";
        System.out.println(health);
        int copyHealth = getSelfHealth(x, y);
        // 自軍の船があるか確認
        if ((selfCoordinate[x][y] != 0 && selfCoordinate[x][y] != -99) || isMove) {
            // この処理でhealthが０となる
            if (health == 0 && copyHealth != 0) {
                System.out.println("Dead");
                if (!isMove) {
                    selfCoordinate[x][y] = -99;
                    // black
                    localStyle = "-fx-base: #747575";
                } else {
                    selfCoordinate[x][y] = 0;
                    localStyle="-fx-base: #f2f2f2";
                }

            } else {
                selfCoordinate[x][y] = health;
                switch (health) {
                    case 1:
                        // red
                        localStyle = "-fx-base: #ff0000";
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
            }
            GUI_DB.buttonSelfCoordinates[x][y].setStyle(localStyle);
        }
        showTwoArray(selfCoordinate);
    }

    // healthを取得する
    public static int getSelfHealth(int x, int y) {
        return selfCoordinate[x][y];
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

    // 自軍の座標を取得する x優勢
    public static int[][] getSelfCoordinate() {
        int[][] selfCoordinate = new int[4][2];
        int localCount = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (getSelfHealth(i, j) > 0) {
                    selfCoordinate[localCount][0] = i;
                    selfCoordinate[localCount][1] = j;
                    localCount++;
                }
            }
        }
        return selfCoordinate;
    }

    // 自軍を移動させる
    public static void moveSelfNaval(int xFrom, int yFrom, int xTo, int yTo) {
        int localHealth = getSelfHealth(xFrom, yFrom);
        System.out.println(xFrom + "," + yFrom);
        System.out.println(xTo + "," + yTo);
        setSelfHealth(xFrom, yFrom, 0, true);
        setSelfHealth(xTo, yTo, localHealth, true);
        int xDeviation = xTo - xFrom;
        int yDeviation = yTo - yFrom;
        // 方角と偏差の処理
        String localString;
        if (yFrom == yTo) {
            // 東西に移動する場合
            if (xDeviation > 0) {
                // 東方向へ移動
                localString = "East: " + Math.abs(xDeviation);
            } else {
                // 西方向へ移動
                localString = "West: " + Math.abs(xDeviation);
            }
        } else {
            // 南北に移動する場合
            if (yDeviation > 0) {
                // 南方向へ移動
                localString = "South: " + Math.abs(yDeviation);
            } else {
                // 北方向へ移動
                localString = "North: " + Math.abs(yDeviation);
            }
        }
        xFrom += 1;
        yFrom += 1;
        xTo += 1;
        yTo += 1;
        GUI_DB.labelNextWay.setText(
                "(" + xFrom + "," + yFrom + ")" + "->" + "(" + xTo + "," + yTo + ") Move STATE:" + localString);
    }
}
