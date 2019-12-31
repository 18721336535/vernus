package code.zs_cx_mapping;

import code.commons.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    static Pattern pt = Pattern.compile("[\\u4e00-\\u9fa5]+|[A-Za-z0-9 |.]+]");
    static Matcher matcher = null;


    public void generateCXWset() throws SQLException {
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
        Set<String> tempset = new TreeSet<String>();
        String str = null;
        for (int i = 0; i < cxColum.length; i++) {
            sql = "select " + cxColum[i] + " from paic_car_model_working ";
            res = stm.executeQuery(sql);
            while (res.next()) {
                str = res.getString(cxColum[i]);
                if (str != null && !str.trim().isEmpty()) {
                    tempset.add(res.getString(cxColum[i]));
                }

            }

        }
    }

    public Set<String> spiliteWord(String colum) {

        Set<String> set = new TreeSet<String>();
        matcher = pt.matcher(colum);
        String slice = null;
        while (matcher.find()) {
            slice = matcher.group();
            if (slice != null && !slice.trim().isEmpty()) {
                set.add(slice);
            }
        }
        return set;
    }

}
