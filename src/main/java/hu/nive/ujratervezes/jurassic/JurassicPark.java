package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> checkOverpopulation() {

        List<String> dinos = new ArrayList<>();
        String sql = "SELECT  breed " +
                "FROM dinosaur " +
                "WHERE actual > expected " +
                "ORDER BY breed ASC ;";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String dino = rs.getString("breed");
                dinos.add(dino);
            }
        } catch (SQLException e) {
            System.out.println("File not found!");
            ;
        }
        return dinos;
    }

}