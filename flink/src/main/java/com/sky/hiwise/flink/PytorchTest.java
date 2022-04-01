package com.sky.hiwise.flink;

import com.meituan.hadoop.afo.pytorchserving.PytorchServingLocalClient;
import com.meituan.hadoop.afo.pytorchserving.thrift.InferDataType;
import com.meituan.hadoop.afo.pytorchserving.thrift.InferObject;
import com.meituan.hadoop.afo.pytorchserving.thrift.InferTensor;
import com.meituan.hadoop.afo.pytorchserving.thrift.InferValue;
import com.meituan.hadoop.afo.pytorchserving.utils.MockUtils;
import com.meituan.hadoop.afo.pytorchserving.utils.TypeFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2022-03-09 17:38
 **/
public class PytorchTest {

    public static void main(String[] args) throws FileNotFoundException, MalformedURLException {

        InferValue inferValue = createPictureInferValue("https://s3plus.sankuai.com/v1/mss_217611ce09cf41e682ea066ca826ce67/small-file/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_202203041410202022-03-04cda46109876f46aa9f8ca5e29590cf9f.jpg");
        startLocalRpcClient(inferValue);
    }

    public static void startLocalRpcClient(InferValue inferValue1) {
                Map<String, InferTensor> param = new HashMap<String, InferTensor>();
        param.put("1", MockUtils.createInferTensor());

        InferValue inferValue = new InferValue();
        inferValue.tensorInputs = param;
        inferValue.dType = InferDataType.DT_TENSOR;
        //inferValue1.dType = InferDataType.DT_TENSOR;
        inferValue1.tensorInputs = param;
        System.out.println("inferValue1:" + inferValue1);

        PytorchServingLocalClient localClient = null;
        try {
            localClient = new PytorchServingLocalClient("127.0.0.1", 6006, "mnist", 1, "mnist_signature");
            Map<String, InferObject> response = localClient.predict(inferValue1);
            for(String key: response.keySet()) {
                System.out.println(response.get(key));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (localClient != null) {
                try {
                    localClient.shutdown();
                } catch (Exception exception) { }
            }
        }
    }

    public static InferValue createPictureInferValue(String imgFile) {

        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            URL url = new URL(imgFile);
            in = url.openStream();
            //in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            System.out.println(data);
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return TypeFactory.createPictureInferValue(data);   // 使用 TypeFactory.createPictureInferValue 接口创建 InferValue
    }

}
