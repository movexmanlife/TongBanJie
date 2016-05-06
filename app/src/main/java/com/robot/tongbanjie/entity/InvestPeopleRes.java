package com.robot.tongbanjie.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class InvestPeopleRes {
    static List<InvestPeople> investPeopleList;
    static {
        investPeopleList = new ArrayList<>();
        investPeopleList.add(new InvestPeople("2016-04-06 23:15", 2000.00f, "135******41"));
        investPeopleList.add(new InvestPeople("2016-05-06 08:15", 100.00f, "133******85"));
        investPeopleList.add(new InvestPeople("2016-05-05 17:15", 12430.00f, "136******55"));
        investPeopleList.add(new InvestPeople("2016-05-05 16:15", 120.00f, "139******07"));
        investPeopleList.add(new InvestPeople("2016-05-05 15:15", 700.00f, "137******29"));

        investPeopleList.add(new InvestPeople("2016-05-04 22:15", 560.00f, "138******57"));
        investPeopleList.add(new InvestPeople("2016-05-04 05:15", 420.00f, "189******39"));
        investPeopleList.add(new InvestPeople("2016-05-03 15:15", 570.00f, "159******98"));
        investPeopleList.add(new InvestPeople("2016-05-03 11:15", 87400.00f, "159******08"));
        investPeopleList.add(new InvestPeople("2016-05-03 08:15", 84560.00f, "139******29"));

        investPeopleList.add(new InvestPeople("2016-05-02 11:28", 54300.00f, "151******38"));
        investPeopleList.add(new InvestPeople("2016-05-02 11:12", 1800.00f, "159******20"));
        investPeopleList.add(new InvestPeople("2016-05-02 09:53", 8800.00f, "139******83"));
        investPeopleList.add(new InvestPeople("2016-05-02 08:55", 650.00f, "189******18"));
        investPeopleList.add(new InvestPeople("2016-05-02 08:15", 1000.00f, "139******84"));

        investPeopleList.add(new InvestPeople("2016-05-01 22:19", 5600.00f, "186******96"));
        investPeopleList.add(new InvestPeople("2016-05-01 20:33", 78900.00f, "153******24"));
        investPeopleList.add(new InvestPeople("2016-05-01 18:41", 56700.00f, "152******57"));
        investPeopleList.add(new InvestPeople("2016-05-01 16:26", 53600.00f, "187******13"));
        investPeopleList.add(new InvestPeople("2016-05-01 13:25", 34500.00f, "131******33"));
    }

    public static List<InvestPeople> getInvestPeopleList() {
        return investPeopleList;
    }
}
