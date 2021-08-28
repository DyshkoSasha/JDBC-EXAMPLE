import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        DataBase dataBase = new DataBase();
        dataBase.dropUsersTable(EnuConect.NAME.getEnuConect());
        dataBase.createTable(EnuConect.NAME.getEnuConect());
        UUID uuid = UUID.randomUUID();
        UUID uuid1 = UUID.randomUUID();
        User user1 = new User(uuid, "Yuzik", "Labeckiy", 33);
        User user2 = new User(uuid1, "Arkadiy", "Petyshinskiy", 31);
        User user3 = new User(UUID.randomUUID(), "Andrei", "Kynin", 32);
        User user4 = new User(UUID.randomUUID(), "Sasha", "Soroka", 41);

        dataBase.addUserTable(user1, EnuConect.NAME.getEnuConect());
        dataBase.addUserTable(user2, EnuConect.NAME.getEnuConect());
        dataBase.addUserTable(user3, EnuConect.NAME.getEnuConect());

//        dataBase.removeUserNa(EnuConect.TABLE, uuid);

        List<User> list = dataBase.getAllUsers(EnuConect.NAME.getEnuConect());
        System.out.println(list);

        User user = dataBase.getUserById(EnuConect.NAME.getEnuConect(), uuid);
        System.out.println(user);

        dataBase.updateUsers(EnuConect.NAME.getEnuConect(), uuid1, user4);

//        dataBase.deleteUsers(EnuConect.NAME.getEnuConect());
    }
}
