package code.zs_cx_mapping;

import code.commons.DBUtils;
import code.commons.RedisUtils;

import java.sql.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Step3_GenerateFvector {

    Map<String,String> name_id_map = RedisUtils.getHashMap("name_id_map");
    Map<String,String> name_score_map=  RedisUtils.getHashMap("name_score_map");
    static Pattern pt = Pattern.compile("[\\u4e00-\\u9fa5]+|[A-Za-z0-9 |.]+]");
    static Matcher matcher = null;
    String[] cxColum = {
            "id",
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

    public  void generateFv() throws SQLException {
        Connection con = DBUtils.getConn();
        Statement st = con.createStatement();
        String temp = null;
        ResultSet rs = null;
        String sql = "select * from cx_car_model_detail";
        rs = st.executeQuery(sql);
        Set<String> tset = new TreeSet();
        String insertsql = " insert into paic_car_model_fv(id_cx,colum_fv,colum_wset)values(?,?,?)";
        PreparedStatement pst = con.prepareStatement(insertsql);
        String[] fv = new String[14000];
        initZeroArray(fv);
        while(rs.next()){
            tset.clear();
            String id = rs.getString(cxColum[0]);
            tset.addAll(spiliteWord(rs.getString(cxColum[1])));
            tset.addAll(spiliteWord(rs.getString(cxColum[2])));
            tset.addAll(spiliteWord(rs.getString(cxColum[3])));
            tset.addAll(spiliteWord(rs.getString(cxColum[4])));
            tset.addAll(spiliteWord(rs.getString(cxColum[5])));
            tset.addAll(spiliteWord(rs.getString(cxColum[6])));
            tset.addAll(spiliteWord(rs.getString(cxColum[7])));
            tset.addAll(spiliteWord(rs.getString(cxColum[8])));
            tset.addAll(spiliteWord(rs.getString(cxColum[9])));
            tset.addAll(spiliteWord(rs.getString(cxColum[10])));
            tset.addAll(spiliteWord(rs.getString(cxColum[11])));
            tset.addAll(spiliteWord(rs.getString(cxColum[12])));
            tset.addAll(spiliteWord(rs.getString(cxColum[12])));
            //
            for(String slice: tset){
                fv[Integer.parseInt(name_id_map.get(slice))] = name_score_map.get(slice);
            }

            pst.setString(1,id);
            pst.setString(2,fv.toString());
            pst.setString(3,tset.toString());
            pst.execute();

            updateSql(id,con);

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

    public void updateSql(String id,Connection conn) throws SQLException {
        String sql = " update cx_car_model_detail set mystatus = ? where id = ? ";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,"1");
        st.setString(2,id);
        st.executeUpdate();
        st.close();
    }

    void initZeroArray(String[] fv){
        for(int i = 0;i< fv.length;i++){
            fv[i] = "0.0";
        }
    }

}
