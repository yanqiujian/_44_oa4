package com.qianfeng.oa.cache;

import java.util.List;
import java.util.Map;

public interface IOACache {

    void saveHash(String key,Map<String,Object> value);

    Map<Object,Object> queryHashByKey(String key);

    void saveList(String key,String processId);

    List<String> getAllProcessIdByKey(String key,int startIndex,int endIndex);
}
