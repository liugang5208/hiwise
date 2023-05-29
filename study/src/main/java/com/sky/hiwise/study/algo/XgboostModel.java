package com.sky.hiwise.study.algo;

import ml.dmlc.xgboost4j.java.Booster;
import ml.dmlc.xgboost4j.java.XGBoost;
import ml.dmlc.xgboost4j.java.XGBoostError;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2022-12-28 14:32
 **/
public class XgboostModel {


    private static final Map<String, Booster> boosterMap = new HashMap<>();


    private static Booster getBooster(String filePath) {
        try {
            URL modelUrl = Thread.currentThread().getContextClassLoader().getResource(filePath);
            System.out.println("modelUrl:" + modelUrl);
            UrlResource resource = new UrlResource(modelUrl);
            InputStream is = resource.getInputStream();
            return XGBoost.loadModel(is);
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (XGBoostError xgBoostError) {
            xgBoostError.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("before....");
        getBooster("model/mt_fencerank_xgb_v1.bin");
        System.out.println("after....");
    }
}
