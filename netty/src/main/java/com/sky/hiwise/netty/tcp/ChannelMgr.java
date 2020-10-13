package com.sky.hiwise.netty.tcp;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

public class ChannelMgr {

    private static class SingletonChannelMgr {
        static final ChannelMgr instance = new ChannelMgr();
    }

    public static ChannelMgr getInstance() {
        return SingletonChannelMgr.instance;
    }

    private Map<String, Channel> channelMap;

    private ChannelMgr() {
        channelMap = new HashMap<>();
    }

    public Map<String, Channel> getChannelMap() {
        return channelMap;
    }

    public void addChannel(String id, Channel channel) {
        channelMap.put(id, channel);
    }
}
