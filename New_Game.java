public class New_Game {
    public static void NewGame() {
        for (int i = 0; i < 5; i++) {
            VariableDB.setEnemyCoordinate(i, 1, 0);
            VariableDB.setEnemyCoordinate(i, 3, 0);
            VariableDB.setEnemyCoordinate(i, 5, 0);
        }
    }
}
