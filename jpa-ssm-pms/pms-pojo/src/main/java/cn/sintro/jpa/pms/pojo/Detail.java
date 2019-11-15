package cn.sintro.jpa.pms.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: jpa-ssm-pms
 * @description:
 * @author: zhang
 * @create: 2019-10-19 08:56
 **/
@Entity
@Table(name = "pms_detail")
@GenericGenerator(name = "uuid", strategy = "uuid")
public class Detail implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @Column(length = 32)
    private String uuid;

    @Column(name = "detail_model")
    private String model;

    @Column(name = "function")
    private String function;

    @Column(name = "detail_desc")
    private String describe;

    @Column(name = "render_name")
    private String renderName;

    @Column(name = "level")
    private String level;

    @Column(name = "status")
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;

    @Transient
    private String createTimeStr;

    @Transient
    private String updateTimeStr;

    // 多对一关系映射：多个联系人对应客户
    @ManyToOne(targetEntity = Item.class)
    @JoinColumn(name = "item_uuid", referencedColumnName = "uuid")
    // 外键设置时防止死循环
    @JsonIgnore
    private Item item;



    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getRenderName() {
        return renderName;
    }

    public void setRenderName(String renderName) {
        this.renderName = renderName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTimeStr() {
        String format = "暂无数据";
        if (createTime != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format = sdf.format(createTime);
        }
        return format;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getUpdateTimeStr() {
        String format = "暂无数据";
        if (updateTime != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format = sdf.format(updateTime);
        }
        return format;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "uuid='" + uuid + '\'' +
                ", model='" + model + '\'' +
                ", function='" + function + '\'' +
                ", describe='" + describe + '\'' +
                ", renderName='" + renderName + '\'' +
                ", level='" + level + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", updateTimeStr='" + updateTimeStr + '\'' +
                '}';
    }
}
