package com.weinan.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.weinan.entity.Role;

public class UserVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;//员工Id
	private String staffCode; //员工编码(即userName)
	private String name; //员工姓名
	private String school;  //毕业院校
	private int age;//年龄
	private String idCard; //身份证号码
	private String phoneNumber; //手机号码
	private String email; //邮箱
	private String address; //现居地址
	private String domicile; //户籍所在地
	private String nativePlace;//籍贯
	private String Country; //国籍
	private String nation; //民族
	private String departmentName; //部门
	private Date birthday; //生日
	private Date createTime; //入职时间
	private Byte sex; //性别（1：男，2：女）
	private Byte isMarried;//婚姻状况(1:未婚,2:已婚)
	private Byte education;//学历(1:专科,2:本科,3:硕士,4:博士)
	private String educationStr;//学历(1:专科,2:本科,3:硕士,4:博士) 文字显示
	private String isMarriedStr;//婚姻状况(1:未婚,2:已婚) 文字显示
	private String sexStr; //性别（1：男，2：女） 文字显示
	private String token; //登录后的令牌token
	private String politic;//政治面貌
	private Byte status; //状态（0：未入职，1：入职，2：离职）
	private List<Role> roles; //职位
	private String image;//照片
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDomicile() {
		return domicile;
	}
	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Byte getSex() {
		return sex;
	}
	public void setSex(Byte sex) {
		this.sex = sex;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getSexStr() {
		return sexStr;
	}
	public void setSexStr(String sexStr) {
		this.sexStr = sexStr;
	}
	public Byte getIsMarried() {
		return isMarried;
	}
	public void setIsMarried(Byte isMarried) {
		this.isMarried = isMarried;
	}
	public String getIsMarriedStr() {
		return isMarriedStr;
	}
	public void setIsMarriedStr(String isMarriedStr) {
		this.isMarriedStr = isMarriedStr;
	}
	public String getPolitic() {
		return politic;
	}
	public void setPolitic(String politic) {
		this.politic = politic;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public Byte getEducation() {
		return education;
	}
	public void setEducation(Byte education) {
		this.education = education;
	}
	public String getEducationStr() {
		return educationStr;
	}
	public void setEducationStr(String educationStr) {
		this.educationStr = educationStr;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}
