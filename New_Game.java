public class New_Game {
    public static void NewGame() {
        for (int i = 0; i < 6; i++) {
            VariableDB.SetEnemyCoodinate(i, 1, 0);
            VariableDB.SetEnemyCoodinate(i, 3, 0);
            VariableDB.SetEnemyCoodinate(i, 5, 0);
        }
    }
}
