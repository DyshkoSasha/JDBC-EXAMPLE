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

    public void createTableAdress(String nameTable) {            // создаем таблицу
        String insert = "CREATE TABLE " + nameTable +
                " (id_adress VARCHAR(50) PRIMARY KEY, " +
                "    city VARCHAR(50), " +
                "    street  VARCHAR(50), " +
                "    house   INTEGER);";
        connection = getConnection();
        try {
            PreparedStatement psSt = connection.prepareStatement(insert);
            psSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTableUser(String nameTable) {            // создаем таблицу
        String insert = "CREATE TABLE " + nameTable +
                "    (id_user VARCHAR(50) PRIMARY KEY, " +
                "    first_name VARCHAR(50), " +
                "    last_name  VARCHAR(50), " +
                "    age       INTEGER," +
                "    id_adress VARCHAR(50)," +
                "FOREIGN KEY (id_adress)  REFERENCES user_address (id_adress) ON DELETE CASCADE);";
        connection = getConnection();
        try {
            PreparedStatement psSt = connection.prepareStatement(insert);
            psSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void addTableAdress(Adress adress, String nameTableAdress) {             //добавляем в таблицу
        String insertAdress = "INSERT INTO " + nameTableAdress + " (id_adress ,city, street, house)  " +
                "VALUES  (?,?,?,?)";
        try {
            PreparedStatement prSTAdress = getConnection().prepareStatement(insertAdress);
            prSTAdress.setString(1, String.valueOf(adress.getId_adress()));
            prSTAdress.setString(2, adress.getCity());
            prSTAdress.setString(3, adress.getStreet());
            prSTAdress.setInt(4, adress.getHouse());
            prSTAdress.addBatch();
            prSTAdress.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addTableUser(User user, String nameTableUser) {             //добавляем в таблицу
        String insertUser = "INSERT INTO " + nameTableUser + " (id_user ,first_name, last_name, " +
                "age, id_adress) VALUES  (?,?,?,?,?)";

        try {
            PreparedStatement prSTUser = getConnection().prepareStatement(insertUser);
            prSTUser.setString(1, String.valueOf(user.getId_user()));
            prSTUser.setString(2, user.getFirstName());
            prSTUser.setString(3, user.getLastName());
            prSTUser.setInt(4, user.getAge());
            prSTUser.setString(5, String.valueOf(user.getId_adress()));
            prSTUser.addBatch();
            prSTUser.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteUserById(String nameTable, UUID uuid) {           // удаляем по id
        String insert = "DELETE FROM " + nameTable + " WHERE id_adress =?";
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

    public List<User> getUserByHouse(String nameTableAdress, Integer house) {                 //достаем юзера по id
        String insert = "SELECT * FROM " + nameTableAdress + " WHERE house=" + house;
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
}
