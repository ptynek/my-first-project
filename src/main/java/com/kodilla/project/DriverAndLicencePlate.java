package com.kodilla.project;

import com.opencsv.bean.CsvBindByPosition;

public class DriverAndLicencePlate {

    @CsvBindByPosition(position = 0)
    private String nameAndSurname;
    @CsvBindByPosition(position = 1)
    private String licencePlate;

    public DriverAndLicencePlate(String nameAndSurname, String licencePlate){
        this.nameAndSurname = nameAndSurname;
        this.licencePlate = licencePlate;
    }


    public DriverAndLicencePlate(){}

    public String getNameAndSurname() {
        return nameAndSurname;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setNameAndSurname(String nameAndSurname) {
        this.nameAndSurname = nameAndSurname;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverAndLicencePlate that = (DriverAndLicencePlate) o;

        if (!nameAndSurname.equals(that.nameAndSurname)) return false;
        return licencePlate.equals(that.licencePlate);
    }

    @Override
    public int hashCode() {
        int result = nameAndSurname.hashCode();
        result = 31 * result + licencePlate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DriverAndLicencePlate{" +
                "nameAndSurname='" + nameAndSurname + '\'' +
                ", licencePlate='" + licencePlate + '\'' +
                '}';
    }
}
