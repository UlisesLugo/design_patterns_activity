package com.itesm.financial;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private static final String CSV_FILENAME = "taxi-data.csv";

    public static void main(String[] args) throws Exception {
        System.out.println("Financial Report Generation");

        List<Ride> result = new ArrayList<>();
        FinancialReportController controller = new FinancialReportController(result);

        controller.loadFile(CSV_FILENAME);

        SimpleReportFactory.setResults(result);

        // OO Principle: Program to interfaces, not implementation
        FinancialReportPresenter financialReport =
                SimpleReportFactory.getReport("text");
        financialReport.createContent();
        // System.out.println(htmlReport);
        financialReport.createFile();
    }
}
