package william.designprinciple.srp;

import lombok.Getter;

/**
 * @Author zhangshenao
 * @Date 2021-01-10
 * @Description 单一职责原则（Single Responsibility Principle，SRP）
 * 尽量不要设计这种大而全的类,而是尽量将不同的功能拆分到不同的类中
 */
@Getter
public class UserInfo {
    //用户基本属性
    private long userId;
    private String username;
    private int gender;
    private String avatarUrl;
    private long createTime;

    //用户通信信息
    private String email;
    private String telephone;
    private long lastLoginTime;

    //用户物流信息
    private String provinceOfAddress; // 省
    private String cityOfAddress; // 市
    private String regionOfAddress; // 区
    private String detailedAddress; // 详细地址
}
