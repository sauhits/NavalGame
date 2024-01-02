public class New_Game {
    public static void NewGame() {
        for (int i = 0; i < 5; i++) {
            VariableDB.setEnemyCoodinate(i, 1, 0);
            VariableDB.setEnemyCoodinate(i, 3, 0);
            VariableDB.setEnemyCoodinate(i, 5, 0);
        }
    }
}
