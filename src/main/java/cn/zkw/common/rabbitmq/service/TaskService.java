package cn.zkw.common.rabbitmq.service;

/**
 * Created with IntelliJ IDEA.
 * User: zkw
 * Date: 1/4/15
 * Time: 1:30 下午
 * To change this template use File | Settings | File Templates.
 */
public interface TaskService {
    void query(String content);
    void ingest(String content);
}
