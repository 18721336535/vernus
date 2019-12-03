package java.core;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataHandleCore {
    static Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]+|[A-Za-z0-9 |.]+|[-]+]");
    static Matcher matcher = null;

    /**
     * @Author: zengbingqing
     * @Description: 获取一列的词集
     * @Date: 2019/12/3
    **/
    public Set<String> getDtaBy_F_Ly(){
        Set<String> reSet = null;
        return reSet;
    }

}
