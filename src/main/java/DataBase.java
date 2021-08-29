import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataBase {
    private Connection connection;

    private Connection getConnection() {             // конектимся с ба3ой
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("Driver not found");
        }

        String coonectionString = "jdbc:postgresql://localhost:5432/pred_project";

        try {
            connection = DriverManager.getConnection(coonectionString, EnuConect.USER.getEnuConect(),
                    EnuConect.PASSWORD.getEnuConect());
        } catch (SQLException throwables) {
            throwables.getMessage();
        }
        return connection;
    }

    public void dropUsersTable(String nameTable) {
        String insert = "DROP TABLE " + nameTable;
        connection = getConnection();
        try {
            PreparedStatement psSt = connection.prepareStatement(insert);
            psSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTableUser(String nameTable) {            // создаем таблицу
        String insert = "CREATE TABLE IF NOT EXISTS " + nameTable +
                " (id_user VARCHAR(50) PRIMARY KEY NOT NULL, " +
                "    first_name VARCHAR(50) not NULL, " +
                "    last_name  VARCHAR(50) not NULL, " +
                "    age       INTEGER     not NULL);";
        connection = getConnection();
        try {
            PreparedStatement psSt = connection.prepareStatement(insert);
            psSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTableAdress(String nameTable) {            // создаем таблицу
        String insert = "CREATE TABLE IF NOT EXISTS " + nameTable +
                " (id_adress VARCHAR(50) PRIMARY KEY, " +
                "    city VARCHAR(50) not NULL, " +
                "    street  VARCHAR(50) not NULL, " +
                "    house   VARCHAR(50) not NULL," +
                "FOREIGN KEY (id_adress)  REFERENCES users (id_user));";
        connection = getConnection();
        try {
            PreparedStatement psSt = connection.prepareStatement(insert);
            psSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addUserTable(User user, String nameTale) {             //добавляем в таблицу
        String insert = "INSERT INTO " + nameTale + " (id_user ,first_name, last_name,age)  " +
                "VALUES  (?,?,?,?)";
        try {
            PreparedStatement prST = getConnection().prepareStatement(insert);
            prST.setString(1, String.valueOf(user.getId_user()));
            prST.setString(2, user.getFirstName());
            prST.setString(3, user.getLastName());
            prST.setInt(4, user.getAge());
            prST.addBatch();
            prST.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addAdressTable(Adress adress, String nameTale) {             //добавляем в таблицу
        String insert = "INSERT INTO " + nameTale + " (id_adress ,city, street, house)  " +
                "VALUES  (?,?,?,?)";
        try {
            PreparedStatement prST = getConnection().prepareStatement(insert);
            prST.setString(1, String.valueOf(adress.getId_adress()));
            prST.setString(2, adress.getCity());
            prST.setString(3, adress.getStreet());
            prST.setString(4, adress.getHouse());
            prST.addBatch();
            prST.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteUserById(String nameTable, UUID uuid) {           // удаляем по id
        String insert = "DELETE FROM " + nameTable + " WHERE id =?";
        connection = getConnection();
        try {

            PreparedStatement psSt = connection.prepareStatement(insert);
            String str = uuid.toString();
            psSt.setString(1, str);
            psSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers(String nameTable) {              // достаем всех из таблицы
        String insert = "SELECT * FROM " + nameTable;
        connection = getConnection();
        List<User> usersList = new ArrayList<>();
        try {
            PreparedStatement psSt = connection.prepareStatement(insert);
            ResultSet resultSet = psSt.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId_user(UUID.fromString(resultSet.getString(1)));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
                usersList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usersList;
    }

    public User getUserById(String nameTable, UUID uuid) {                 //достаем юзера по id
        String insert = "SELECT * FROM " + nameTable + " WHERE id =?";
        connection = getConnection();
        User user = null;
        try {
            PreparedStatement psSt = connection.prepareStatement(insert);
            String str = uuid.toString();
            psSt.setString(1, str);
            ResultSet resultSet = psSt.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId_user(UUID.fromString(resultSet.getString(1)));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getInt(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public void updateUsers(String nameTable, UUID uuid, User user) {           // изменяем по id
        User userDost = getUserById(nameTable, uuid);// а че за userDost? я не понимать
        try {
            userDost.setFirstName(user.getFirstName());
            userDost.setLastName(user.getLastName());
            deleteUserById(nameTable, uuid);
            addUserTable(userDost, nameTable);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteUsers(String nameTable) {           // удаляем всех
        String insert = "DELETE FROM " + nameTable;
        connection = getConnection();
        try {

            PreparedStatement psSt = connection.prepareStatement(insert);
            psSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
