package cn.sintro.jpa.pms.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
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
 * @create: 2019-10-19 15:15
 **/

@Entity
@Table(name = "pms_permission")
@GenericGenerator(name = "permission_uuid", strategy = "uuid")
@Proxy(lazy = false)
public class Permission implements Serializable {

    @Id
    @GeneratedValue(generator = "permission_uuid")
    @Column(length = 32)
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parent_permission_uuid",referencedColumnName = "uuid")
    private Permission parentPermission;

    @Column(name = "permission_name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;

    @Column(name = "generate_menu")
    private String generateMenu;//是否生成菜单，1：是 0：否

    @Column(name = "zindex")
    private Integer zindex;

    @ManyToMany(mappedBy = "permissions")  //配置多表关系
    @JsonIgnore // 防止数据库逻辑死循环注解
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy = "parentPermission",targetEntity= Permission.class)
    @JsonIgnore
    private Set children = new HashSet(0);//当前权限的下级权限


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;

    @Transient
    private  String createTimeStr;

    @Transient
    private String updateTimeStr;



    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Permission getParentPermission() {
        return parentPermission;
    }

    public void setParentPermission(Permission parentPermission) {
        this.parentPermission = parentPermission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGenerateMenu() {
        return generateMenu;
    }

    public void setGenerateMenu(String generateMenu) {
        this.generateMenu = generateMenu;
    }

    public Integer getZindex() {
        return zindex;
    }

    public void setZindex(Integer zindex) {
        this.zindex = zindex;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set getChildren() {
        return children;
    }

    public void setChildren(Set children) {
        this.children = children;
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
        return "Permission{" +
                "uuid='" + uuid + '\'' +
                ", parentPermission=" + parentPermission +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", generateMenu='" + generateMenu + '\'' +
                ", zindex=" + zindex +
                ", roles=" + roles +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", updateTimeStr='" + updateTimeStr + '\'' +
                '}';
    }
}
