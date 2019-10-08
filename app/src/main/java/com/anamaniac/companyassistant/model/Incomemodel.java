package com.anamaniac.companyassistant.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Incomemodel implements Parcelable {
    private Integer Gains;
    private Integer Loss;
    public Incomemodel(){

    }

    public Incomemodel(Parcel in) {
        Gains = in.readInt();
        Loss = in.readInt();
    }

    public static final Creator<Incomemodel> CREATOR = new Creator<Incomemodel>() {
        @Override
        public Incomemodel createFromParcel(Parcel in) {
            return new Incomemodel(in);
        }

        @Override
        public Incomemodel[] newArray(int size) {
            return new Incomemodel[size];
        }
    };

    public Integer getGains() {
        return Gains;
    }

    public void setGains(Integer gains) {
        Gains = gains;
    }

    public Integer getLoss() {
        return Loss;
    }

    public void setLoss(Integer loss) {
        this.Loss = loss;
    }
    public Incomemodel(Integer gains, Integer loss){
        Gains = gains;
        Loss = loss;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Gains);
        dest.writeInt(Loss);
    }
}
