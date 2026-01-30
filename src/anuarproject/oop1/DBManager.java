package anuarproject.oop1;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
public class DBManager {
    private static DBManager instance;
    private final String url = "jdbc:postgresql://localhost:5432/CinemaSystemDB";
    private final String user = "postgres";
    private final String password = "0000";
    private DBManager() {}

    public static DBManager getInstance() {
        if (instance == null) {instance = new DBManager();}return instance;
    }

    public Connection connect() throws SQLException, ClassNotFoundException {
    return DriverManager.getConnection(url, user, password);
      }


    //CRUD

           //add film to DB
              public void Addfilm(Film film) throws SQLException, ClassNotFoundException {
                 String sql = "INSERT INTO films (title, price, genre) VALUES (?, ?, ?)";

                 try(Connection conn = connect();
                 PreparedStatement prstm = conn.prepareStatement(sql)){
                 prstm.setString(1,film.getTitle());
                 prstm.setDouble(2,film.getPrice());
                 prstm.setString(3,film.getGenre().name());
                  prstm.executeUpdate();
                  System.out.println("Film saved to DB");


              }catch (SQLException e){System.out.println("Error"+e.getMessage());}}

                    // ShowAllFilm
                  public List<Film> getAllFilms() throws SQLException, ClassNotFoundException {
                      List<Film> films = new ArrayList<>();
                      String sql = "SELECT * FROM films";

                      try (Connection conn = connect();
                           Statement stmt = conn.createStatement();
                           ResultSet rs = stmt.executeQuery(sql)) {

                          while (rs.next()) {
                              Film film = new Film(
                                      rs.getString("title"),
                                      rs.getDouble("price"),
                                      Genre.valueOf(rs.getString("genre"))  //enem sting
                              );
                              films.add(film);
                          }
                      } catch (SQLException e) {
                          e.printStackTrace();
                      }
                      return films;
                  }


                  //UPDATE PRICE
                  public void updateFilmPrice(String title, double newPrice) throws SQLException, ClassNotFoundException {
                      String sql = "UPDATE films SET price = ? WHERE title = ?";

                      try (Connection conn = connect();
                           PreparedStatement pstmt = conn.prepareStatement(sql)) {

                          pstmt.setDouble(1, newPrice);
                          pstmt.setString(2, title);
                          pstmt.executeUpdate();
                      } catch (SQLException e) {
                          e.printStackTrace();
                      }
                  }





                  //DELETE FILM
                  public void deleteFilm(String title) throws SQLException, ClassNotFoundException {
                      String sql = "DELETE FROM films WHERE title = ?";
                      try (Connection conn = connect();
                           PreparedStatement pstmt = conn.prepareStatement(sql)) {
                          pstmt.setString(1, title);
                          pstmt.executeUpdate();
                      } catch (SQLException e) {
                          e.printStackTrace();
                      }
                  }

    public void registerViewer(String username, String password, String fullName, double balance) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO users (username, password, role, full_name, balance) VALUES (?, ?, 'VIEWER', ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, fullName);
            pstmt.setDouble(4, balance);


            pstmt.executeUpdate();
            System.out.println("User registered successfully!");

        } catch (SQLException e) {
            System.out.println("Registration error: " + e.getMessage());
        }
    }


    public User login(String username, String password) throws SQLException, ClassNotFoundException {
        if (username.equals("admin") && password.equals("admin")) {
            return new Admin(0, "admin", "admin", null, null);
        }

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);


            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Viewer(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        }

        return null;
    }
    }