package code.zs_cx_mapping;

import code.commons.DBUtils;
import code.commons.RedisUtils;

import java.sql.*;
import java.util.HashMap;


public class Step2_LoadToCache {
    public static void main(String args[]){
        new Step2_LoadToCache().loadToCache();
    }

    /**
     * @Author: zengbingqing
     * @Description: 词集对应的编号和权重载入缓存
     * @Date: 2019/12/4
    **/
    public  void loadToCache() {
        HashMap<String,String> name_id_map = new HashMap<String,String>();
        HashMap<String,String> name_score_map = new HashMap<String,String>();
        Connection conn = null;
        PreparedStatement ps = null;
        Statement stm = null;
        ResultSet res = null;
        String sql = null;
        String name = null;
        String score = null;
        String id = null;

        conn = DBUtils.getConn();
        try {
            sql = "select id, facture_name, wscore from wset_data ";
            stm = conn.createStatement();
            res = stm.executeQuery(sql);
            while(res.next()){
                name = res.getString("facture_name");
                score = res.getString("score");
                id = res.getString("id");
                name_id_map.put(name,id);
                name_score_map.put(name,score);
            }
            RedisUtils.setHashMap("name_id_map",name_id_map);
            RedisUtils.expire("name_id_map",30*24*3600);
            RedisUtils.setHashMap("name_score_map",name_score_map);
            RedisUtils.expire("name_score_map",30*24*3600);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.releaseSource(conn,stm,res);

    }
}
