import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class Main {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        dataBase.dropUsersTable(EnuConect.NAME_USERS.getEnuConect());
        dataBase.dropUsersTable(EnuConect.NAME_ADRESS.getEnuConect());

        dataBase.createTableAdress(EnuConect.NAME_ADRESS.getEnuConect());
        dataBase.createTableUser(EnuConect.NAME_USERS.getEnuConect());

        UUID uuid1 = UUID.randomUUID();
        User user1 = new User(UUID.randomUUID(), "Yuzik", "Labeckiy", 33, uuid1);
        Adress adress1 = new Adress(uuid1, "Minsk", "Nezavisimosti", 3);
        dataBase.addTable(user1, EnuConect.NAME_USERS.getEnuConect(), adress1, EnuConect.NAME_ADRESS.getEnuConect());

        UUID uuid2 = UUID.randomUUID();
        User user2 = new User(UUID.randomUUID(), "Arkadiy", "Petyshinskiy", 31, uuid2);
        Adress adress2 = new Adress(uuid2, "Minsk", "Nezavisimosti", 3);
        dataBase.addTable(user2, EnuConect.NAME_USERS.getEnuConect(), adress2, EnuConect.NAME_ADRESS.getEnuConect());

        UUID uuid3 = UUID.randomUUID();
        User user3 = new User(UUID.randomUUID(), "Andrei", "Kynin", 32, uuid3);
        Adress adress3 = new Adress(uuid3, "Minsk", "Nezavisimosti", 5);
        dataBase.addTable(user3, EnuConect.NAME_USERS.getEnuConect(), adress3, EnuConect.NAME_ADRESS.getEnuConect());

        UUID uuid4 = UUID.randomUUID();
        User user4 = new User(UUID.randomUUID(), "Sasha", "Soroka", 41, uuid4);
        Adress adress4 = new Adress(uuid4, "Minsk", "Nezavisimosti", 1);
        dataBase.addTable(user4, EnuConect.NAME_USERS.getEnuConect(), adress4, EnuConect.NAME_ADRESS.getEnuConect());

        dataBase.deleteUserById(EnuConect.NAME_ADRESS.getEnuConect(), uuid4);

        List<User> list = dataBase.getUserByHouse(EnuConect.NAME_ADRESS.getEnuConect(),adress1.getHouse());
        System.out.println(list);
    }
}
