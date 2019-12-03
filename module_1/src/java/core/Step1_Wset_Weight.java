package java.core;

import java.common.CommonUtils;
import java.common.DBUtils;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

public class Step1_Wset_Weight {
    //特征字段选取
    static String[] lyClums = {
            "brand",
            "model_family",
            "model",
            "version_short",
            "engin_model",
            "transmisson",
            "engine",
            "body",
            "seat_number"
    };

    //特征字段选取
    static String[] zsClums = {
            "brand",
            "series",
            "model",
            "version_short",
            "engin",
            "transmisson",
            "engine",
            "sell_name"
    };
    public  static void main(String args[]) throws Exception{
        Connection conn = null ;
        PreparedStatement ps = null;
        Statement stm = null;
        String sql = null;
        BigDecimal bd = null;
        BigDecimal bd1 = new BigDecimal("10");
        BigDecimal bd2 = new BigDecimal("1");

        Set<String> sortSetLy = new TreeSet<String>();
        Set<String> sortSetZs = new TreeSet<String>();

        Set<String> sortSetTotal = new TreeSet<String>();
        conn = DBUtils.getConn();
        stm = conn.createStatement();

        String clSet = null;
        String sqlly = null;
        for(String cloum :lyClums){
            sqlly = "select "+ cloum +" from t_car_models where "+ cloum +" is not null";

        }


    }


}
