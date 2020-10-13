package com.sky.hiwise.youli.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@TableName(value = "youli")
public class YouLi {

    public static Map<String, String> fieldMap = new HashMap<>();
    static {
        fieldMap.put("项目名称", "projectName");
        fieldMap.put("项目编号", "projectId");
        fieldMap.put("借款金额(元)", "loanAmount");
        fieldMap.put("借款期限", "loanTerm");
        fieldMap.put("年化利率", "rate");
        fieldMap.put("预计起息日", "startTime");
        fieldMap.put("还款方式", "repaymentMethod");
        fieldMap.put("募集开始时间", "raiseTime");
        fieldMap.put("项目状态", "projectStatus");

        fieldMap.put("姓名", "username");
        fieldMap.put("证件类型", "idType");
        fieldMap.put("证件号码", "idNo");
        fieldMap.put("工作性质", "work");
        fieldMap.put("在本平台逾期次数", "overdueCount");
        fieldMap.put("在本平台逾期总金额(元)", "overdueAmount");
        fieldMap.put("借款人收入及负债情况", "income");
        fieldMap.put("其他借款信息", "otherLoan");
        fieldMap.put("借款人征信报告情况", "creditReport");

    }



    private static final long serialVersionUID = -1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Integer projectId;

    private String projectName;

    private String raiseTime;

    private String startTime;

    private Double loanAmount;

    private String loanTerm;

    private Integer repaymentMethod;

    private Integer projectStatus;

    private String rate;

    private String username;

    private Integer idType;

    private String idNo;

    private String work;

    private String otherLoan;

    private String creditReport;

    private Integer overdueCount;

    private Double overdueAmount;

    private String income;



    // `project_name` varchar(45) NOT NULL,
    //  `project_id` int(11) NOT NULL,
    //  `raise_time` datetime NOT NULL,
    //  `start_time` datetime NOT NULL,
    //  `loan_amount` decimal(9,2) NOT NULL,
    //  `loan_term` varchar(45) NOT NULL,
    //  `repayment_method` varchar(45) DEFAULT NULL,
    //  `project_status` varchar(45) DEFAULT NULL,
    //  `rate` varchar(45) DEFAULT NULL,
    //  `username` varchar(45) DEFAULT NULL,
    //  `id_type` varchar(45) DEFAULT NULL,
    //  `id_no` varchar(45) DEFAULT NULL,
    //  `work` varchar(45) DEFAULT NULL,
    //  `other_loan` varchar(45) DEFAULT NULL,
    //  `credit_report` varchar(45) DEFAULT NULL,
    //  `overdue_count` int(11) DEFAULT NULL,
    //  `overdue_amount` decimal(9,2) DEFAULT NULL,
    //  `income` varchar(145) DEFAULT NULL,
}
