package code.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataHandleCore {

    static Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]+|[A-Za-z0-9 |.]+|[-]+]");
    static Matcher matcher = null;

    /**
     * @Author: zengbingqing
     * @Description: 获取列的词集
     * @Date: 2019/12/3
    **/
    public Set<String> getDtaBy_F_Ly(String sql, String cloumName, Connection conn , Statement stm){
        ResultSet res = null;
        Set<String> reSet = null;
        Set<String> temSet = null;
        String value = null;
        try {
            res = stm.executeQuery(sql);
            reSet = new HashSet<String>();
            while(res.next()){
                value = res.getString(cloumName);
                temSet = preHandle_ly(value,cloumName);
                reSet.addAll(temSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reSet;

    }

    /**
     * @Author: zengbingqing
     * @Description:
     * @Date: 2019/12/4
    **/
    public Set<String> preHandle_ly(String value, String cloumName){
        Set<String> tempSet = new HashSet<String>();
        if("seat_number".equals(cloumName)) {
            value = value.replaceAll("\\(选配\\)","").replaceAll("\\s+","");
        }
        String[] values = value.split(" ");
        return patternMatchSplite(values,cloumName);
    }

    /**
     * @Author: zengbingqing
     * @Description:
     * @Date: 2019/12/4
    **/
    public Set<String> patternMatchSplite(String[] values, String cloumName){
        Set<String> tempSet = new HashSet<String>();
        String strtemp  = null;
       for(String str:  values){
           Matcher mch = pattern.matcher(str);
           while(mch.find()){
               strtemp = mch.group();
               tempSet.add(strtemp);
           }
       }
       return tempSet;
    }

    /**
     * @Author: zengbingqing
     * @Description:
     * @Date: 2019/12/4
    **/
    public  void sortSet(Set<String> set1,Set<String> set2){
        set1.addAll(set1);
    }


    public static void main(String args[]){
        String strtemp  = null;
        String str = "";
            Matcher mch = pattern.matcher(str);
            while(mch.find()){
                strtemp = mch.group();
                System.out.println(strtemp);
            }
        }



}
