package com.itesm.financial;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FinancialReportController {
    private List<Ride> rides;

    // Controller is created using Dependency Inversion
    public FinancialReportController(List<Ride> rides) {
        this.rides = rides;
    }

    public void loadFile(String filename) throws IOException {
        InputStream inputStream = FinancialReportController.class.getClassLoader().getResourceAsStream(filename);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        for (String rawLine; (rawLine = reader.readLine()) != null;) {
            List<String> line = CSVUtils.parseLine(rawLine);
            Ride newRide = RidesParser.parseFromList(line);
            if(newRide != null) {
                rides.add(newRide);
            }
        }
    }

}
