package code.core;

import code.commons.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Step3_GenerateFvector {
    public  void generateFv() throws SQLException {
        Connection con = DBUtils.getConn();
        Statement st = con.createStatement();
        String temp = null;
        ResultSet rs = null;
        String sql = "select * from cx_car_model";
        rs = st.executeQuery(sql);
        while(rs.next()){


        }
    }

}
