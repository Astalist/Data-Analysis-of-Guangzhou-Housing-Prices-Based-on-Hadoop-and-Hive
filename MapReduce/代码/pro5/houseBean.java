package com.gaolei.shixun.pro5;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class houseBean implements WritableComparable<houseBean> {
    private double price;
    private double total;
    private String area;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public houseBean() {
    }

    @Override
    public int compareTo(houseBean o) {
        if(this.price==o.price){
            double cnt=this.total-o.total;
            if(cnt<0){
                return -1;
            }
            else {return 1;}
        }
        else{
            double cnt=this.price-o.price;
            if(cnt<0){
                return -1;
            }
            else {return 1;}
        }
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeDouble(this.price);
        dataOutput.writeDouble(this.total);
        dataOutput.writeUTF(this.area);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.price=dataInput.readDouble();
        this.total=dataInput.readDouble();
        this.area=dataInput.readUTF();
    }

    @Override
    public String toString() {
        return this.area+"\t"+this.price+"\t"+this.total+"w";
    }
}
