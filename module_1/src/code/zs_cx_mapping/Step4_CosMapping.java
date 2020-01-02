package code.zs_cx_mapping;

import java.util.*;

public class Step4_CosMapping {
    Map<String, List<String>> preMap = new HashMap<String,List<String>>();
    public void initRelation(){
        // zs cx品牌 和车系 建立初步对应关系 load to map

    }

    public void mappingEntry(Map<String, Set<String>> idMap){
        Double[] zsfv= null;
        Double[] cxfv= null;
        double score= 0d;
        double scoreTemp= 0d;
        Map<String,Double> recordMap = new HashMap<String,Double>();
        Set<String> backupSet = new HashSet<String>();
        for(String zsId :idMap.keySet()){
            // 根据zsId，获取zs特征向量 array Double[]

            for(String cxId:idMap.get(zsId)){
                // 根据cxId，获取cx特征向量 array Double[]
                scoreTemp = cosMath(zsfv,cxfv);
                recordMap.put(cxId,scoreTemp);
                if(scoreTemp > score) score = scoreTemp;
            }

            for(Map.Entry<String, Double> en:recordMap.entrySet()){
                if(en.getValue() == score) backupSet.add(en.getKey());
            }

            //update table zsId-cxId status and score

        }


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
