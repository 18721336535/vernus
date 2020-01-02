package code.zs_cx_mapping;

import code.commons.DBUtils;

import java.sql.*;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zengbingqing
 * @Description:
 * @Date: 2019/12/31
**/
public class Step1_GetCXWset {

    static Pattern pt = Pattern.compile("[\\u4e00-\\u9fa5]+|[A-Za-z0-9 |.]+");
    static Pattern ptch = Pattern.compile("[\\u4e00-\\u9fa5]+");
    static Pattern pten_num = Pattern.compile("[A-Za-z0-9]+");
    static Pattern pten = Pattern.compile("[A-Za-z0-9]+");
    static Pattern ptnum = Pattern.compile("[0-9]+");
    static Matcher matcher = null;


    public Set<String> generateCXWset() throws SQLException {
        Connection conn = DBUtils.getConn();
        Statement stm = conn.createStatement();
        String[] cxColum = {
                "model_name",
                "remark",
                "group_name",
                "group_code",
                "series_name",
                "series_code",
                "manufacturer_name",
                "brand_code",
                "brand_name",
                "car_category_name",
                "year_pattern",
                "exhaust_capability",
                "seat_count"};
        String sql = null;
        ResultSet res = null;
        Set<String> wset = new TreeSet<String>();
        String str = null;
        for (int i = 0; i < cxColum.length; i++) {
            sql = "select " + cxColum[i] + " from paic_car_model_working ";
            res = stm.executeQuery(sql);
            while (res.next()) {
                str = res.getString(cxColum[i]);
                if (str != null && !str.trim().isEmpty()) {
                    wset.addAll(spiliteWord(res.getString(cxColum[i])));
                }

            }
        }
        return wset;
    }

    public Set<String> spiliteWord(String colum) {

        Set<String> set = new TreeSet<String>();
        matcher = ptch.matcher(colum);
        String slice = null;
        char[] charay = null;
        while (matcher.find()) {
            slice = matcher.group();
            if (slice != null && !slice.trim().isEmpty()) {
                set.add(slice);
                charay = slice.toCharArray() ;
                for(char ch: charay) set.add(ch+"");
            }
        }

        matcher = pten_num.matcher(colum);
        while (matcher.find()) {
            slice = matcher.group();
            if (slice != null && !slice.trim().isEmpty()) {
                set.add(slice);
            }
        }
        matcher = pten.matcher(colum);
        while (matcher.find()) {
            slice = matcher.group();
            if (slice != null && !slice.trim().isEmpty()) {
                set.add(slice);
            }
        }

        matcher = ptnum.matcher(colum);
        while (matcher.find()) {
            slice = matcher.group();
            if (slice != null && !slice.trim().isEmpty()) {
                set.add(slice);
            }
        }

        return set;
    }

    public  static void  mian(String args[]) throws Exception {
        Set<String> zsSet = new Step1_GetCXWset().generateCXWset();
        String sql = "insert into wset(wname, wscore)values(?,?);";
        Connection cn= DBUtils.getConnectionInstance();
        PreparedStatement pst =  cn.prepareStatement(sql);
        for(String str: zsSet){
            pst.setString(1,str);
            pst.setDouble(2, str.length()> 1 ? 10.0:1.0);
            pst.executeUpdate();

        }



    }

}
