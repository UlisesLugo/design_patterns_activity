package com.itesm.financial;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PrintReport implements  FinancialReportPresenter{
    private String content;
    private List<Ride> rides;

    public PrintReport(List<Ride> rides) {
        this.rides = rides;
    }
    @Override
    public void createContent() {
        StringBuilder builder = new StringBuilder();
        builder.append(createHeader("Taxi Report"));
        rides.forEach( ride -> {
            builder.append(addRide(ride));
        });

        content = builder.toString();
    }

    @Override
    public void createFile() throws IOException {
        FileWriter fileWriter = new FileWriter("financial-report.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(content);
        printWriter.close();
    }

    private String createHeader(String title) {
        return title + "\n" +
                "TaxiId \t\t" +
                "Pickup Time \t\t\t\t\t" +
                "Dropoff Time \t\t" +
                "Passenger Count \t" +
                "Trip Distance \t" +
                "Total amount \n";
    };

    private String addRide(Ride ride) {
        return ride.getTaxiId() + "\t" +
                ride.getPickUpTime() + "\t" +
                ride.getDropOffTime() + "\t" +
                ride.getPassengerCount() + "\t\t\t\t" +
                ride.getTripDistance() + "\t\t\t\t" +
                formatAmount(ride.getTollsAmount())  + "\n";
    }

    private String formatAmount(double amount) {
        String result = Double.toString(Math.abs(amount));
        if(amount < 0) {
            result = "(" + result + ")";
        }
        return result;
    }

}
