package com.sky.hiwise.study.aop.chain;

public class Client {

    static class HandlerA extends Handler {

        @Override
        protected void handleProcess() {
            System.out.println("handle by a");
        }
    }

    static class HandlerB extends Handler {

        @Override
        protected void handleProcess() {
            System.out.println("handle by b");
        }
    }

    static class HandlerC extends Handler {

        @Override
        protected void handleProcess() {
            System.out.println("handle by c");
        }
    }
    //缺点：各个handler之间的顺序需要显式指定
    //handlerA.setSuccessor(handlerB);
    //handlerB.setSuccessor(handlerC);
    public static void main(String[] args) {
        HandlerA handlerA = new HandlerA();
        HandlerB handlerB = new HandlerB();
        HandlerC handlerC = new HandlerC();

        handlerA.setSuccessor(handlerB);
        handlerB.setSuccessor(handlerC);

        handlerA.execute();
    }
}
