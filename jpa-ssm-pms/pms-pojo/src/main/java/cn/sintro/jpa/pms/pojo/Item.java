package cn.sintro.jpa.pms.pojo;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: jpa-ssm-pms
 * @description:
 * @author: zhang
 * @create: 2019-10-19 08:56
 **/

@Entity
@Table(name = "pms_item")
@GenericGenerator(name = "uuid", strategy = "uuid")
public class Item implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")  // 自增
    @Column(length = 32)
    private String uuid;

    @Column(name = "item_name")
    private String name;

    @Column(name = "number")
    private Integer number;

    @Column(name = "problem")
    private String problem;

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

    @OneToMany(mappedBy = "item")
    private Set<Detail> detail = new HashSet<Detail>();



    public Set<Detail> getDetail() {
        return detail;
    }

    public void setDetail(Set<Detail> detail) {
        this.detail = detail;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
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
        return "Item{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", problem='" + problem + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", updateTimeStr='" + updateTimeStr + '\'' +
                '}';
    }
}
