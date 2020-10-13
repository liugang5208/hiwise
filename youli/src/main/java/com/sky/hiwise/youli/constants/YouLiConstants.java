package com.sky.hiwise.youli.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class YouLiConstants {

    public enum IDType {
        /**
         * 绑定原因
         */
        ID("身份证", 1),
        ;

        /**
         * 名称
         */
        private String name;
        /**
         * 值
         */
        private Integer value;


        /**
         * Value => 枚举映射
         */
        private static final Map<String, IDType> VALUE_MAP;

        /**
         * 初始化映射
         */
        static {
            Map<String, IDType> fakeMap = new HashMap<>();
            for (IDType item : values()) {
                fakeMap.put(item.getName(), item);
            }
            VALUE_MAP = Collections.unmodifiableMap(fakeMap);
        }

        /**
         * 客户绑定来源
         *
         * @param name
         * @param value
         */
        IDType(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }

        /**
         * 是否合法绑定来源
         *
         * @param name
         * @return
         */
        public static IDType get(String name) {
            if (!VALUE_MAP.containsKey(name)) {
                return null;
            }
            return VALUE_MAP.get(name);
        }
    }

    public enum Status {
        /**
         * 绑定原因
         */
        START("募集中", 1),
        IN_PROGRESS("还款中", 2),
        OVER("正常还款已结清", 3),
        ;

        /**
         * 名称
         */
        private String name;
        /**
         * 值
         */
        private Integer value;


        /**
         * Value => 枚举映射
         */
        private static final Map<String, Status> VALUE_MAP;

        /**
         * 初始化映射
         */
        static {
            Map<String, Status> fakeMap = new HashMap<>();
            for (Status item : values()) {
                fakeMap.put(item.getName(), item);
            }
            VALUE_MAP = Collections.unmodifiableMap(fakeMap);
        }

        /**
         * 客户绑定来源
         *
         * @param name
         * @param value
         */
        Status(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }

        /**
         * 是否合法绑定来源
         *
         * @param name
         * @return
         */
        public static Status get(String name) {
            if (!VALUE_MAP.containsKey(name)) {
                return null;
            }
            return VALUE_MAP.get(name);
        }
    }

    public enum Repayment {
        /**
         * 绑定原因
         */
        MONTHLY("按月等额本息还款", 1),
        TWO("按月付息到期还本", 2),
        ;

        /**
         * 名称
         */
        private String name;
        /**
         * 值
         */
        private Integer value;


        /**
         * Value => 枚举映射
         */
        private static final Map<String, Repayment> VALUE_MAP;

        /**
         * 初始化映射
         */
        static {
            Map<String, Repayment> fakeMap = new HashMap<>();
            for (Repayment item : values()) {
                fakeMap.put(item.getName(), item);
            }
            VALUE_MAP = Collections.unmodifiableMap(fakeMap);
        }

        /**
         * 客户绑定来源
         *
         * @param name
         * @param value
         */
        Repayment(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }

        /**
         * 是否合法绑定来源
         *
         * @param name
         * @return
         */
        public static Repayment get(String name) {
            if (!VALUE_MAP.containsKey(name)) {
                return null;
            }
            return VALUE_MAP.get(name);
        }
    }
}
