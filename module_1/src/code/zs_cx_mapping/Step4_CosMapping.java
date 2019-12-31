package code.zs_cx_mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Step4_CosMapping {
    Map<String, List<String>> preMap = new HashMap<String,List<String>>();
    public void initRelation(){
        // zs cx品牌 和车系 建立初步对应关系 load to map

    }

    public void mappingEntry(){


    }

    public double cosMath(Double[] fv_zs,Double[] fv_cx){
        double mol = 0d;
        double den1 = 0d;
        double den2 = 0d;
        double temp  = 0d;
        double res = 0d;
        for(int i= 0;i< fv_cx.length;i++){
            temp += fv_zs[i]*fv_cx[i];
        }
        mol = temp;

        temp = 0d;
        for(double d:fv_zs){
            temp += d*d;
        }
        den1 = Math.sqrt(temp);

        temp = 0d;
        for(double d:fv_cx){
            temp += d*d;
        }
        den2 = Math.sqrt(temp);

        res = mol /(den1*den2);

        return res;
    }

}
