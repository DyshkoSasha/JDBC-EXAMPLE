import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();

        dataBase.dropUsersTable(EnuConect.NAME_ADRESS.getEnuConect());
        dataBase.dropUsersTable(EnuConect.NAME_USERS.getEnuConect());

        dataBase.createTableUser(EnuConect.NAME_USERS.getEnuConect());
        dataBase.createTableAdress(EnuConect.NAME_ADRESS.getEnuConect());

        UUID uuid1 = UUID.randomUUID();
        User user1 = new User(uuid1, "Yuzik", "Labeckiy", 33);
        Adress adress1 = new Adress(uuid1, "Minsk", "Nezavisimosti", "3");
        dataBase.addUserTable(user1, EnuConect.NAME_USERS.getEnuConect());
        dataBase.addAdressTable(adress1,EnuConect.NAME_ADRESS.getEnuConect());

        UUID uuid2 = UUID.randomUUID();
        User user2 = new User(uuid2, "Arkadiy", "Petyshinskiy", 31);
        Adress adress2 = new Adress(uuid2, "Minsk", "Nezavisimosti", "3");
        dataBase.addUserTable(user2, EnuConect.NAME_USERS.getEnuConect());
        dataBase.addAdressTable(adress2,EnuConect.NAME_ADRESS.getEnuConect());

        UUID uuid3 = UUID.randomUUID();
        User user3 = new User(uuid3, "Andrei", "Kynin", 32);
        Adress adress3 = new Adress(uuid3, "Brest", "Lenina", "5");
        dataBase.addUserTable(user3, EnuConect.NAME_USERS.getEnuConect());
        dataBase.addAdressTable(adress3,EnuConect.NAME_ADRESS.getEnuConect());

        UUID uuid4 = UUID.randomUUID();
        User user4 = new User(uuid4, "Sasha", "Soroka", 41);
        Adress adress4 = new Adress(uuid4, "Beryoza", "Lenina", "1");
        dataBase.addUserTable(user4, EnuConect.NAME_USERS.getEnuConect());
        dataBase.addAdressTable(adress4,EnuConect.NAME_ADRESS.getEnuConect());

//        dataBase.removeUserNa(EnuConect.TABLE, uuid);

//        List<User> list = dataBase.getAllUsers(EnuConect.NAME_USERS.getEnuConect());
//        System.out.println(list);
//
//        User user = dataBase.getUserById(EnuConect.NAME_USERS.getEnuConect(), uuid1);
//        System.out.println(user);
//
//        dataBase.updateUsers(EnuConect.NAME_USERS.getEnuConect(), uuid2, user4);

//        dataBase.deleteUsers(EnuConect.NAME.getEnuConect());
    }
}
