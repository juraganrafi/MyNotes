package com.belajar.rafi.pajaku.entity;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * Kelas model untuk merepresentasikan data yang tersimpan
 * dijadikan Parcel agar mudah dalam pengiriman data antar activity
 */

public class Note implements Parcelable {
    private int     id;
    private String  vehicle; //title
    private String  bbnkb; //desc
    private String  pkb;
    private String  swdkllaj;
    private String  admSTNK;
    private String  admTNKB;
    private String  jumlah;
    private String  date;
    //  date + time
    private String  notif;
    private int     datePic;
    private int     timePic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getBbnkb() {
        return bbnkb;
    }

    public void setBbnkb(String bbnkb) {
        this.bbnkb = bbnkb;
    }

    public String getPkb() {
        return pkb;
    }

    public void setPkb(String pkb) {
        this.pkb = pkb;
    }

    public String getSwdkllaj() {
        return swdkllaj;
    }

    public void setSwdkllaj(String swdkllaj) {
        this.swdkllaj = swdkllaj;
    }

    public String getAdmSTNK() {
        return admSTNK;
    }

    public void setAdmSTNK(String admSTNK) {
        this.admSTNK = admSTNK;
    }

    public String getAdmTNKB() {
        return admTNKB;
    }

    public void setAdmTNKB(String admTNKB) {
        this.admTNKB = admTNKB;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotif() {
        return notif;
    }

    public void setNotif(String notif) {
        this.notif = notif;
    }

    public int getDatePic() {
        return datePic;
    }

    public void setDatePic(int datePic) {
        this.datePic = datePic;
    }

    public int getTimePic() {
        return timePic;
    }

    public void setTimePic(int timePic) {
        this.timePic = timePic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.vehicle);
        parcel.writeString(this.bbnkb);
        parcel.writeString(this.pkb);
        parcel.writeString(this.swdkllaj);
        parcel.writeString(this.admSTNK);
        parcel.writeString(this.admTNKB);
        parcel.writeString(this.jumlah);
        parcel.writeString(this.date);
        parcel.writeString(this.notif);
        parcel.writeInt(this.datePic);
        parcel.writeInt(this.timePic);
    }

    public Note(){
    }

    protected Note(Parcel in){
        this.id         = in.readInt();
        this.vehicle    = in.readString();
        this.bbnkb      = in.readString();
        this.pkb        = in.readString();
        this.swdkllaj   = in.readString();
        this.admSTNK    = in.readString();
        this.admTNKB    = in.readString();
        this.jumlah     = in.readString();
        this.date       = in.readString();
        this.notif      = in.readString();
        this.datePic    = in.readInt();
        this.timePic    = in.readInt();

    }

    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>(){

        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
