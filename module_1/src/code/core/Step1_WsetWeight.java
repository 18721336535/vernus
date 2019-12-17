package code.core;

import code.commons.DBUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Author: zengbingqing
 * @Description: 生成词集权重类
 * @Date: 2019/12/4
**/
public class Step1_WsetWeight {

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
            sortSetLy.clear();
            sortSetLy = new DataHandleCore().getDtaBy_F_Ly(sqlly,cloum,conn,stm);
            new DataHandleCore().sortSet(sortSetTotal,sortSetLy);
        }


        sql = "insert into wset_data(facture_name,wscore)values(?,?)";
        ps = conn.prepareStatement(sql);
        for(String word: sortSetTotal){
            switch (word.length()){
                case 1:
                    bd = bd2;
                    break;
                default:
                    bd = bd1;
            }
            ps.setString(1,word);
            ps.setDouble(2,Double.parseDouble(bd.toString()));
            ps.execute();
        }
        ps.close();
        stm.close();
        conn.close();
    }


}
