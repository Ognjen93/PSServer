package model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
//import net.etfbl.is.pozoriste.controller.PregledRadnikaController;
import model.dto.AdministrativniRadnik;

public class AdministratorDAO {

    public static void dodajAdministrativnogRadnika(AdministrativniRadnik admin) {
        System.out.println("DODAVANJE ADMINA: " + admin.getHash());
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall("{call dodavanjeAdministrativnogRadnika(?,?,?,?,?,?,?,?)}");
            callableStatement.setString(1, admin.getIme());
            callableStatement.setString(2, admin.getPrezime());
            callableStatement.setString(3, admin.getJmb());
            callableStatement.setString(4, admin.getKontakt());
            callableStatement.setString(5, admin.getKorisnickoIme());
            callableStatement.setString(6, admin.getHash());
            callableStatement.setString(7, admin.getTipRadnika());
            callableStatement.registerOutParameter(8, Types.INTEGER);

            callableStatement.executeQuery();

            admin.setIdRadnika(callableStatement.getInt(8));
            System.out.println("ADMIN ID: " + admin.getIdRadnika());
        } catch (SQLException ex) {
           // Logger.getLogger(BIletarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().checkIn(connection);
            }
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                  //  Logger.getLogger(BIletarDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static boolean provjeriMaticniBrojUBazi(String jmb) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String pomJMB = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT jmb from radnik where jmb=" + jmb);
            while (rs.next()) {
                pomJMB = rs.getString(1);
            }

        } catch (SQLException ex) {
            //Logger.getLogger(DodajRadnikaController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().checkIn(connection);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(DodajRadnikaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return pomJMB == null?false:true;
    }

    public static boolean postojiUBaziKorisnickoIme(String korisnickoIme) {
        boolean postoji = false;
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall("{call provjeraKorisnickogImena(?,?)}");
            callableStatement.setString(1, korisnickoIme);
            callableStatement.registerOutParameter(2, Types.BOOLEAN);

            callableStatement.executeQuery();
            postoji = callableStatement.getBoolean(2);
            if (postoji) {
                return true;
            } else {
                return false;

            }
        } catch (SQLException ex) {
            //Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionPool.getInstance().checkIn(connection);
        }
        return false;
    }

    public static  boolean postojiUBaziLozinka(String lozinka) {
        boolean postoji = false;
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall("{call provjeraLozinke(?,?)}");
            callableStatement.setString(1, lozinka);
            callableStatement.registerOutParameter(2, Types.BOOLEAN);
            callableStatement.executeQuery();
            postoji = callableStatement.getBoolean(2);
            if (postoji) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            //Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionPool.getInstance().checkIn(connection);
        }
        return false;
    }

//ovo mozda treba izbaciti
  /*  public static void ubaciUTabeluRadnik() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        AdministrativniRadnik admin;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from admini_info");
            while (rs.next()) {

                admin = new AdministrativniRadnik(rs.getString("Ime"), rs.getString("Prezime"), rs.getString("JMB"), rs.getBoolean("StatusRadnika"), rs.getString("Kontakt"), rs.getString("KorisnickoIme"), rs.getString("HashLozinke"), rs.getString("TipKorisnika"));
                admin.setIdRadnika(rs.getInt("Id"));
                if (!PregledRadnikaController.radniciObservableList.contains(admin)) {
                    PregledRadnikaController.radniciObservableList.add(admin);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PregledRadnikaController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().checkIn(connection);
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PregledRadnikaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }*/

    public static void izmjeniAdministratora(AdministrativniRadnik admin) {
        System.out.println("IZMJENA ADMINA : : : " + admin.getHash());
        System.out.println("BILETAR ID : " + admin.getIdRadnika());
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall("{call azuriranjeRadnikaKojiKoristiSistem(?,?,?,?,?,?,?)}");

            callableStatement.setString(1, admin.getIme());
            callableStatement.setString(2, admin.getPrezime());
            callableStatement.setString(3, admin.getJmb());
            callableStatement.setInt(4, admin.getIdRadnika());
            callableStatement.setBoolean(5, admin.isStatusRadnika());
            callableStatement.setString(6, admin.getKorisnickoIme());
            callableStatement.setString(7, admin.getHash());

            callableStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(UmjetnikDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().checkIn(connection);
            }
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UmjetnikDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static Integer vratiId(String korisnickoIme) {
        Integer id = null;
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();
            callableStatement = connection.prepareCall("{call idAdmina(?,?)}");

            callableStatement.setString(1, korisnickoIme);
            callableStatement.registerOutParameter(2, Types.INTEGER);

            callableStatement.executeQuery();
            id = callableStatement.getInt(2);
        } catch (SQLException ex) {
            Logger.getLogger(UmjetnikDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().checkIn(connection);
            }
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UmjetnikDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return id;
    }

}