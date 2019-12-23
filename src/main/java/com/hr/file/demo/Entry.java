package com.hr.file.demo;

public class Entry {
    private String NET_ID;//41
    private String STATION_ID;//33018
    private String NODE_TYPE;//ETC
    private String CREATION_TIME;
    private Integer SHIFT_NO;//1
    private String CREATION_TIME_1;//
    private String LICENSE_NO;//豫AW772W
    private Integer VEHICLE_SORT;//0
    private String VEHICLE_CLASS;//A
    private String VEHICLE_CASE;//130
    private String PASSCARD_ID;//41011439220102005447
    private Integer PASSCARD_TYPE;//1
    private Integer AXIAL_NUM;//0
    private Integer TOLL_WEIGHT;//0
    private String OPERATOR_ID;//110103096
    private String LOGON_TIME;//2018/07/05 16:09:28.000

    public String getNET_ID() {
        return NET_ID;
    }

    public void setNET_ID(String NET_ID) {
        this.NET_ID = NET_ID;
    }

    public String getSTATION_ID() {
        return STATION_ID;
    }

    public void setSTATION_ID(String STATION_ID) {
        this.STATION_ID = STATION_ID;
    }

    public String getNODE_TYPE() {
        return NODE_TYPE;
    }

    public void setNODE_TYPE(String NODE_TYPE) {
        this.NODE_TYPE = NODE_TYPE;
    }

    public String getCREATION_TIME() {
        return CREATION_TIME;
    }

    public void setCREATION_TIME(String CREATION_TIME) {
        this.CREATION_TIME = CREATION_TIME;
    }

    public Integer getSHIFT_NO() {
        return SHIFT_NO;
    }

    public void setSHIFT_NO(Integer SHIFT_NO) {
        this.SHIFT_NO = SHIFT_NO;
    }

    public String getCREATION_TIME_1() {
        return CREATION_TIME_1;
    }

    public void setCREATION_TIME_1(String CREATION_TIME_1) {
        this.CREATION_TIME_1 = CREATION_TIME_1;
    }

    public String getLICENSE_NO() {
        return LICENSE_NO;
    }

    public void setLICENSE_NO(String LICENSE_NO) {
        this.LICENSE_NO = LICENSE_NO;
    }

    public Integer getVEHICLE_SORT() {
        return VEHICLE_SORT;
    }

    public void setVEHICLE_SORT(Integer VEHICLE_SORT) {
        this.VEHICLE_SORT = VEHICLE_SORT;
    }

    public String getVEHICLE_CLASS() {
        return VEHICLE_CLASS;
    }

    public void setVEHICLE_CLASS(String VEHICLE_CLASS) {
        this.VEHICLE_CLASS = VEHICLE_CLASS;
    }

    public String getVEHICLE_CASE() {
        return VEHICLE_CASE;
    }

    public void setVEHICLE_CASE(String VEHICLE_CASE) {
        this.VEHICLE_CASE = VEHICLE_CASE;
    }

    public String getPASSCARD_ID() {
        return PASSCARD_ID;
    }

    public void setPASSCARD_ID(String PASSCARD_ID) {
        this.PASSCARD_ID = PASSCARD_ID;
    }

    public Integer getPASSCARD_TYPE() {
        return PASSCARD_TYPE;
    }

    public void setPASSCARD_TYPE(Integer PASSCARD_TYPE) {
        this.PASSCARD_TYPE = PASSCARD_TYPE;
    }

    public Integer getAXIAL_NUM() {
        return AXIAL_NUM;
    }

    public void setAXIAL_NUM(Integer AXIAL_NUM) {
        this.AXIAL_NUM = AXIAL_NUM;
    }

    public Integer getTOLL_WEIGHT() {
        return TOLL_WEIGHT;
    }

    public void setTOLL_WEIGHT(Integer TOLL_WEIGHT) {
        this.TOLL_WEIGHT = TOLL_WEIGHT;
    }

    public String getOPERATOR_ID() {
        return OPERATOR_ID;
    }

    public void setOPERATOR_ID(String OPERATOR_ID) {
        this.OPERATOR_ID = OPERATOR_ID;
    }

    public String getLOGON_TIME() {
        return LOGON_TIME;
    }

    public void setLOGON_TIME(String LOGON_TIME) {
        this.LOGON_TIME = LOGON_TIME;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "NET_ID='" + NET_ID + '\'' +
                ", STATION_ID='" + STATION_ID + '\'' +
                ", NODE_TYPE='" + NODE_TYPE + '\'' +
                ", CREATION_TIME='" + CREATION_TIME + '\'' +
                ", SHIFT_NO=" + SHIFT_NO +
                ", CREATION_TIME_1='" + CREATION_TIME_1 + '\'' +
                ", LICENSE_NO='" + LICENSE_NO + '\'' +
                ", VEHICLE_SORT=" + VEHICLE_SORT +
                ", VEHICLE_CLASS='" + VEHICLE_CLASS + '\'' +
                ", VEHICLE_CASE='" + VEHICLE_CASE + '\'' +
                ", PASSCARD_ID='" + PASSCARD_ID + '\'' +
                ", PASSCARD_TYPE=" + PASSCARD_TYPE +
                ", AXIAL_NUM=" + AXIAL_NUM +
                ", TOLL_WEIGHT=" + TOLL_WEIGHT +
                ", OPERATOR_ID='" + OPERATOR_ID + '\'' +
                ", LOGON_TIME='" + LOGON_TIME + '\'' +
                '}';
    }
}
