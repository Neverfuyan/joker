package com.wj.springcloud.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;


/**
 * @author ：actor
 * @date ：Created in 2022/1/19 10:21
 * @description：
 * @modified By：
 * @version: $
 */
public class GeneralExcelListener<T> extends AnalysisEventListener<T> {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(GeneralExcelListener.class);

    ArrayList<T> list = new ArrayList<T>();


    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        list.add(t);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        logger.info(JSON.toJSONString(list));
    }


    public ArrayList<T> getDatas(){
        return list;
    }
}
