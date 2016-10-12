package cn.zkw.common.rabbitmq.common;

/**
 * Created by zhu_kewei on 16-9-18.
 */
public class SysConfig {
    private String storePath;
    private String tempPath;

    private String mqExchange;
    private String mqResultQueueName;

    private String bSaveTemp;

    private Integer textMatchScore;


    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

    public String getMqExchange() {
        return mqExchange;
    }

    public void setMqExchange(String mqExchange) {
        this.mqExchange = mqExchange;
    }

    public String getMqResultQueueName() {
        return mqResultQueueName;
    }

    public void setMqResultQueueName(String mqResultQueueName) {
        this.mqResultQueueName = mqResultQueueName;
    }

    public String getbSaveTemp() {
        return bSaveTemp;
    }

    public void setbSaveTemp(String bSaveTemp) {
        this.bSaveTemp = bSaveTemp;
    }

    public Integer getTextMatchScore() {
        return textMatchScore;
    }

    public void setTextMatchScore(Integer textMatchScore) {
        this.textMatchScore = textMatchScore;
    }
}
