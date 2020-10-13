package com.sky.hiwise.youli.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.hiwise.youli.constants.YouLiConstants;
import com.sky.hiwise.youli.domain.YouLi;
import com.sky.hiwise.youli.mapper.YouLiMapper;
import com.sky.hiwise.youli.task.AtomicId;
import com.sky.hiwise.youli.util.FieldFormat;
import com.sky.hiwise.youli.util.HttpClientResult;
import com.sky.hiwise.youli.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class YouLiServiceImpl extends ServiceImpl implements YouLiService {

    @Autowired
    YouLiMapper youLiMapper;

    @Override
    public void parseHtml(Integer projectId) {
        try {
            String url = AtomicId.URL_PRIFIX + projectId;
            HttpClientResult result = HttpClientUtils.doGet(url);
            YouLi youli = new YouLi();
            Document document = Jsoup.parse(result.getContent());
            Element baseInfo = document.getElementById("base-info");

            if (baseInfo == null) {
                System.out.println(result);
                log.info("get data fail : {}", projectId);
                return;
            }
            format(youli, baseInfo);
            Element governInfo = document.getElementById("govern-info");
            format(youli, governInfo);
            if (youli.getProjectId() == null) {
                log.info("id : {}, no data", projectId);
                return;
            }
            youLiMapper.insert(youli);
        } catch (Exception e) {
            log.error("parseHtml error, id: {}, ", projectId, e);
        }
    }

    private void format(YouLi youli, Element element) throws Exception {
        // 使用选择器选择该table内所有的<tr> <tr/>
        Elements trs = element.select("tr");
        //遍历该表格内的所有的<tr> <tr/>
        for (int i = 0; i < trs.size(); ++i) {
            // 获取一个tr
            Element tr = trs.get(i);
            // 获取该行的所有td节点
            Elements tds = tr.select("td");
            if (YouLi.fieldMap.containsKey(tds.get(0).text())) {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(YouLi.fieldMap.get(tds.get(0).text()), youli.getClass());
                // 获得set方法
                Method setMethod = propertyDescriptor.getWriteMethod();
                String fieldValue = tds.get(1).text();
                // 调用指定对象set方法
                if (tds.get(0).text().equals("借款期限") || tds.get(0).text().equals("年化利率")) {
                    fieldValue = tds.get(1).text().substring(0, tds.get(1).text().length() - 1);
                } else if (tds.get(0).text().equals("证件类型")) {
                    YouLiConstants.IDType type = YouLiConstants.IDType.get(tds.get(1).text());
                    if (type == null) {
                        throw new RuntimeException(tds.get(1).text() + " miss match");
                    }
                    fieldValue = type.getValue().toString();
                } else if (tds.get(0).text().equals("还款方式")) {
                    YouLiConstants.Repayment repayment = YouLiConstants.Repayment.get(tds.get(1).text());
                    if (repayment == null) {
                        throw new RuntimeException(tds.get(1).text() + " miss match");
                    }
                    fieldValue = repayment.getValue().toString();
                } else if (tds.get(0).text().equals("项目状态")) {
                    YouLiConstants.Status status = YouLiConstants.Status.get(tds.get(1).text());
                    if (status == null) {
                        throw new RuntimeException(tds.get(1).text() + " miss match");
                    }
                    fieldValue = status.getValue().toString();
                }
                setMethod.invoke(youli, FieldFormat.getValue(fieldValue, setMethod.getParameterTypes()[0]));
            }
        }
    }


}
