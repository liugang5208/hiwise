package com.sky.hiwise.design.lesson.collection;

import com.sky.hiwise.design.lesson.basic.TestEnum;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class MapTest {

    /**
     *  将键映射到值的对象;一个映射不能包含重复的键;每个键最多只能映射到一个值
     *  Map<K,V>，一次添加一对元素（又称键值对）。K是键，V是值。而Collection一次添加一个元素。Map集合也称为双列集合，Collection集合也称为单列集合。其实Map集合中存储的就是键值对，且保证键（K）的唯一性。
     Map常用的子类：
     1、Hashtable:内部结构是哈希表，是同步的。不支持null作为键和值。
     2、HashMap:内部结构是哈希表，不是同步的，支持null作为键和值。
     3、TreeMap:内部结构是二叉树，不是同步的，支持null作为键和值。
     1.2 Map与Collection的区别
     1.Map 存储的是键值对形式的元素，键唯一，值可以重复。
     2.Collection 存储的是单列元素，子接口Set元素唯一，子接口List元素可重复。
     3.Map集合的数据结构值针对键有效，跟值无关，Collection集合的数据结构是针对元素有效
     ---Map 键唯一
     |------HashMap
     基于哈希表的 Map 接口的实现。此实现提供所有可选的映射操作，并允许使用 null 值和 null 键。（除了非同步和允许使用 null 之外，HashMap 类与 Hashtable 大致相同。）此类不保证映射的顺序，特别是它不保证该顺序恒久不变。 此实现不是同步的。
        |------LinkedHashMap
     Map接口的哈希表和链接列表实现，具有可预知的迭代顺序。此实现与 HashMap 的不同之处在于，后者维护着一个运行于所有条目的双重链接列表。此链接列表定义了迭代顺序，该迭代顺序通常就是将键插入到映射中的顺序（插入顺序）。此实现不是同步的
     |------WeakHashMap
     以弱键 实现的基于哈希表的 Map。在 WeakHashMap 中，当某个键不再正常使用时，将自动移除其条目。更精确地说，对于一个给定的键，其映射的存在并不阻止垃圾回收器对该键的丢弃，这就使该键成为可终止的，被终止，然后被回收。丢弃某个键时，其条目从映射中有效地移除，null 值和 null 键都被支持。
     |------Hashtable
     此类实现一个哈希表，该哈希表将键映射到相应的值。任何非 null 对象都可以用作键或值。        Hashtable 是同步的
     |------TreeMap
     基于红黑树（Red-Black tree）的 NavigableMap 实现。该映射根据其键的自然顺序进行排序，或者根据创建映射时提供的 Comparator 进行排序，具体取决于使用的构造方法。 此实现不是同步的
     大致包含如下功能：

     插入（put、putAll()）、删除（remove()）
     获取（entrySet()、get()、keySet()、size()、values()）
     判断（containsKey()、containsValue()、equals()、isEmpty()）、清除（clear()）
     替换（replace()，replace(K key, V oldValue, V newValue) jdk1.8之后，后面示例会讲到它们）
     void clear()
     从此映射中移除所有映射关系（可选操作）。
     boolean containsKey(Object key)
     如果此映射包含指定键的映射关系，则返回 true。
     boolean containsValue(Object value)
     如果此映射将一个或多个键映射到指定值，则返回 true。
     Set<Map.Entry<K,V>> entrySet()
     返回此映射中包含的映射关系的 Set 视图。
     boolean equals(Object o)
     比较指定的对象与此映射是否相等。
     V get(Object key)
     返回指定键所映射的值；如果此映射不包含该键的映射关系，则返回 null。
     int hashCode()
     返回此映射的哈希码值。
     boolean isEmpty()
     如果此映射未包含键-值映射关系，则返回 true。
     Set<K> keySet()
     返回此映射中包含的键的 Set 视图。
     V put(K key, V value)
     将指定的值与此映射中的指定键关联（可选操作）。
     void putAll(Map<? extends K,? extends V> m)
     从指定映射中将所有映射关系复制到此映射中（可选操作）。
     V remove(Object key)
     如果存在一个键的映射关系，则将其从此映射中移除（可选操作）。
     int size()
     返回此映射中的键-值映射关系数。
     Collection<V> values()
     返回此映射中包含的值的 Collection 视图。
     */

    public static void main(String[] args) {
        //testHashMap();
        //testLinkedHashMap();
        //testTreeMap();
        testWeakHashMap();
        testIdentityHashMap();
        testEnumMap();

    }

    /**
     * 3.1、 Map集合遍历的常见方式
     方式1、根据键获取值(key -> value)
     1.获取所有键的集合
     2.遍历键的集合，获取到每一个键
     3.根据键找值
     方式2、根据键值对对象获取键和值( entrySet -> key,value)
     1.获取所有键值对对象的集合
     2.遍历键值对对象的集合，获取到每一个键值对对象
     3.根据键值对对象找键和值

     因为如果键是第一次存储，就直接存储元素，返回null
     如果键不是第一次存在，就用值把以前的值替换掉，返回以前的值
     */
    public static void testHashMap() {

        Map<String, String> map=new HashMap<String, String>();
        map.put("000", "qqq");
        map.put("003", "rrr");
        map.put("001", "www");
        map.put("002", "eee");
        map.put("004", "sss");

        map.forEach((key, value)->System.out.println("key="+key+" value="+value));

        System.out.println(map);  // 直接打印输出键值对

        //Collections.emptyMap();

        // 遍历1 ： 通过键值对对象entrySet获取键与值
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key="+key+" value="+value);
        }
        System.out.println("-------------------");

        // 遍历2：通过键keySet获取值
        Set<String> keySet = map.keySet(); // 得到键的集合
        for (String key : keySet) {
            String value = map.get(key);
            System.out.println("key="+key+" value="+value);
        }
        System.out.println("-------------------");

        // 获取Map值的集合
        Collection<String> values = map.values();
        System.out.println(values);

        // 判断是否存在键和值
        System.out.println("containsKey="+map.containsKey("001"));
        System.out.println("containsKey="+map.containsValue("eee"));

        // 向Map集合添加元素时，若键存在，则返回之前与键对应的值
        String put = map.put("000", "aaa");
        System.out.println("put="+put); // output： qqq

        //  default V replace(K key, V value)
        //  替换功能，将旧值替换成新值，并返回旧值（若有的话）
        String replace = map.replace("003", "666");
        System.out.println("replace="+replace);
        System.out.println(map);

        // default boolean replace(K key, V oldValue, V newValue
        // 只有当键key存在，并且oldValue与newValue相等时，旧的值才会被替换成新的值，并且返回true
        boolean success = map.replace("004", "sss", "lll");
        System.out.println("replace2="+success); // output : true
    }

    /**
     * Java8除了为map增加了remove(Object key, Object value)默认方法之外，还增加了如下方法：
     Object compute(Object key, BiFunction remappingFunction)：该方法使用remappingFunction根据原key-value对计算一个新value。只要新value不为null，就使用新value覆盖原value；如果原value不为null，但新value为null，则删除原key-value对；如果原value、新value同时为null，那么该方法不改变任何key-value对，直接返回null
     Object computeIfAbsent(Object key, Function mappingFunction)：如果传给该方法的key参数在Map中对应的value为null，则使用mappingFunction根据key计算一个新的结果，如果计算结果不为null，则用计算结果覆盖原有value。如果原Map原来不包含该key，那么该方法可能会添加一组key-value对
     Object computeIfPresent(Object key ,BiFunction remappingFunction)：如果传给该方法的key参数在Map中对应的value不为null，该方法将使用remappingFunction根据原key、value计算一个新的结果，如果计算结果不为null，则使用该结果覆盖原来的value，如果计算的结果为null，则删除原key-value对
     void forEach(BiConsumer action)：该方法是Java 8为Map新增的一个遍历key-value对的方法
     Object getOrDefault(Object key, V defaultValue)：获取指定key对应的value。如果该key不存在则返回defaultValue
     Object merge(Object key, Object value, BiFunction remappingFunction)：该方法会先根据Key参数获取该Map中对应的value。如果获得value为null，则直接用传入的value覆盖原有的value(在这中情况下，可能要添加一组key-value对)；如果获取的value不为null,则使用remappingFunction 函数根据原value、新value计算一新的结果，并用得到的结果去覆盖原有的value
     Object putIfAbsent(Object key, Object value)：该方法会自动检测指定key对应的value是否为null，如果该key对应的value为null，该方法将会用新value代替原来的null值
     Object replace(Object key, Object value)：将Map中指定key对应的value替换成新的value。与传统的put方法不同的是，该方法不可能添加新的key-value对。如果尝试替换的key在原Map中不存在，该方法不会添加key-value对，而是返回null
     boolean replace(K key, V oldValue, V newValue)
     将Map中指定key-value对的原value提换成新value。如果在Map中找到指定的key-value对，则执行替换并返回true,否额返回false
     replaceAll(Bifunction function)：该方法使用Bifunction 对原key-value对执行计算，并将计算结果作为key-value对的value的值
     */

    /**
     * Map 接口的哈希表和链接列表实现，具有可预知的迭代顺序。
     - 由哈希表保证键的唯一性，不可重复
     - 由链表保证键盘的有序(存储和取出的顺序一致)
     HashSet有一个子类是LinkedHashSet,HashMap也有一个LinkedHashMap子类;LinkedHashMap也使用双向链表来维护key-value对的次序；该链表负责维护key的迭代顺序，迭代顺序与key-value的插入顺序一致
     LinkedHashMap可以避免对HashMap、Hashtable里的key-value对进行排序（只要插入key-value对时保持顺序即可）。同时又可避免使用TreeMap所增加的成本
     LinkedHashMap需要维护元素的插入顺序，因此性能略低于HashMap的性能，但在迭代访问Map里的全部元素时将有很好的性能，因为它以链表来维护内部顺序
     */
    public static void testLinkedHashMap() {
        Map<String, String> map=new LinkedHashMap<>();
        map.put("000", "qqq");
        map.put("003", "rrr");
        map.put("001", "www");
        map.put("002", "eee");
        map.put("004", "sss");
        map.forEach((key, value)->System.out.println("key="+key+" value="+value));
    }

    /**
     * SortedMap接口和TreeMap实现类
     Map接口派生了一个SortedMap子接口，TreeMap为其实现类。类似TreeSet排序，TreeMap也是基于红黑树对TreeMap中所有key进行排序，从而保证TreeMap中所有key-value对处于有序状态。TreeMap两种排序方法：
     ● 自然排序：TreeMap的所有key必须实现Comparable接口，而且所有key应该是同一个类的对象，否则将会抛出ClassCastExcepiton异常
     ● 定制排序：创建TreeMap时，传入一个Comparator对象，该对象负责对TreeＭap中所有key进行排序。采用定制排序时不要求Map的key实现Comparable接口
     如果使用自定义的类作为TreeMap的key，应重新该类的equals方法和compareTo方法时应有一致的返回结果：即两个key通过equals方法比较返回true时，它们通过compareTo方法比较应该返回0。
     */
    public static void testTreeMap() {
        TreeMap tm = new TreeMap();
        tm.put(3 , "轻量级Java EE企业应用实战");
        tm.put(-5 , "疯狂Java讲义");
        tm.put(9 , "疯狂Android讲义");
        System.out.println(tm);
        // 返回该TreeMap的第一个Entry对象
        System.out.println(tm.firstEntry());
        // 返回该TreeMap的最后一个key值
        System.out.println(tm.lastKey());
        // 返回该TreeMap的比new 2大的最小key值。
        System.out.println(tm.higherKey(2));
        // 返回该TreeMap的比new 2小的最大的key-value对。
        System.out.println(tm.lowerEntry(2));
        // 返回该TreeMap的子TreeMap
        System.out.println(tm.subMap(-1 , 4));
    }

    /**
     * HashMap中的key保存的是实际对象的强引用，这意味着只要该HashMap对象不被销毁，该HashMap的所有key所引用的对象就不会被垃圾回收，HashMap也不会自动删除这些key所对应的key-value对
     WeakHashMap中的key保存的是实际对象的弱引用，这意味着只要该WeakHashMap对象没被其他强对象引用变量引用，
     则这些key所引用的对象可能被垃圾回收，就有可能会被垃圾回收机制回收对应的Key-value，WeakHashMap也可能自动删除这些key所对应的key-value对
     */
    public static void testWeakHashMap() {
        WeakHashMap whm = new WeakHashMap();
        // 将WeakHashMap中添加三个key-value对，
        // 三个key都是匿名字符串对象（没有其他引用）
        whm.put(new String("南特") , new String("Nantes"));
        whm.put(new String("巴黎") , new String("Paris"));
        whm.put(new String("波尔多") , new String("Bordeaux"));
        //将 WeakHashMap中添加一个key-value对，
        // 该key是一个系统缓存的字符串对象。
        whm.put("马赛" , new String("Marseille"));    // ①
        // 输出whm对象，将看到4个key-value对。
        System.out.println(whm);
        // 通知系统立即进行垃圾回收
        System.gc();
        System.runFinalization();
        // 通常情况下，将只看到一个key-value对。
        System.out.println(whm);
    }

    /**
     * IdentityHashMap的实现机制与HashMap基本相似，
     * 在IdentityHashMap中，判断两个key是否相等，是通过严格相等即（key1==key2）来判断的，
     * 而HashMap是通过equals()方法和hashCode()这两个方法来判断key是否相等的。
     * IdentityHashMap允许使用null作为key和value，不保证key-value对之间的顺序，
     * 不保证它们的顺序随时间的推移保持不变
     */
    public static void testIdentityHashMap() {
        IdentityHashMap ihm = new IdentityHashMap();
        // 下面两行代码将会向IdentityHashMap对象中添加两个key-value对
        ihm.put(new String("勒布朗詹姆斯") , 23);
        ihm.put(new String("勒布朗詹姆斯") , 6);
        // 下面两行代码只会向IdentityHashMap对象中添加一个key-value对
        ihm.put("科比布莱恩特" , 8);
        ihm.put("科比布莱恩特" , 24);
        System.out.println(ihm);
    }

    /**
     * EnumMap是一个与枚举类一起使用的Map实现，EnumMap中的所有key都必须是单个枚举类的枚举值。创建EnumMap时必须显式或隐式指定它对应的枚举类。EnumMap具有如下特征：
     EnumMap在内部以数组形式保存，所以这种实现形式非常紧凑、高效
     EunmMap根据key的自然顺序（即枚举值在枚举类中的定义顺序）来维护key-value对的顺序。当程序通过keySet()、entrySet()、values()等方法遍历EnumMap时可以看到这种顺序
     EnumMap不允许使用null作为key，但允许使用null作为value。如果试图使用null作为key时将抛出NullpointerException。如果只是查询是否包含值为null的key，或只是删除值为null的key，都不会抛出异常
     */
    public static void testEnumMap() {
        // 创建EnumMap对象，该EnumMap的所有key都是Season枚举类的枚举值
        EnumMap enumMap = new EnumMap(TestEnum.class);
        enumMap.put(TestEnum.WAIT_CALL, "俄克拉荷马雷霆");
        enumMap.put(TestEnum.WAIT_SEE, "克利夫兰骑士");
        System.out.println(enumMap);
    }

    /**
     * 3.24 一个HashMap面试题
     需求如下：

     已知一个HashMap<Integer，Person>集合， Person有name（String）和age（int）属性。
     请写一个方法实现对HashMap的排序功能。该方法接收HashMap<Integer，Person>为形参，返回类型为HashMap<Integer，Person>，
     要求对HashMap中的Person的age升序进行排序。排序时key=value键值对不得拆散。
     1
     2
     分析：
     HashMap本身是不保证元素的顺序不变的，要对其排序可使用LinkedHashMap，它是有序的并且还是HashMap的子类，我们可以使用它来完成排序的目的。
     最后返回它的实例即可满足要求 并且还符合多态的编程思想
     */

}
