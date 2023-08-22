# Flink流处理简介
## 1. Flink简介
### 1.1 Flink是什么？
Apache Flink is a framework and distributed processing engine for stateful computations over unbounded and bounded data streams.
![flink stream](../img/flink/bounded-unbounded.png)
Apache Flink 是一个计算框架和分布式处理引擎，用于对无界和有界数据流进行有状态计算。其针对数据流的分布式计算提供了数据分布、数据通信以及容错机制等功能。基于流执行引擎，Flink 提供了诸多更高抽象层的 API 以便用户编写分布式任务：
1) DataSet API：对静态数据进行批处理操作，将静态数据抽象成分布式的数据集，用户可以方便地使用 Flink 提供的各种操作符对分布式数据集进行处理，支持 Java、Scala 和 Python。
2) DataStream API：对数据流进行流处理操作，将流式的数据抽象成分布式的数据流，用户可以方便地对分布式数据流进行各种操作，支持 Java 和 Scala。
3) Table API：对结构化数据进行查询操作，将结构化数据抽象成关系表，并通过类 SQL 的 DSL 对关系表进行各种查询操作，支持 Java 和 Scala。
此外，Flink 还针对特定的应用领域提供了领域库，例如：
1) Flink ML：Flink 的机器学习库，提供了机器学习 Pipelines API 并实现了多种机器学习算法
2) Gelly：Flink 的图计算库，提供了图计算的相关 API 及多种图计算算法实现
### 1.2 为什么选择Flink？
Flink作为新一代的大数据项目，除了尽量匹配其他大数据项目所提供的能力之外，还拥有一些不错的特性，列举如下：
* 既支持批处理也支持流计算，拥有一个 streaming-first（流优先）的运行时（runtime）引擎；
* 为Java以及Scala开发者提供优雅而流畅的API；
* 可进行大规模扩展同时兼顾高吞吐和低延迟；
* 基于”Dataflow Model“（数据流模型）在DataStream API中支持事件时间、无序处理、事件延迟等能力并保证结果的正确性；
* 灵活且支持多种时间语义（事件时间、处理时间、摄入时间）的窗口；
* 支持状态以及容错性（fault-tolerance）并提供恰好一次（exactly-once）的一致性语义保证；
* 程序更新、扩容后不丢失状态的保存点机制；
* 流计算中自然的反压机制；
* 提供关系及SQL查询、图计算、机器学习以及复杂事件处理等领域特定库；
* 内置对迭代的支持；
* 自实现的具备高效性和鲁棒性的显式内存管理机制；
* 提供对Hadoop以及Storm的兼容性；
* 提供与YARN、HDFS、HBase以及Hadoop生态系统中其他部件的集成能力；

以上这些特性在保证Flink满足通用目的的数据分析与处理的同时也使得其成为某些特殊领域与场景（比如要求极低延迟、较高准确性的实时流计算）的可靠选择。
总结：低延迟、高吞吐、结果的准确性和良好的容错性。
### 1.3 Flink应用场景
* **1.Data Pipeline**
![data pipeline](../img/flink/usecases-datapipelines.png)
Data Pipeline 的核心场景类似于数据搬运并在搬运的过程中进行部分数据清洗或者处理，而整个业务架构图的左边是Periodic ETL，它提供了流式 ETL 或者实时 ETL，能够订阅消息队列的消息并进行处理，清洗完成后实时写入到下游的Database 或 File system 中。场景举例：
1) 实时数仓: 当下游要构建实时数仓时，上游则可能需要实时的 Stream ETL。这个过程会进行实时清洗或扩展数据，清洗完成后写入到下游的实时数仓的整个链路中，可保证数据查询的时效性，形成实时数据采集、实时数据处理以及下游的实时 Query。
2) 搜索引擎推荐: 搜索引擎这块以淘宝为例，当卖家上线新商品时，后台会实时产生消息流，该消息流经过 Flink 系统时会进行数据的处理、扩展。然后将处理及扩展后的数据生成实时索引，写入到搜索引擎中。这样当淘宝卖家上线新商品时，能在秒级或者分钟级实现搜索引擎的搜索。
* **2.Data Analytics**
![data analytics](../img/flink/usecases-analytics.png)
Data Analytics，如图，左边是 Batch Analytics，右边是 Streaming Analytics。Batch Analytics 就是传统意义上使用类似于Map Reduce、Hive、Spark Batch 等，对作业进行分析、处理、生成离线报表；Streaming Analytics 使用流式分析引擎如Storm、Flink 实时处理分析数据，应用较多的场景如实时大屏、实时报表。
* **3.Data Driven**
![data eventdrivenapps](../img/flink/usecases-eventdrivenapps.png)
从某种程度上来说，所有的实时的数据处理或者是流式数据处理都是属于 Data Driven，流计算本质上是 Data Driven 计算。应用较多的如风控系统，当风控系统需要处理各种各样复杂的规则时，Data Driven 就会把处理的规则和逻辑写入到Datastream 的 API 或者是 ProcessFunction 的 API 中，然后将逻辑抽象到整个 Flink 引擎，当外面的数据流或者是事件进入就会触发相应的规则，这就是 Data Driven 的原理。在触发某些规则后，Data Driven 会进行处理或者是进行预警，这些预警会发到下游产生业务通知，这是 Data Driven 的应用场景，Data Driven 在应用上更多应用于复杂事件的处理。
### 1.4 Flink基础概念
了解 Flink 应用开发需要先理解 Flink 的 Streams、State、Time 等基础处理语义以及 Flink 兼顾灵活性和方便性的多层次 API。

1、Streams：流，分为有限数据流与无限数据流，unbounded stream 是有始无终的数据流，即无限数据流；而 bounded stream 是限定大小的有始有终的数据集合，即有限数据流，二者的区别在于无限数据流的数据会随时间的推演而持续增

加，计算持续进行且不存在结束的状态，相对的有限数据流数据大小固定，计算最终会完成并处于结束的状态。

2、State，状态是计算过程中的数据信息，在容错恢复和 Checkpoint 中有重要的作用，流计算在本质上是 Incremental Processing，因此需要不断查询保持状态；另外，为了确保 Exactly-once 语义，需要数据能够写入到状态中；而持久化存储，能够保证在整个分布式系统运行失败或者挂掉的情况下做到 Exactly-once，这是状态的另外一个价值。

3、Time，分为 Event time、Ingestion time（进入 flink 的 source task 的时间）、Processing time，Flink 的无限数据流是一个持续的过程，时间是我们判断业务状态是否滞后，数据处理是否及时的重要依据。

4、API，API 通常分为三层，由上而下可分为 SQL / Table API、DataStream API、ProcessFunction 三层，API 的表达能力及业务抽象能力都非常强大，但越接近 SQL 层，表达能力会逐步减弱，抽象能力会增强，反之，ProcessFunction 层 API 的表达能力非常强，可以进行多种灵活方便的操作，但抽象能力也相对越小。
![api stack](../img/flink/api-stack.png)
## 2 Flink运行架构
### 2.1 Flink运行时组件
Flink由四部分组成

JobManager，作业管理器

TaskManager，任务管理器

Resource Manager，资源管理器

Dispacher，分发器

1、JobManager（作业管理器）

作用：控制一个应用程序执行的主进程，也就是说，每个应用程序都会被一个不同的 JobManager 所控制执行。

JobManager 会先接收到要执行的应用程序，这个应用程序会包括：作业图 （JobGraph）、逻辑数据流图（logical dataflow graph）和打包了所有的类、 库和其它资源的JAR包。

JobManager 会把JobGraph转换成一个物理层面的数据流图，这个图被叫做 “执行图”（ExecutionGraph），包含了所有可以并发执行的任务。

JobManager 会向资源管理器（ResourceManager）请求执行任务必要的资源， 也就是任务管理器（TaskManager）上的插槽（slot）。一旦它获取到了足够的资源，就会将执行图分发到真正运行它们的TaskManager上。而在运行过程中， JobManager会负责所有需要中央协调的操作，比如说检查点（checkpoints） 的协调。

2、任务管理器（TaskManager）

Flink中的工作进程。通常在Flink中会有多个TaskManager运行，每一 个TaskManager都包含了一定数量的插槽（slots）。插槽的数量限制 了TaskManager能够执行的任务数量

启动之后，TaskManager会向资源管理器注册它的插槽；收到资源管理器的指令后，TaskManager就会将一个或者多个插槽提供给 JobManager调用。JobManager就可以向插槽分配任务（tasks）来执行了。

在执行过程中，一个TaskManager可以跟其它运行同一应用程序的 TaskManager交换数据。

3、资源管理器（ResourceManager）

作用：负责管理任务管理器（TaskManager）的插槽（slot），TaskManger 插槽是Flink中定义的处理资源单元。

Flink为不同的环境和资源管理工具提供了不同资源管理器，比如YARN、 Mesos、K8s，以及standalone部署。

当JobManager申请插槽资源时，ResourceManager会将有空闲插槽 的TaskManager分配给JobManager。如果ResourceManager没有足 够的插槽来满足JobManager的请求，它还可以向资源提供平台发起会 话，以提供启动TaskManager进程的容器。

4、分发器（Dispatcher）

可以跨作业运行，它为应用提交提供了REST接口。

当一个应用被提交执行时，分发器就会启动并将应用移交给一个 JobManager。

Dispatcher也会启动一个Web UI，用来方便地展示和监控作业执行的信息。

Dispatcher在架构中可能并不是必需的，这取决于应用提交运行的方式。
### 2.2 任务提交流程
1、Flink 的各个组件是如何交互协作的，下图是从一个较为高层级的视角，来看应用中各组件的交互协作。如果部署的集群环境不同（例如 YARN，Mesos，Kubernetes，standalone 等），其中一些步骤可以被省略，或是有些组件会运行在同一个 JVM 进程中。
![task](../img/flink/task.png)
2、具体地，如果我们将 Flink 集群部署到 YARN 上，那么就会有如下的提交流程：
![task-submit](../img/flink/task-submit.png)
Flink 任务提交后，Client 向 HDFS 上传 Flink 的 Jar 包和配置，之后向 Yarn ResourceManager 提交任务，ResourceManager 分配 Container 资源并通知对应的NodeManager 启动 ApplicationMaster，ApplicationMaster 启动后加载 Flink 的 Jar 包和配置构建环境，然后启动 JobManager，之后 ApplicationMaster 向 ResourceManager申请资源启动 TaskManager ， ResourceManager 分 配 Container 资 源 后 ， 由ApplicationMaster 通 知 资 源 所 在 节 点 的 NodeManager 启 动 TaskManager ，NodeManager 加载 Flink 的 Jar 包和配置构建环境并启动 TaskManager，TaskManager启动后向 JobManager 发送心跳包，并等待 JobManager 向其分配任务。
### 2.3 任务调度
1、任务调度原理
![task-dispatch](../img/flink/task-dispatch.svg)
客户端不是运行时和程序执行的一部分，但它用于准备并发送dataflow(JobGraph)给 Master(JobManager)，然后，客户端断开连接或者维持连接以等待接收计算结果。

当 Flink集群启动后，首先会启动一个JobManger和一个或多个的TaskManager。由Client提交任务给JobManager，JobManager再调度任务到各个TaskManager 去执行，然后TaskManager将心跳和统计信息汇报给JobManager。TaskManager之间以流的形式进行数据的传输。上述三者均为独立的 JVM 进程。

Client 为提交Job的客户端，可以是运行在任何机器上（与JobManager 环境连通即可）。提交Job后，Client 可以结束进程（Streaming 的任务），也可以不结束并等待结果返回。

JobManager 主 要 负 责 调 度 Job 并 协 调 Task 做 checkpoint， 职 责 上 很 像Storm 的 Nimbus。从 Client 处接收到 Job 和 JAR 包等资源后，会生成优化后的执行计划，并以 Task 的单元调度到各个 TaskManager 去执行。

TaskManager 在启动的时候就设置好了槽位数（Slot），每个 slot 能启动一个Task，Task 为线程。从 JobManager 处接收需要部署的 Task，部署启动后，与自己的上游建立 Netty 连接，接收数据并处理。

2、并行度

Flink 程序的执行具有并行、分布式的特性。

在执行过程中，一个流（stream）包含一个或多个分区（stream partition），而每一个算子（operator）可以包含一个或多个子任务（operator subtask），这些子任务在不同的线程、不同的物理机或不同的容器中彼此互不依赖地执行。

一个特定算子的子任务（subtask）的个数被称之为其并行度（parallelism）。

一般情况下，一个流程序的并行度，可以认为就是其所有算子中最大的并行度。一个程序中，不同的算子可能具有不同的并行度。

![parallelism](../img/flink/parallelism.svg)

3、TaskManager 和 Slots

 Flink 中每一个 TaskManager 都是一个JVM进程，它可能会在独立的线程上执行一个或多个子任务，为了控制一个 TaskManager 能接收多少个 task， TaskManager 通过 task slot 来进行控制（一个 TaskManager 至少有一个 slot）
![tasks_slots](../img/flink/tasks_slots.svg)
默认情况下，Flink 允许子任务共享 slot，即使它们是不同任务的子任务。 这样的结果是，一个 slot 可以保存作业的整个管道。Task Slot 是静态的概念，是指 TaskManager 具有的并发执行能力
![slot_sharing](../img/flink/slot_sharing.svg)

附官方文档连接：https://nightlies.apache.org/flink/flink-docs-release-1.14/docs/concepts/flink-architecture/
 
## 3 Flink流处理API
在Flink中，应用程序由可由用户定义的运算符转换的流式数据流组成。这些数据流形成有向图，从一个或多个源开始，到一个或多个汇结束。

Flink流处理API的编程模型如下图：
![program_dataflow](../img/flink/program_dataflow.svg)

Flink流处理整体流程抽象如下图：
![api-process](../img/flink/api-process.png)

### 3.1 Environment


 
 
 