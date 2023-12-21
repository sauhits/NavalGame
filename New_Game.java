public class New_Game {
    public static void NewGame() {
        for (int i = 0; i < 6; i++) {
            VariableDB.SetCoodinate(i, 1, 0);
            VariableDB.SetCoodinate(i, 3, 0);
            VariableDB.SetCoodinate(i, 5, 0);
        }
    }
}
