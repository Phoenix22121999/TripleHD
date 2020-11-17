package com.example.triplehd.ObjectClass;

public class TheLoai {
    HanhDong HanhDong;
    KinhDi KinhDi;
    VoThuat VoThuat;
    ThanThoai ThanThoai;

    public TheLoai(HanhDong hanhDong, KinhDi kinhDi, VoThuat voThuat, ThanThoai thanThoai) {
        HanhDong = hanhDong;
        KinhDi = kinhDi;
        VoThuat = voThuat;
        ThanThoai = thanThoai;
    }

    public com.example.triplehd.ObjectClass.HanhDong getHanhDong() {
        return HanhDong;
    }

    public void setHanhDong(com.example.triplehd.ObjectClass.HanhDong hanhDong) {
        HanhDong = hanhDong;
    }

    public com.example.triplehd.ObjectClass.KinhDi getKinhDi() {
        return KinhDi;
    }

    public void setKinhDi(com.example.triplehd.ObjectClass.KinhDi kinhDi) {
        KinhDi = kinhDi;
    }

    public com.example.triplehd.ObjectClass.VoThuat getVoThuat() {
        return VoThuat;
    }

    public void setVoThuat(com.example.triplehd.ObjectClass.VoThuat voThuat) {
        VoThuat = voThuat;
    }

    public com.example.triplehd.ObjectClass.ThanThoai getThanThoai() {
        return ThanThoai;
    }

    public void setThanThoai(com.example.triplehd.ObjectClass.ThanThoai thanThoai) {
        ThanThoai = thanThoai;
    }
}
