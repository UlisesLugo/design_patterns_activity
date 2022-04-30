package com.itesm.financial;

import java.util.List;

public class SimpleReportFactory {
    private static List<Ride> results;

    public static FinancialReportPresenter getReport(String type){
        if(type.equals("web")){
            return new WebReport(results);
        } else if(type.equals("text")){
            return new PrintReport(results);
        }
        return null;
    }

    public static void setResults(List<Ride> results) {
        SimpleReportFactory.results = results;
    }
}
