package com.itesm.financial;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class WebReport implements FinancialReportPresenter {
    private String content;
    private List<Ride> rides;

    public WebReport(List<Ride> rides) {
        this.rides = rides;
    }

    @Override
    public void createContent() {
        StringBuilder builder = new StringBuilder();
        builder.append(createHeaders("Taxi Report"));
        builder.append(createTableHeaders());
        rides.forEach( ride -> {
            builder.append(addRide(ride));
        });
        builder.append(closeTableHeaders());

        content = builder.toString();
    }

    @Override
    public void createFile() throws IOException {
        FileWriter fileWriter = new FileWriter("financial-report.html");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(content);
        printWriter.close();
    }

    private String createHeaders(String title){
        return "<h1>" + title + "</h1>";
    }

    private String createTableHeaders() {
        return "<table>" +
                "<tr>" +
                    "<th> TaxiID </th>" +
                    "<th> Pickup time </th>" +
                    "<th> Dropoff time </th>" +
                    "<th> Passenger count </th>" +
                    "<th> Trip Distance </th>" +
                    "<th> Total amount </th>" +
                "</tr>";
    }

    private String closeTableHeaders() {
        return "</table>";
    }

    private String addRide(Ride ride) {
        return "<tr>" +
                "<td>" + ride.getTaxiId() + "</td>" +
                "<td>" + ride.getPickUpTime() + "</td>" +
                "<td>" + ride.getDropOffTime() + "</td>" +
                "<td>" + ride.getPassengerCount() + "</td>" +
                "<td>" + ride.getTripDistance() + "</td>" +
                "<td>" + formatAmount(ride.getTollsAmount()) + "</td>" +
                "</tr>";
    }

    private String formatAmount(double amount) {
        if(amount < 0) {
            return "<span style='color:red'>" + amount + "</span>";
        }
        return Double.toString(amount);
    }
}
