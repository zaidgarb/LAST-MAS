public class Driver {
    static UserAgent ua;
    static ManagerAgent ma;

    public static void main(String[] args) {
        ma = new ManagerAgent();
        ua = new UserAgent(ma);
    }
}
