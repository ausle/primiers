package asule.primiers.entity;

import java.util.List;

/**
 * Created by lenovo on 2016/11/9.
 */

public class CityEntity {


    private List<City> city;

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }

    public class City{
        /**
         * label : 北京Beijing010
         * name : 北京
         * pinyin : Beijing
         * zip : 010
         */

        private String label;
        private String name;
        private String pinyin;
        private String zip;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPinyin() {
            return pinyin;
        }

        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }
    }




}
