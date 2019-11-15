package cn.sintro.jpa.pms.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * @create: 2019-10-19 13:39
 **/
@Entity
@Table(name = "pms_role")
@GenericGenerator(name = "uuid", strategy = "uuid")
public class Role implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")  // 自增
    @Column(length = 32)
    private String uuid;

    @Column(name = "role_name")
    private String name;

    @Column(name = "role_desc")
    private String describe;

    @Column(name = "code")
    private String code;

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

    //配置多对多
    @ManyToMany(mappedBy = "roles")  //配置多表关系
    @JsonIgnore
    private Set<User> users = new HashSet<>();


    @ManyToMany(targetEntity = Permission.class,cascade = CascadeType.REFRESH)
    @JoinTable(name = "pms_role_permission",
            //joinColumns,当前对象在中间表中的外键
            joinColumns = {@JoinColumn(name = "pms_role_uuid",referencedColumnName = "uuid")},
            //inverseJoinColumns，对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "pms_permission_uuid",referencedColumnName = "uuid")}
    )
    private Set<Permission> permissions = new HashSet<>();




    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Role{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", code='" + code + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", updateTimeStr='" + updateTimeStr + '\'' +
                ", users=" + users +
                '}';
    }
}
