package code.commons;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zengbingqing
 * @Description:
 * @Date: 2019/11/19
**/
public class RedisUtils {
   static Jedis jds = initRedis();

   /**
    * @Author: zengbingqing
    * @Description:
    * @Date: 2019/12/3
   **/
   public static  Jedis getJedisInstance(){
       return jds;
   }


    /**
     * @Author: zengbingqing
     * @Description:
     * @Date: 2019/11/19
    **/
    public static  Jedis initRedis(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("123456");
        return jedis;
    }
    /**
     * @Author: zengbingqing
     * @Description:
     * @Date: 2019/11/19
     **/
    public static  String setx(String key,int seconds, String value){
        Jedis jedis = getJedisInstance();
        jedis.select(0);
        return jedis.setex(key,seconds,value);
    }
    /**
     * @Author: zengbingqing
     * @Description:
     * @Date: 2019/11/19
     **/
    public static  void del(String key){
        Jedis jedis = getJedisInstance();
        jedis.select(0);
        jedis.del(key);
    }
 /**
  * @Author: zengbingqing
  * @Description: 设置过期时间s
  * @Date: 2019/12/3
 **/
    public static  void expire(String key,int second){
        Jedis jedis = getJedisInstance();
        jedis.select(0);
        jedis.expire(key,second);
    }

    /**
     * @Author: zengbingqing
     * @Description:
     * @Date: 2019/11/19
     **/
    public static  void setHashMap(String key, HashMap<String,String> map){
        Jedis jedis = getJedisInstance();
        jedis.select(0);
        jedis.hmset(key,map);
    }

    /**
     * @Author: zengbingqing
     * @Description:
     * @Date: 2019/11/19
     **/
    public static Map<String,String> getHashMap(String key){
        Jedis jedis = getJedisInstance();
        jedis.select(0);
        return jedis.hgetAll(key);
    }

    public static  void main(String args[]){
        Jedis jds = initRedis();
        jds.set("123","we556");
        System.out.println("--=-="+jds.get("123"));
        jds.del("123");
    }
}
