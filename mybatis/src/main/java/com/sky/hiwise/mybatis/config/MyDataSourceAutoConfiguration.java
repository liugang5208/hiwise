//package com.sky.hiwise.mybatis.config;
//
//import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.TransactionDefinition;
//import org.springframework.transaction.interceptor.*;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class MyDataSourceAutoConfiguration {
//
//    /**
//     * 配置事务拦截方式
//     *
//     * @return org.springframework.transaction.interceptor.TransactionAttributeSource
//     **/
//    @Bean("txSource")
//    public TransactionAttributeSource transactionAttributeSource() {
//        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
//        /* 只读事务，不做更新操作 */
//        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
//        readOnlyTx.setReadOnly(true);
//        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
//
//        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED,
//                Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
//        requiredTx.setTimeout(5);
//
//        RuleBasedTransactionAttribute batchRequiredTx = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRES_NEW,
//                Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
//        batchRequiredTx.setTimeout(1800);
//
//        Map<String, TransactionAttribute> txMap = new HashMap<>(9);
//        txMap.put("batch*", batchRequiredTx);
//        txMap.put("*Batch*", batchRequiredTx);
//        txMap.put("add*", requiredTx);
//        txMap.put("save*", requiredTx);
//        txMap.put("insert*", requiredTx);
//        txMap.put("update*", requiredTx);
//        txMap.put("delete*", requiredTx);
//        txMap.put("change*", requiredTx);
//        txMap.put("remove*", requiredTx);
//        txMap.put("disable*", requiredTx);
//        txMap.put("*", readOnlyTx);
//        source.setNameMap(txMap);
//        return source;
//    }
//
//    /**
//     * 切面拦截规则 参数会自动从容器中注入
//     *
//     * @param txInterceptor
//     * @return org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor
//     **/
//    @Bean
//    public AspectJExpressionPointcutAdvisor pointcutAdvisor(TransactionInterceptor txInterceptor) {
//        AspectJExpressionPointcutAdvisor pointcutAdvisor = new AspectJExpressionPointcutAdvisor();
//        pointcutAdvisor.setAdvice(txInterceptor);
//        pointcutAdvisor.setExpression("(execution(public * com.sky.hiwise..service..*ServiceImpl.*(..)))");
//        return pointcutAdvisor;
//    }
//
//    /**
//     * 事务拦截器
//     *
//     * @param tx
//     * @return org.springframework.transaction.interceptor.TransactionInterceptor
//     **/
//    @Bean("txInterceptor")
//    public TransactionInterceptor getTransactionInterceptor(PlatformTransactionManager tx) {
//        return new TransactionInterceptor(tx, transactionAttributeSource());
//    }
//}
