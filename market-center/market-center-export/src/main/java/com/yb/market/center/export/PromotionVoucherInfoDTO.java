package com.bjucloud.marketcenter.export;

import java.io.Serializable;
import java.util.Date;

public class PromotionVoucherInfo implements Serializable {
    /** ID */
    private Long id;

    /** 代金券类型编码（自动生成） */
    private String voucherTypeCode;

    /** 代金券类型名称 */
    private String voucherTypeName;

    /** 代金券类型前缀 */
    private String cardPrefix;

    /** 代金券卡号长度 */
    private Integer voucherNumberLength;

    /** 代金券类型状态 1未生效 2生效中 3已失效 4已下架 5已关闭 */
    private Integer status;

    /** 生成数量限制：-1代表无限制 */
    private Integer generatedQuantity;

    /** 0:手动生成 1：实时生成 2：门店手动领取  */
    private Integer generationType;

    /** 活动开始时间 */
    private Date activityStartTime;

    /** 活动结束时间	 */
    private Date activityEndTime;

    /** 有效期类型 1：生成时间+周期 2：自定义时间 */
    private Integer validTimeType;

    /** 周期（有效期） */
    private Integer period;

    /** 使用开始时间 */
    private Date useStartTime;

    /** 使用结束时间 */
    private Date useEndTime;

    /** 创建时间 */
    private Date createTime;

    /** 创建人id */
    private Long createId;

    /** 创建人名称 */
    private String createName;

    /** 备注 */
    private String remark;

    /** 活动参与平台 O2O SFA */
    private String participatePlatform;

    /** 活动发布系统：1、集团系统。2、商家系统 */
    private Integer publishSystem;

    /** 发布人id */
    private Long publisherId;

    /** 发布人code */
    private String publisherCode;

    /** 发布人姓名 */
    private String publisherName;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoucherTypeCode() {
        return voucherTypeCode;
    }

    public void setVoucherTypeCode(String voucherTypeCode) {
        this.voucherTypeCode = voucherTypeCode == null ? null : voucherTypeCode.trim();
    }

    public String getVoucherTypeName() {
        return voucherTypeName;
    }

    public void setVoucherTypeName(String voucherTypeName) {
        this.voucherTypeName = voucherTypeName == null ? null : voucherTypeName.trim();
    }

    public String getCardPrefix() {
        return cardPrefix;
    }

    public void setCardPrefix(String cardPrefix) {
        this.cardPrefix = cardPrefix == null ? null : cardPrefix.trim();
    }

    public Integer getVoucherNumberLength() {
        return voucherNumberLength;
    }

    public void setVoucherNumberLength(Integer voucherNumberLength) {
        this.voucherNumberLength = voucherNumberLength;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGeneratedQuantity() {
        return generatedQuantity;
    }

    public void setGeneratedQuantity(Integer generatedQuantity) {
        this.generatedQuantity = generatedQuantity;
    }

    public Integer getGenerationType() {
        return generationType;
    }

    public void setGenerationType(Integer generationType) {
        this.generationType = generationType;
    }

    public Date getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Date activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public Integer getValidTimeType() {
        return validTimeType;
    }

    public void setValidTimeType(Integer validTimeType) {
        this.validTimeType = validTimeType;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Date getUseStartTime() {
        return useStartTime;
    }

    public void setUseStartTime(Date useStartTime) {
        this.useStartTime = useStartTime;
    }

    public Date getUseEndTime() {
        return useEndTime;
    }

    public void setUseEndTime(Date useEndTime) {
        this.useEndTime = useEndTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getParticipatePlatform() {
        return participatePlatform;
    }

    public void setParticipatePlatform(String participatePlatform) {
        this.participatePlatform = participatePlatform == null ? null : participatePlatform.trim();
    }

    public Integer getPublishSystem() {
        return publishSystem;
    }

    public void setPublishSystem(Integer publishSystem) {
        this.publishSystem = publishSystem;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherCode() {
        return publisherCode;
    }

    public void setPublisherCode(String publisherCode) {
        this.publisherCode = publisherCode == null ? null : publisherCode.trim();
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName == null ? null : publisherName.trim();
    }
}