package com.zuora.interview;

import java.util.List;

public class ThreePageUrl {

    String urlOne;
    String urlTwo;
    String urlThree;

    public ThreePageUrl(String urlOne, String urlTwo, String urlThree) {
        this.urlOne = urlOne;
        this.urlTwo = urlTwo;
        this.urlThree = urlThree;
    }

    public ThreePageUrl(List<String> list) {
        this.urlOne = list.get(0);
        this.urlTwo = list.get(1);
        this.urlThree = list.get(2);
    }

    public void test(){

    }



    @Override public String toString() {
        return "\n" +
                urlOne + " -> " +
                urlTwo +  " -> " +
                urlThree  ;

    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ThreePageUrl that = (ThreePageUrl) o;

        if (!urlOne.equals(that.urlOne))
            return false;
        if (!urlTwo.equals(that.urlTwo))
            return false;
        return urlThree.equals(that.urlThree);
    }

    @Override public int hashCode() {
        int result = urlOne.hashCode();
        result = 31 * result + urlTwo.hashCode();
        result = 31 * result + urlThree.hashCode();
        return result;
    }
}
